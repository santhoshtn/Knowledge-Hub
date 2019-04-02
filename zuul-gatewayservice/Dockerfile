FROM openjdk:11
ADD ./target/zuul-gatewayservice-0.0.1-SNAPSHOT.jar /usr/src/zuul-gatewayservice-0.0.1-SNAPSHOT.jar
EXPOSE 8092
WORKDIR usr/src
ENTRYPOINT ["java","-jar","zuul-gatewayservice-0.0.1-SNAPSHOT.jar"]
