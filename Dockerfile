FROM openjdk:11-jre-slim-buster
COPY web/target/web*.jar /web.jar
CMD ["java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", "-jar", "/web.jar"]