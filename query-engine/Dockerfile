FROM openjdk:11
ADD ./target/query-engine-0.0.1-SNAPSHOT.jar /usr/src/query-engine-0.0.1-SNAPSHOT.jar
EXPOSE 8096
WORKDIR usr/src
ENTRYPOINT ["java","-jar","query-engine-0.0.1-SNAPSHOT.jar"]