FROM eclipse-temurin:21.0.1_12-jre
ARG JAR_FILE=heroes.jar
ARG WORKDIR_PATH=/app
WORKDIR ${WORKDIR_PATH}
COPY ${JAR_FILE} ${JAR_FILE}
ENTRYPOINT ["java", "-jar", "/app/heroes.jar"]