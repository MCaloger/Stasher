FROM openjdk:11
VOLUME /stasher
COPY target/stasher-*.jar stasher.jar
ENTRYPOINT ["java","-jar","stasher.jar"]
EXPOSE 8000