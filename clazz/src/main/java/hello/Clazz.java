package hello;

import org.metaworks.annotation.RestAssociation;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import javax.persistence.*;
import java.util.List;

/**
 * Created by uengine on 2018. 1. 6..
 *
 * create table clazz(
 *    id primary key,
 *    states int,
 *    cid references (Course(id)),
 * )
 *
 */
@Entity
public class Clazz{

    @Id
    @GeneratedValue
    Long id;
    String states;
    int evaluationRate;

    String title;

    @RestAssociation(/*serviceId = "course", */path="/courses/{courseId}", joinColumn = "courseId") @Transient
    //@ManyToOne @JoinColumn(name="cid")
    Course course;

    @RestAssociation(path="/instructors/{iid}", joinColumn = "iid") @Transient //@ManyToOne @JoinColumn(name="iid")
    Instructor instructor;

    @OneToMany(mappedBy = "clazz")
    List<ClazzDay> clazzDays;
        public List<ClazzDay> getClazzDays() {
            return clazzDays;
        }
        public void setClazzDays(List<ClazzDay> clazzDays) {
            this.clazzDays = clazzDays;
        }


    /*** dummy ***/
    Long courseId;
        public Long getCourseId() {
            return courseId;
        }
        public void setCourseId(Long courseId) {
            this.courseId = courseId;
        }

    Long iid;
        public Long getIid() {
            return iid;
        }
        public void setIid(Long iid) {
            this.iid = iid;
        }
    /*** end of dummy **/


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public int getEvaluationRate() {
        return evaluationRate;
    }

    public void setEvaluationRate(int evaluationRate) {
        this.evaluationRate = evaluationRate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    @PrePersist @PreUpdate
    public void beforeSave() {

        checkAvailabilityAndSetInstructor();

    }

    private void checkAvailabilityAndSetInstructor() {
        if(getInstructor()!=null){
            SharedCalendarService sharedCalendarService = Application.applicationContext.getBean(SharedCalendarService.class);

            if(getClazzDays()==null)
                throw new RuntimeException("Instructor can be mapped after adding class days for this class.");

            for(ClazzDay clazzDay : getClazzDays()){

                ResourceSupport schedules = sharedCalendarService.getSchedules(getInstructor().getId(), clazzDay.getDate().toString());
//                ResourceSupport schedules = sharedCalendarService.getSchedules(getInstructor().getId(), clazzDay.getDate());


                if(schedules!=null && ((Resources)schedules).getContent().iterator().hasNext()){
                    throw new RuntimeException("Instructor " + getInstructor().getId() + " already have another class for that time slot.");
                }

            }

            //set instructor if available time slot
            for(ClazzDay clazzDay : getClazzDays()){

                clazzDay.setInstructor(getInstructor());

            }

        }
    }
}
