FROM bellsoft/liberica-runtime-container:jdk-17-musl
MAINTAINER tischenko.prg

COPY target/gs-master-1.0.jar gs-master.jar
COPY src/main/resources/gs-master.properties gs-master.properties
COPY src/main/resources/gs-master-logback.xml gs-master-logback.xml

EXPOSE 8083

ENTRYPOINT java -Djava.net.useSystemProxies=true -Dprogram.name=gs-master -Dspring.config.location=./gs-master.properties -jar gs-master.jar
