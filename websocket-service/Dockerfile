FROM openjdk:11
ADD ./target/websocket-service-0.0.1-SNAPSHOT.jar /usr/src/websocket-service-0.0.1-SNAPSHOT.jar
EXPOSE 8097
WORKDIR usr/src
ENTRYPOINT ["java","-jar","websocket-service-0.0.1-SNAPSHOT.jar"]