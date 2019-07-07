FROM openjdk:8-jdk-alpine
EXPOSE 9010
RUN apk add --no-cache tzdata
ENV TZ=America/Guayaquil
COPY spring-server/target/devops-1.0.0.jar /app
ENTRYPOINT ["java","-Duser.language=es","-jar","/app/devops-1.0.0.jar"]
