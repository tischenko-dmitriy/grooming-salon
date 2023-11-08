FROM bellsoft/liberica-runtime-container:jdk-17-musl
MAINTAINER tischenko.prg

COPY target/gs-client-1.0.jar gs-client.jar
COPY src/main/resources/gs-client.properties gs-client.properties
COPY src/main/resources/gs-client-logback.xml gs-client-logback.xml

EXPOSE 8081
EXPOSE 18081

ENTRYPOINT java -Djava.net.useSystemProxies=true -Dprogram.name=gs-client -Dspring.config.location=./gs-client.properties -jar gs-client.jar
