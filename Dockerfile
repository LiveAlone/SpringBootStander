# pre build docker
# docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -v /Users/yaoqijun/workspace/data/dockerData/maven/.m2:/root/.m2 -w /usr/src/mymaven maven:3-openjdk-8 mvn clean package

FROM openjdk:8
RUN mkdir /data
COPY ./target/boot-demo-1.0-SNAPSHOT.jar /data
WORKDIR /data
EXPOSE 8080
CMD java -jar boot-demo-1.0-SNAPSHOT.jar
