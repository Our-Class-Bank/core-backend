./gradlew bootjar

sudo docker build -f docker/Dockerfile -t our-class-bank-server .
sudo docker-compose -f docker/docker-compose.yml --env-file docker/.env.prod up
