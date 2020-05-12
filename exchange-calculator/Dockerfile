FROM openjdk:8u111-jre-alpine
MAINTAINER Diegol <diegolopezcdm@gmail.com>

EXPOSE 8081

ADD ./target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
#sudo docker build -t exchanguecalculator .
#sudo docker run -p 8081:8081 exchanguecalculator