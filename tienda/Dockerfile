FROM openjdk:20
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api.jar
ENTRYPOINT ["java","-jar","/api.jar"]