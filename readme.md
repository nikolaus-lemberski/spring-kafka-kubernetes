# Spring and Kafka on Kubernetes

Simple demo project for Spring and Kafka on Kubernetes.

# Producer

Simple REST endpoint, to add messages to the system with POST and Json properties "author" and "text".

# Consumer

Kafka consumer, logs messages.

# Getting started

Setup Kafka, for example with Strimzi on Minikube:

https://strimzi.io/quickstarts/

Wait until Kafka is healthy: `watch kubectl get pods -n kafka`

Build images and push to docker
```
./mvnw clean install

docker build -t <your-docker-name>/consumer consumer
docker build -t <your-docker-name>/producer producer

docker push <your-docker-name>/consumer:latest
docker push <your-docker-name>/producer:latest

kubectl apply -f consumer/deploy.yml -n kafka
kubectl apply -f producer/deploy.yml -n kafka
```

Get the service address of producer from minikube: 

`minikube service producer --url -n kafka`

Open consumer logs in a terminal and POST some messages to the producer.