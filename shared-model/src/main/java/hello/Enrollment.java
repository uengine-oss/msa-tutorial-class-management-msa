package hello;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Enrollment {

    @Id
    Long id;

//    Customer customer;
//    Clazz clazz;

    String status;
    String grade;
    String feedback;

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

//    public Clazz getClazz() {
//        return clazz;
//    }
//
//    public void setClazz(Clazz clazz) {
//        this.clazz = clazz;
//    }


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
