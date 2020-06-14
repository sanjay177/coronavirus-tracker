FROM openjdk:8
ADD taget/coronavirus-tracker-0.0.1-SNAPSHOT.jar coronavirus-tracker-0.0.1-SNAPSHOT.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "coronavirus-tracker-0.0.1-SNAPSHOT.jar"]