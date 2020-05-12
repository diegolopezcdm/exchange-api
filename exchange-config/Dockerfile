FROM openjdk:8u111-jre-alpine
MAINTAINER Diegol <diegolopezcdm@gmail.com>

EXPOSE 8888

ADD ./target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
#sudo docker build -t exchangueconfig .
#sudo docker run -p 8081:8081 exchangueconfig