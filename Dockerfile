FROM alpine:edge
RUN apk update
RUN apk add --no-cache openjdk17
ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]