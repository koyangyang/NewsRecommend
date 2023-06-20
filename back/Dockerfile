FROM openjdk:8-jdk-alpine
#发布到网上时只会把jar包和Dockerfile发布上去
COPY ./target/*.jar /app.jar
#地址映射
CMD ["--server.port=8080"]
#对外暴露端口
EXPOSE 8080
#执行命令
ENTRYPOINT ["java","-jar","/app.jar"]
