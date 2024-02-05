FROM openjdk:17
COPY target/fouda-store-1.0-SNAPSHOT.jar app.jar
ENV RUN_ENV=''
CMD ["java","-Dspring.profiles.active=${RUN_ENV}","-jar","/app.jar"]