package hello;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

public class Customer {

    private Long id;
    private String firstName;
    private String lastName;

    //TODO
    //private List<Enrollment> enrollments;

    public Customer() {}

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

//    public List<Enrollment> getEnrollments() {
//        return enrollments;
//    }
//
//    public void setEnrollments(List<Enrollment> enrollments) {
//        this.enrollments = enrollments;
//    }


}

