FROM openjdk:17-oracle
ENV APP_FILE bankAccountRestApi-0.0.1-SNAPSHOT.jar
ENV APP_HOME /opt/bank-rest-api
WORKDIR $APP_HOME
EXPOSE 8087
COPY target/*.jar $APP_HOME/
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar $APP_FILE"]