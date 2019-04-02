FROM openjdk:11
ADD ./target/question-provider-0.0.1-SNAPSHOT.jar /usr/src/question-provider-0.0.1-SNAPSHOT.jar
EXPOSE 8095
WORKDIR usr/src
ENTRYPOINT ["java","-jar","question-provider-0.0.1-SNAPSHOT.jar"]
