FROM maven:3.9.16-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B package -DskipTests

FROM tomee:10.1.4-jre21-Temurin-plume

RUN rm -rf /usr/local/tomee/webapps/*

COPY --from=build /workspace/target/co2Daten /usr/local/tomee/webapps/Projektarbeit
COPY docker/tomee/resources.xml /usr/local/tomee/webapps/Projektarbeit/WEB-INF/resources.xml

EXPOSE 8080

CMD ["catalina.sh", "run"]
