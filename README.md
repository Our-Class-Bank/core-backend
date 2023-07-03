# core-backend

### environment

- spring boot 3.1.0
- kotlin 1.8.21 with java 17

### how to run

```shell
# 1. run docker mysql
$ ./run_db.sh

# 2. check docker mysql
$ docker ps
CONTAINER ID IMAGE COMMAND CREATED STATUS PORTS NAMES
73ada97df8d1 mysql:8.0.33 "docker-entrypoint.s…" About a minute ago Up 43 seconds 0.0.0.0:3306- 33060/tcp our-class-bank-mysql >3306/tcp,

# 3. build application
$ ./gradlew bootjar
BUILD SUCCESSFUL in 967ms

$ ls -lrt api/core-api/build/libs/core-api-0.0.1-SNAPSHOT.jar
-rw-r--r--  1 mk-mac-041  staff  45311389  6 18 14:30 api/core-api/build/libs/core-api-0.0.1-SNAPSHOT.jar


# 4. run application
$ java -jar -Dspring.profiles.active=local api/core-api/build/libs/core-api-0.0.1-SNAPSHOT.jar
2023-06-18T14:42:14.714+09:00  INFO 48213 --- [           main] c.o.coreapi.CoreApiApplicationKt         : The following 1 profile is active: "local"
2023-06-18T14:42:16.150+09:00  INFO 48213 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-06-18T14:42:16.160+09:00  INFO 48213 --- [           main] c.o.coreapi.CoreApiApplicationKt         : Started CoreApiApplicationKt in 1.741 seconds (process running for 1.998)
```

- local swagger [link](http://localhost:8080/swagger-ui/index.html#/)

## worklog

- [x] [milestone1 jwt 기반 회원 기능 개발](https://github.com/Our-Class-Bank/core-backend/wiki/milestone1---jwt-%EA%B8%B0%EB%B0%98-%ED%9A%8C%EC%9B%90-%EA%B8%B0%EB%8A%A5-%EA%B0%9C%EB%B0%9C)
- [x] [milestone2 회원 고도화 & 반 개념 추가](https://github.com/Our-Class-Bank/core-backend/wiki/milestone2---%ED%9A%8C%EC%9B%90-%EA%B3%A0%EB%8F%84%ED%99%94-&-%EB%B0%98-%EA%B0%9C%EB%85%90-%EC%B6%94%EA%B0%80)
