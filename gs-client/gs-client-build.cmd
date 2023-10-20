mvn clean package
docker build --tag gs-client .
docker run --rm --name gs-client -p 8081:8081 -p 18081:18081 gs-client:latest
