FROM openjdk:8-jre-alpine
ADD /target/*.jar  core.jar
ENTRYPOINT ["java","-Dprofile=prod","-jar","core.jar"]
MAINTAINER Murat Demir