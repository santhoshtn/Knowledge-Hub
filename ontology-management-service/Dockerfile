FROM openjdk:11
ADD ./target/ontology-management-service-0.0.1-SNAPSHOT.jar /usr/src/ontology-management-service-0.0.1-SNAPSHOT.jar
EXPOSE 8071
WORKDIR usr/src
ENTRYPOINT ["java","-jar","ontology-management-service-0.0.1-SNAPSHOT.jar"]

