package hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.metaworks.annotation.RestAssociation;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.eventsourcing.EventSender;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by uengine on 2018. 1. 27..
 */
@Entity
public class ClazzDay {

    @Id
    @GeneratedValue
    Long id;
    Date date;
    int startTime;
    int endTime;

    @ManyToOne @JoinColumn(name="cid")
    Clazz clazz;

    @RestAssociation(path="/instructors/{iid}", joinColumn = "iid") @Transient //@ManyToOne @JoinColumn(name="iid")
    Instructor instructor;

    /*** dummy ***/
    Long iid;
        public Long getIid() {
            return iid;
        }
        public void setIid(Long iid) {
            this.iid = iid;
        }
    /*** end of dummy **/

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    @PrePersist
    public void publishEvent(){

        ObjectMapper objectMapper = new ObjectMapper();

        EventSender eventSender = MetaworksRemoteService.getComponent(EventSender.class);

        try {
            eventSender.sendBusinessEvent(objectMapper.writeValueAsString(this));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while sending event", e);
        }

    }
}
