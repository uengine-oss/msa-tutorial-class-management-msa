package hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.metaworks.annotation.RestAssociation;
import org.metaworks.dwr.MetaworksRemoteService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.concurrent.ListenableFuture;

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
    public void publishEvent() throws JsonProcessingException {

        /** version1. Using general Kafka API **/
//        KafkaTemplate kafkaTemplate = Application.getApplicationContext().getBean(KafkaTemplate.class);
//        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send("bpm.topic", payload);



        /** version 2. Using Reactive Kafka API **/

//        EventProducer eventProducer = Application.getApplicationContext().getBean(EventProducer.class);
//        try {
//            eventProducer.sendMessages("bpm.topic", 10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        /** version 3. Using Spring Cloud's Reactive Streams Kafka API **/

        Streams streams = Application.getApplicationContext().getBean(Streams.class);

        if(streams==null) return;

        MessageChannel messageChannel = streams.outboundChannel();
        messageChannel.send(MessageBuilder
                .withPayload(new ClazzDayRegistered(this))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

        System.out.println("Event published");

    }
}
