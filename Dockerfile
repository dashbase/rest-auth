FROM openjdk:8

ENV VERSION 1.0.0-SNAPSHOT

WORKDIR /dashbase
ADD target/rest-auth-${VERSION}.jar /dashbase/rest-auth.jar

ENTRYPOINT ["java", "-jar", "/dashbase/rest-auth.jar"]