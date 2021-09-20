# ktor-bookstore-app

Bookstore app developed using Ktor and Mongo.


## Testing 

The project use [k6](https://k6.io/) to do the performance tests. The project is using docker-compose to run the k6, 
but there are other ways of installing that can be found [here](https://k6.io/docs/getting-started/installation/).

To execute the performance test on the root dir of the project run:

    docker-compose run k6 run //scripts//script.js
