FROM openjdk:8u111-jre-alpine
MAINTAINER Diegol <diegolopezcdm@gmail.com>

EXPOSE 8080

ADD ./target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
#sudo docker build -t exchanguecurrency .
#sudo docker run -p 8080:8080 exchanguecurrency