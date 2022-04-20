docker-compose down
docker rmi taskmanager_api:0.0.1-SNAPSHOT
cd api
./mvnw -Dmaven.test.skip spring-boot:build-image
cd ..
docker-compose up -d