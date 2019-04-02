FROM openjdk:11
ADD ./target/web-search-service-0.0.1-SNAPSHOT.jar /usr/src/web-search-service-0.0.1-SNAPSHOT.jar
EXPOSE 8099
WORKDIR usr/src
ENTRYPOINT ["java","-jar","web-search-service-0.0.1-SNAPSHOT.jar"]
