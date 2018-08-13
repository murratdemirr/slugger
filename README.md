
A demo for Spring Boot microservices and dockerized example

# How to run

You can run the application with docker compose.

Here are some docker command lines you can use.

**Deploy the whole project in docker container.**
> docker-compose up -d 

Notice: Before the deploying the application in docker please make sure 
> maven clean install

More information on how to run can be found at: https://docs.docker.com/compose/reference/up/


# Core Api

This application developed with spring boot and provides email restful endpoints and processing batch stuff. 
Core API listens to the Kafka Queue for each message from any edge API and each 5 minute thus data write to a database.

# Edge Api

This application provides that xml feed endpoint and proxy restful client via core app. 
The main goal of this application is parsing XML and send each email address to Kafka Queue.

Notice: You can run this application as multiple instances. 

for more information about rest services look at the below link

http://localhost:8085/edge/api/v1/swagger-ui.html


