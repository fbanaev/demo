FROM openjdk:11

COPY .target/demo-*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]