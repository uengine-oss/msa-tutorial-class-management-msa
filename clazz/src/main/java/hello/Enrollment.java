package hello;

import org.metaworks.annotation.RestAssociation;

import javax.persistence.*;

/**
 * Created by uengine on 2018. 3. 19..
 *

 http localhost:8080/courses title="aaa" duration=5 maxEnrollment=5 minEnrollment=1
 http localhost:8080/clazzes course="http://localhost:8080/courses/1"
 http localhost:8080/customers firstName="jang" lastName="rick"
 http localhost:8080/enrollments clazz="http://localhost:8080/clazzes/2" customer="http://localhost:8080/customers/3"  #수강예약

 http "http://localhost:8080/customers/3/enrollments" #수강 예약 상태
 http PATCH "http://localhost:8080/enrollments/4" status="approved"  #수강승인상태

 */
@Entity
public class Enrollment {
    @Id
    @GeneratedValue
    Long id;

    @RestAssociation(/*serviceId = "customer", */path="/customers/{customerId}", joinColumn = "customerId") @Transient //@OneToOne
    Customer customer;

    Long customerId;
        public Long getCustomerId() {
            return customerId;
        }
        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }



    @OneToOne
    Clazz clazz;

    String status;
    String grade;
    String feedback;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
