FROM openjdk:8

ENV   POSTGRES="pg-net" KAFKA="kafka-net" KAFKA_LISTEN="9092" EUREKA="eur-net"

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw install -DskipTests

CMD ["java", "-jar", "target/EmployeeSOAP-0.0.1-SNAPSHOT.jar"]