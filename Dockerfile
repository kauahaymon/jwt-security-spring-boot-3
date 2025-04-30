FROM maven:3.8.8-amazoncorretto-21-al2023 AS build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.6
WORKDIR /app
COPY --from=build ./build/target/*.jar ./ordermanagementapi.jar

EXPOSE 8080

ENV TZ='America/Manaus'
ENTRYPOINT java -jar ordermanagementapi.jar