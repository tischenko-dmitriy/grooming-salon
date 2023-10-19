FROM openjdk:17
MAINTAINER tischenko.prg
COPY target/gs-client-1.0.jar gs-client.jar
COPY src/main/resources/gs-client.properties gs-client.properties
COPY src/main/resources/gs-client-logback.xml gs-client-logback.xml

ENTRYPOINT java -Djava.net.useSystemProxies=true -Dprogram.name=gs-client -Dspring.config.location=./gs-client.properties -jar gs-client.jar
