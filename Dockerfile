# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Kuba Wasilewski <k.k.wasilewski@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=target/bananapp-spring-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} bananapp-spring.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/bananapp-spring.jar"]
