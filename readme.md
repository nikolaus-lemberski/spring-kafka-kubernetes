# Spring and Kafka on Kubernetes

Simple demo project for Spring and Kafka on Kubernetes.

# Producer

Simple REST endpoint, to add messages to the system with POST and Json properties "author" and "text".

# Consumer

Kafka consumer, logs messages.

# Getting started

Setup kafka namespace with `kubectl apply -f kubernetes/namespace.yml`

Setup Kafka, for example with Strimzi on Minikube:

https://strimzi.io/quickstarts/

```
minikube start --memory=4096
kubectl apply -f 'https://strimzi.io/install/latest?namespace=kafka' -n kafka
kubectl apply -f https://strimzi.io/examples/latest/kafka/kafka-persistent-single.yaml -n kafka 
```

Wait until Kafka is healthy: 

`kubectl wait kafka/my-cluster --for=condition=Ready --timeout=300s -n kafka`

Build images and push to docker
```
./mvnw clean install

docker build -t <your-docker-name>/consumer consumer
docker build -t <your-docker-name>/producer producer

docker push <your-docker-name>/consumer:latest
docker push <your-docker-name>/producer:latest

kubectl apply -f kubernetes/consumer.yml
kubectl apply -f kubernetes/producer.yml
```

Get the service address of producer from minikube: 

`minikube service producer --url -n kafka`

Open consumer logs in a terminal and POST some messages to the producer.