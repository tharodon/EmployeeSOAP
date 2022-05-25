# the first stage of our build will use a maven 3.6.3 parent image
FROM maven:3.6.3-jdk-8 AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./

# package our application code
RUN mvn clean package

# the second stage of our build will use open jdk 11 on alpine 3.9
FROM openjdk:8

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD target/EmployeeSOAP-0.0.1-SNAPSHOT.jar /demo.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/demo.jar"]

#docker build .
#docker run -it [IMAGE]