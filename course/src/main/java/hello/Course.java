package hello;

import org.metaworks.annotation.RestAssociation;
import org.metaworks.multitenancy.persistence.AfterLoad;
import org.metaworks.multitenancy.persistence.AfterLoadOne;

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
public class Course {

    @Id
    @GeneratedValue
    Long id;
    String title;
    int duration;
    String description;
    int maxEnrollment;
    int minEnrollment;
    Double unitPrice;


    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "course")
    List<Clazz> clazzes;

    @Transient
    Long courseId;
        public Long getCourseId() {
            return courseId;
        }
        public void setCourseId(Long courseId) {
            this.courseId = courseId;
        }


    public List<Clazz> getClazzes() {
        return clazzes;
    }
    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @PostLoad
    public void afterLoad() {
        setCourseId(getId());
    }
}
