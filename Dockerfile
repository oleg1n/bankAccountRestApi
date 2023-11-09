FROM eclipse-temurin:17-jdk-jammy as build

COPY . /opt
WORKDIR /opt

RUN /bin/sh mvnw clean install


FROM eclipse-temurin:17-jdk-jammy

RUN mkdir -p /var/files/bankAccountRestApi

COPY --from=build /opt/target/*.jar /app/

EXPOSE 8080

WORKDIR /app
ENTRYPOINT java $JAVA_OPTS                                                \
   -Dspring.profiles.active=dev                                           \
   -Dserver.port=8080                                                     \
   -DLOGFILE=/app/log/bankAccountRestApi.log                              \
   -Dspring.config.location=file:/app/config/smev-ervu-service.properties \
   -Dlogging.config=/app/config/logback.xml                               \
   -Dfile.encoding=UTF-8                                                  \
   -jar /app/bankAccountRestApi.jar
