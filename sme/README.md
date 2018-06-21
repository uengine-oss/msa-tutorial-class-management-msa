# 실행방법

```
 mvn spring-boot:run
```

# 테스트

```
http localhost:8080/courses title="aaa" duration=5 maxEnrollment=5 minEnrollment=1

http localhost:8080/clazzes course="http://localhost:8080/courses/1"

http "http://localhost:8080/courses/1/clazzes"
```

# HTTPie 가 없는 경우

- 맥

> brew install httpie

- 설치 방법: https://github.com/TheOpenCloudEngine/uEngine-cloud/wiki/Httpie-%EC%84%A4%EC%B9%98
