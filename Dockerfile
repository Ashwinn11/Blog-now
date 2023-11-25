FROM openjdk:17
WORKDIR /app
COPY target/blog-now.jar /app
ENTRYPOINT ["java","-jar","blog-now.jar"]