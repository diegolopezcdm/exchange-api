FROM openjdk:8u111-jre-alpine
MAINTAINER Diegol <diegolopezcdm@gmail.com>

EXPOSE 8761

ADD ./target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
#sudo docker build -t exchanguediscovey .
#sudo docker run -p 8761:8761 exchanguediscovey