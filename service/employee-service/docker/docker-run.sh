docker run --name zookeeper --network kafka-test --network-alias zoo-net --memory="256m" -p 22181:2181 -e ZOOKEEPER_CLIENT_PORT=2181 -e ZOOKEEPER_TICK_TIME=2000 -d confluentinc/cp-zookeeper:latest;
docker run --name kafka -d --network kafka-test --network-alias kafka-net -p 29092:29092 --memory="700m" -e KAFKA_BROKER_ID=1 -e KAFKA_ZOOKEEPER_CONNECT=zoo-net -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092 -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT -e KAFKA_INTER_BROKER_LISTENER_NAME=PLAINTEXT -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 -e KAFKA_AUTO_CREATE_TOPICS_ENABLE=true -e KAFKA_CREATE_TOPICS=order-topic:3:1 confluentinc/cp-kafka:latest;


docker run --name postgres --network kafka-test --network-alias pg-net --memory="700m" -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres:13.0;


docker run --name employee1 --network kafka-test --memory="600m" -it -e TOPIC=topic1 -p 8081:8081 e306e4d04959;
docker run --name employee2 --network kafka-test --memory="600m" -it -e TOPIC=topic2 -e PORT=8082 -p 8082:8082 e306e4d04959;

docker run --name eureka-server --network kafka-test --network-alias eur-net -d -p 8761:8761 ee156154dc38;

docker run --name gateway-app --network kafka-test --memory="2g" -d -p 8765:8765 f618db488fdb;