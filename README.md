## Boot
```
(open new terminal)
cd registry-service/
mvn spring-boot:run

(open new terminal)
cd course
mvn spring-boot:run -Dserver.port=9091

(open new terminal)
cd clazz
mvn spring-boot:run -Dserver.port=9092

(open new terminal)
cd calendar
mvn spring-boot:run -Dserver.port=9093

(open new terminal)
cd marketing
mvn spring-boot:run -Dserver.port=9094

(open new terminal)
cd proxy-service
mvn spring-boot:run -Dserver.port=9095

```


## Test
```

 # 과정등록
 http localhost:8080/courses title="software modeling lecture" duration=5 maxEnrollment=5 minEnrollment=1
 
 # 클래스등록
 http localhost:8080/clazzes course="http://localhost:8080/courses/1" # 클래스 1 등록
 http "http://localhost:8080/clazzDays" clazz="http://localhost:8080/clazzes/1" date="2018-03-14"
 http localhost:8080/clazzes course="http://localhost:8080/courses/1"   # 다른 클래스 하나 더 생성
 http "http://localhost:8080/clazzDays" clazz="http://localhost:8080/clazzes/3" date="2012-03-14"
 
 # 강사등록
 http "http://localhost:8080/instructors" firstName="진영" lastName="장"  
 http "http://localhost:8080/instructors" firstName="상철" lastName="황"

 # 강사매핑
 http PATCH "http://localhost:8080/clazzes/1" instructor="http://localhost:8080/instructors/1"  # 장진영 강사에게 첫 클래스를 지정
 http PATCH "http://localhost:8080/clazzes/3" instructor="http://localhost:8080/instructors/1"  # 두번째 클래스는 같은 날에 있기 때문에 장진영 강사 일정이 쫑나서 오류가 날 것.
 http PATCH "http://localhost:8080/clazzes/3" instructor="http://localhost:8080/instructors/2"  # 이 요청은 성공 해야 함. 황상철 강사는 일정이 가능하기 때문에...


```
