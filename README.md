# Transactional Outbox Pattern

Demo application implementing Transactional Outbox pattern with Spring Boot, PostgreSQL and Kafka

## How to Run

```shell
docker compose up -d --build
```

## Useful commands

View outbox topic
```shell
docker exec -it kafka sh
kafka-console-consumer --bootstrap-server kafka:29092 --topic outbox --from-beginning
```

Create todo:
```shell
curl -X POST \
-H 'Content-type: application/json' \
--data '{"todo": "My todo"}' \
http://localhost:8080/api/todos
```

List todos:
```shell
curl http://localhost:8080/api/todos
```

Delete todo:
```shell
curl -X DELETE http://localhost:8080/api/todos/1000
```