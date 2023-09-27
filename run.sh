./gradlew bootjar

#export DATABASE_URL="__FILL_ME__"
#export DATABASE_USERNAME="__FILL_ME__"
#export DATABASE_PASSWORD="__FILL_ME__"
#export ENV="__FILL_ME__"

#mkdir log
#nohup ./run.sh > log/nohup.log 2>&1 < /dev/null &

java -jar -Dspring.profiles.active="$ENV" api/core-api/build/libs/core-api-0.0.1-SNAPSHOT.jar
