# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jdk-alpine as builder

RUN apk upgrade && apk add maven

ADD . /usr/local/subnet
WORKDIR /usr/local/subnet

RUN mvn package

#---------------------------------------------------------------------------------------#

FROM openjdk:8-jdk-alpine


EXPOSE 8080

COPY --from=builder /usr/local/subnet/target/*.jar /app.jar


ENTRYPOINT ["java", "-jar", "/app.jar"]
