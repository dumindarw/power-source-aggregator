## Power Source Aggregator 

Application will build automatically when push the changes to main branch. Github action will trigger then jar file 
will be pushed to a s3 bucket then elastic-beanstalk instance will fetch that jar file and deploy

The elastic-beanstalk base url as follows

`http://powersourcesaggregator-env-1.eba-swk3begz.us-east-1.elasticbeanstalk.com/`

### Start MariaDB
```
docker run -dt --name mariadb -p 13306:3306 -e MARIADB_ROOT_PASSWORD=root -e MARIADB_DATABASE=psa_db -e MARIADB_USER=psaUser -e MARIADB_PASSWORD=p5aus3R!  mariadb:10.6.5
```

### How to build

```
./gradlew build
```

### How to Run

```
./gradlew bootRun

```

### APIs - Dev

`curl -X POST -d '[{ "name": "Canningtonu", "postcode": "6107","capacity": 13500  }]' -H 'Content-Type: application/json' http://localhost:5000`

`curl -X GET -H 'Content-Type: application/json' 'http://localhost:5000?postCodeFrom=6107&postCodeTo=6120'`

`curl -X GET -H 'Content-Type: application/json' 'http://localhost:5000/Cannington'`

### APIs - Prod (Deployed in Beanstalk)

`curl -X POST -d '[{ "name": "Canningtonu", "postcode": "6107","capacity": 13500  }]' -H 'Content-Type: application/json' http://powersourcesaggregator-env-1.eba-swk3begz.us-east-1.elasticbeanstalk.com/`

`curl -X GET -H 'Content-Type: application/json' 'http://powersourcesaggregator-env-1.eba-swk3begz.us-east-1.elasticbeanstalk.com/?postCodeFrom=4107&postCodeTo=6120'`

### Failed batteries, produces batteries to FAILED_BATTERY topic

`docker run -it --name kafka-producer -p 19092:9092  quay.io/strimzi/kafka:0.30.0-kafka-3.2.0  bin/kafka-console-producer.sh --bootstrap-server 192.168.1.7:9092 --topic FAILED_BATTERY`

`docker run -it --name kafka-consumer -p 29092:9092  quay.io/strimzi/kafka:0.30.0-kafka-3.2.0  bin/kafka-console-consumer.sh --bootstrap-server 192.168.1.7:9092 --topic FAILED_BATTERY --from-beginning`

## Build docker images and push to AWS ECR

First login to  ECR

`aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 325224159075.dkr.ecr.us-east-1.amazonaws.com`

then
```
./gradlew jib

```

That will build the docker image and automatically push image to ECR

## Run application locally (Using docker-compose)

```
docker-compose up -d

```

Then the api is run on port 15000

`curl -X POST -d '[{ "name": "Cannington", "postcode": "6107","capacity": 13500  }]' -H 'Content-Type: application/json' http://localhost:15000/`

`curl -X GET -H 'Content-Type: application/json' 'http://localhost:15000/?postCodeFrom=4107&postCodeTo=6120'`
