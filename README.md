## Power Source Aggregator 

https://power-sources-aggregator-bkt.s3.amazonaws.com/code/

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

### APIs

`curl -X POST -d '[{ "name": "Canningtonu", "postcode": "6107","capacity": 13500  }]' -H 'Content-Type: application/json' http://localhost:8080`

`curl -X GET -H 'Content-Type: application/json' 'http://localhost:8080?postCodeFrom=6107&postCodeTo=6120'`