FROM amazoncorretto:21

WORKDIR /app


COPY target/dbcontrollers-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
# Use the following command to build the Docker image:
# docker build -t api_rest:latest .
# Use the following command to run the Docker container:
# docker run -p 8080:8080 api_rest:latest