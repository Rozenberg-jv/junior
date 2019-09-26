FROM openjdk:jdk-11.0.4_11-alpine

COPY /docker/junior-0.1.jar /app/junior.jar

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=default", "/app/junior.jar"]