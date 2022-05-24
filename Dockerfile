#FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD
#
## copy the pom and src code to the container
#COPY ./ ./
#
#
## package our application code
#
## the second stage of our build will use open jdk 11 on alpine 3.9
#FROM openjdk:8-jdk-alpine
#
## copy only the artifacts we need from the first stage and discard the rest
#COPY --from=MAVEN_BUILD /target/EmployeeSOAP-0.0.1-SNAPSHOT.jar /EmployeeSOAP-0.0.1-SNAPSHOT.jar
#
## set the startup command to execute the jar
#RUN docker-compose up openjdk:8-jdk-alpine
##RUN mvn clean package
#CMD ["java", "-jar", "/EmployeeSOAP-0.0.1-SNAPSHOT.jar"]

FROM openjdk:8

WORKDIR "~/application/"

COPY . .
CMD ["java", "-jar", "target/EmployeeSOAP-0.0.1-SNAPSHOT.jar"]