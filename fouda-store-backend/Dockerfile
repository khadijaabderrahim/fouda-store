FROM openjdk:17
ARG BUILD_VERSION
COPY target/fouda-store-${BUILD_VERSION}.jar app.jar
ENV RUN_ENV=''
CMD ["java","-Dspring.profiles.active=${RUN_ENV}","-jar","/app.jar"]