mvn clean package
docker build --tag gs-client .
docker run gs-client
