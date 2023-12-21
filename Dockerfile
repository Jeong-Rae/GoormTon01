FROM openjdk:17-jdk
LABEL authors="kkwjdfo@gmail.com"



WORKDIR ~/spring

RUN microdnf install findutils

COPY . .

RUN ./gradlew build -x test

ENV PORT 8080

EXPOSE ${PORT}

ENV EXE SpringDemo-0.0.1-SNAPSHOT.jar

CMD java -jar "./build/libs/${EXE}"