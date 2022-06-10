# the first stage of our build will use a maven 3.6.3 parent image
FROM maven:3.6.3-jdk-8 AS MAVEN_BUILD

WORKDIR /home/app
# copy the pom and src code to the container
COPY . .

# package our application code
RUN mvn clean package

# the second stage of our build will use open jdk 11 on alpine 3.9
FROM openjdk:8

ENV  EUREKA="eur-net"

WORKDIR /app

COPY --from=MAVEN_BUILD /home/app/target/eureka-server-0.0.1-SNAPSHOT.jar /app/eureka-server.jar

CMD ["java", "-jar", "eureka-server.jar"]