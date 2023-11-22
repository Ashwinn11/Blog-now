FROM openjdk:17
WORKDIR /temp
COPY .* /temp
ENTRYPOINT ["java","-jar"]