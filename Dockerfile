FROM maven:3.8.5-openjdk-17 as build

COPY . /var/docker/compose/
WORKDIR /var/docker/compose/

RUN mvn clean package

FROM openjdk-17

COPY --from=build /var/docker/compose/target/*.jar /app/bank-account-rest-api.jar

EXPOSE 8080

WORKDIR /app
ENTRYPOINT java $JAVA_OPTS                                                \
   -Dspring.profiles.active=dev                                           \
   -Dserver.port=8080                                                     \
   -DLOGFILE=/app/log/bank-account-rest-api.log                            \
   -Dfile.encoding=UTF-8                                                  \
   -jar /app/bank-account-rest-api.jar


