cd execution

copy ..\gs-administrator\target\gs-administrator-1.0.jar gs-administrator-1.0.jar
copy ..\gs-administrator\src\main\resources\gs-administrator.properties gs-administrator.properties
copy ..\gs-administrator\src\main\resources\gs-administrator-logback.xml gs-administrator-logback.xml

java ^
	-Djava.net.useSystemProxies=true ^
	-Dprogram.name=gs-client ^
	-Dspring.config.location=./gs-administrator.properties ^
	-jar ./gs-administrator-1.0.jar

cd ..
