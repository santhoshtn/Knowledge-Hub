FROM openjdk:11
ADD ./target/web-content-extractor-0.0.1-SNAPSHOT.jar /usr/src/web-content-extractor-0.0.1-SNAPSHOT.jar
EXPOSE 8111
WORKDIR usr/src
ENTRYPOINT ["java","-jar","web-content-extractor-0.0.1-SNAPSHOT.jar"]
