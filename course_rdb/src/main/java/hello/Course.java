package hello;

import org.metaworks.annotation.RestAssociation;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

/**
 * Created by uengine on 2018. 1. 6..
 *
 *
 http localhost:8080/courses title="aaa" duration=5 maxEnrollment=5 minEnrollment=1
 http localhost:8080/clazzes course="http://localhost:8080/courses/1"
 */

@Entity

//@Document
public class Course {

    @Id
    @GeneratedValue
    String id;
    String title;
    int duration;
    String description;
    int maxEnrollment;
    int minEnrollment;
    Double unitPrice;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    //@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "course")
    @RestAssociation(path="/clazzes/search/findByCourseId?courseId={id}", joinColumn = "id") @Transient
    List<Clazz> clazzes;

    public List<Clazz> getClazzes() {
        return clazzes;
    }
    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public int getMinEnrollment() {
        return minEnrollment;
    }

    public void setMinEnrollment(int minEnrollment) {
        this.minEnrollment = minEnrollment;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
