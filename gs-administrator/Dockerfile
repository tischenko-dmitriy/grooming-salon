FROM bellsoft/liberica-runtime-container:jdk-17-musl
MAINTAINER tischenko.prg

COPY target/gs-administrator-1.0.jar gs-administrator.jar
COPY src/main/resources/gs-administrator.properties gs-administrator.properties
COPY src/main/resources/gs-administrator-logback.xml gs-administrator-logback.xml

EXPOSE 8082

ENTRYPOINT java -Djava.net.useSystemProxies=true -Dprogram.name=gs-administrator -Dspring.config.location=./gs-administrator.properties -jar gs-administrator.jar
