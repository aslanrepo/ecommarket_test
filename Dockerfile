FROM amazoncorretto:17-alpine-jdk
MAINTAINER telegrambots.ton
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]