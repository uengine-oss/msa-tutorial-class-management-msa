package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by uengine on 2018. 1. 6..
 *

 http localhost:8080/courses title="software modeling lecture" duration=5 maxEnrollment=5 minEnrollment=1
 http "http://localhost:8080/instructors" firstName="진영" lastName="장"
 http localhost:8080/clazzes course="http://localhost:8080/courses/1" # 클래스 1 등록
 http "http://localhost:8080/clazzDays" clazz="http://localhost:8080/clazzes/3" date="2012-04-23T18:25:43.511Z"
 http localhost:8080/clazzes course="http://localhost:8080/courses/1"   # 다른 클래스 하나 더 생성
 http "http://localhost:8080/clazzDays" clazz="http://localhost:8080/clazzes/5" date="2012-04-23T18:25:43.511Z"
 http PATCH "http://localhost:8080/clazzes/3" instructor="http://localhost:8080/instructors/2"  # 장진영 강사에게 첫 클래스를 지정
 http PATCH "http://localhost:8080/clazzes/5" instructor="http://localhost:8080/instructors/2"  # 두번째 클래스는 같은 날에 있기 때문에 장진영 강사 일정이 쫑나서 오류가 날 것.
 http "http://localhost:8080/instructors" firstName="상철" lastName="황"
 http PATCH "http://localhost:8080/clazzes/5" instructor="http://localhost:8080/instructors/8"  # 이 요청은 성공 해야 함. 황상철 강사는 일정이 가능하기 때문에...

 *
 *
 */

@Entity
public class Instructor {

    @Id @GeneratedValue
    Long id;
    String firstName;
    String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
