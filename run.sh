./gradlew bootjar

sudo docker build -f docker/Dockerfile -t our-class-bank-core-api .
sudo docker-compose -f docker/docker-compose.yml up
