FROM openjdk:11
WORKDIR /app
ENV domain=http://localhost:8000
COPY ./target/stasher-*.jar ./stasher.jar
ENTRYPOINT ["java","-jar","./stasher.jar"]
EXPOSE 8080