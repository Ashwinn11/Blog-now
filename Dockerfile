FROM openjdk:17
WORKDIR /app
COPY target/* /app
ENTRYPOINT ["java","-jar","blog-now.jar"]