FROM openjdk:11
ADD ./target/question-publisher-0.0.1-SNAPSHOT.jar /usr/src/question-publisher-0.0.1-SNAPSHOT.jar
EXPOSE 8077
WORKDIR usr/src
ENTRYPOINT ["java","-jar","question-publisher-0.0.1-SNAPSHOT.jar"]
