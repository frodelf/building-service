FROM openjdk:17.0.2-jdk-slim-buster
COPY build/libs/building-service.jar building-service.jar
ENTRYPOINT ["java", "-jar", "building-service.jar"]