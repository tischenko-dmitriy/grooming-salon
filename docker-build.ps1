# Set-ExecutionPolicy -ExecutionPolicy Unrestricted -Scope CurrentUser

mvn clean package

cd gs-administrator
docker build --tag gs-administrator .

cd ..\gs-client
docker build --tag gs-client .

cd ..\gs-master
docker build --tag gs-master .

cd ..

docker-compose rm -s -f
docker-compose up -d
