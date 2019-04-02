FROM openjdk:11

ADD ./target/search-upstream-service-0.0.1-SNAPSHOT.jar usr/src/search-upstream-service-0.0.1-SNAPSHOT.jar
EXPOSE 8094
WORKDIR usr/src
ENTRYPOINT ["java","-jar","search-upstream-service-0.0.1-SNAPSHOT.jar"]