FROM amazoncorretto:16 AS app-build

ENV GRADLE_OPTS -Dorg.gradle.daemon=false

RUN ls -R

COPY . /build
WORKDIR /build

RUN ls -R

ENTRYPOINT ["java", "-cp" "./api/src/main/java/cz/cvut/fit/sp/botbuilder/BotBuilderApplication.java", "-Dserver.port=$PORT", "$JAVA_OPTS", "-jar", "build/libs/bot_builder-0.0.1-SNAPSHOT.jar"]

RUN ./gradlew build