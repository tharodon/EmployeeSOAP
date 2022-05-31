# the first stage of our build will use a maven 3.6.3 parent image
FROM maven:3.6.3-jdk-8 AS MAVEN_BUILD

WORKDIR /home/app
# copy the pom and src code to the container
COPY . .

# package our application code
RUN mvn clean package

# the second stage of our build will use open jdk 11 on alpine 3.9
FROM openjdk:8

ENV   POSTGRES="pg-net" KAFKA="kafka-net" KAFKA_LISTEN="9092"

EXPOSE 8081:8081

WORKDIR /app

COPY --from=MAVEN_BUILD /home/app/target/EmployeeSOAP-0.0.1-SNAPSHOT.jar /app/app.jar
COPY --from=MAVEN_BUILD /home/app/src/main/resources /app/resources/

CMD ["java", "-jar", "app.jar"]