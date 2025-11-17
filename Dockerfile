# --- Build stage ---
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy only wrapper & build scripts first (better layer caching)
COPY gradlew ./
COPY gradle/wrapper/ ./gradle/wrapper/
RUN chmod +x gradlew

# Then copy build files
COPY build.gradle settings.gradle ./
# If you have gradle.properties at project root:
# COPY gradle.properties ./

# Do a lightweight resolve to leverage cache (optional)
RUN ./gradlew --no-daemon --version

# Now copy sources
COPY src ./src

# Build the Boot jar
RUN ./gradlew --no-daemon bootJar

# --- Runtime stage ---
FROM eclipse-temurin:21-jre
WORKDIR /app
EXPOSE 3050
COPY --from=build /app/build/libs/*-SNAPSHOT.jar app.jar
# Or copy the exact JAR name if you know it:
# COPY --from=build /app/build/libs/quizserver-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
