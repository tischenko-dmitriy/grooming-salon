cd execution

copy ..\gs-client\target\gs-client-1.0.jar gs-client-1.0.jar
copy ..\gs-client\src\main\resources\gs-client.properties gs-client.properties
copy ..\gs-client\src\main\resources\gs-client-logback.xml gs-client-logback.xml

java ^
	-Djava.net.useSystemProxies=true ^
	-Dprogram.name=gs-client ^
	-Dspring.config.location=./gs-client.properties ^
	-jar ./gs-client-1.0.jar

cd ..
