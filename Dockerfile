FROM openjdk:11-jre-slim-buster
COPY web/target/web*.jar /web.jar
CMD ["java", "-jar", "/web.jar"]