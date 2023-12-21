FROM openjdk:17-jdk
LABEL authors="kkwjdfo@gmail.com"


WORKDIR ~/spring

COPY build.gradle .

RUN ./gradlew dependencies

COPY . .

RUN ./gradlew build

ENV PORT 8080

EXPOSE ${PORT}

ENV EXE SpringDemo-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "./build/libs/${EXE}"]