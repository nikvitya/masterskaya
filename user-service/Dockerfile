FROM amazoncorretto:21-alpine-jdk
COPY target/*.jar user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]