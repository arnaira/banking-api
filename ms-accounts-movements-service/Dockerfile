FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/ms-accounts-movements-service-1.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]