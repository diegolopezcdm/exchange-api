FROM openjdk:8u111-jre-alpine
MAINTAINER Diegol <diegolopezcdm@gmail.com>

EXPOSE 9999

ADD ./target/*-exec.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
#sudo docker build -t exchanguesecurity .
#sudo docker run -p 9999:9999 exchanguesecurity