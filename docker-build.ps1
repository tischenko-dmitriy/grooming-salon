# Set-ExecutionPolicy -ExecutionPolicy Unrestricted -Scope CurrentUser

mvn clean package

cd gs-client
docker build --tag gs-client .
docker run --rm --name gs-client -p 8081:8081 gs-client:latest

cd ..\gs-administrator
docker build --tag gs-administrator .
docker run --rm --name gs-administrator -p 8082:8082 gs-administrator:latest

cd ..\gs-master
docker build --tag gs-master .
docker run --rm --name gs-master -p 8083:8083 gs-master:latest

cd ..
