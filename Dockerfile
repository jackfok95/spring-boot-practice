FROM openjdk
ENV SPRING_DATASOURCE_URL jdbc:postgresql://postgresdatabase:5432/store
ENV SPRING_DATASOURCE_USERNAME postgres
ENV SPRING_DATASOURCE_PASSWORD postgres
COPY target/store-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]