FROM openjdk:11
ADD ./target/web-analytic-service-0.0.1-SNAPSHOT.jar /event/src/web-analytic-service-0.0.1-SNAPSHOT.jar
EXPOSE 8098
WORKDIR event/src
ENTRYPOINT ["java","-jar","web-analytic-service-0.0.1-SNAPSHOT.jar"]