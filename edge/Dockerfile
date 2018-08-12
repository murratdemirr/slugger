FROM openjdk:8-jre-alpine
ADD /target/*.jar  edge.jar
ENTRYPOINT ["java","-Dprofile=prod","-jar","edge.jar"]
MAINTAINER Murat Demir