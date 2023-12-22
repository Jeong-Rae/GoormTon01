FROM openjdk:17-jdk as build
LABEL authors="kkwjdfo@gmail.com"

WORKDIR ~/app

RUN microdnf install findutils

COPY . .

RUN ./gradlew build -x test

FROM openjdk:17-ea-28-jdk-slim as run

WORKDIR /app

COPY --from=build /app/build/libs/SpringDemo-0.0.1-SNAPSHOT.jar /app

CMD ["java","-jar","./build/libs/SpringDemo-0.0.1-SNAPSHOT.jar"]