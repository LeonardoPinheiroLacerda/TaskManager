cd api
./mvnw -Dmaven.test.skip spring-boot:build-image
cd ..
docker-compose up -d