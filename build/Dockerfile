
# first build
FROM maven:3-openjdk-8 AS build
RUN mkdir -p /root/.m2

# copy setting mvn config file
COPY settings.xml /root/.m2
COPY .. /tmp
WORKDIR /tmp
RUN mvn clean package
CMD /bin/bash

# second build run image
FROM openjdk:8
WORKDIR /tmp
COPY --from=build /tmp/target/boot-demo-1.0-SNAPSHOT.jar .
CMD java -jar boot-demo-1.0-SNAPSHOT.jar

# docker build -t yaoqijun/web-demo .   构建镜像
# docker run -dp 8080:8080 --name web-demo yaoqijun/web-demo    启动服务