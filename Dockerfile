FROM openjdk:11

ENV   POSTGRES="pg-net" KAFKA="kafka-net" KAFKA_LISTEN="9092" EUREKA="eur-net"

COPY . .

RUN chmod +x gradlew && ./gradlew bootJar

CMD ["java", "-jar", "build/libs/EmployeeSOAP-0.0.1-SNAPSHOT.jar"]


