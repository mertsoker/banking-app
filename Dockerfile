FROM eclipse-temurin:17-jdk-jammy
COPY build/libs/*.jar ./banking-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "banking-app.jar"]