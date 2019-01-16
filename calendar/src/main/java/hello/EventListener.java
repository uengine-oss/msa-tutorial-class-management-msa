package hello;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Profile("kafka")
@Component
public class EventListener {

    @Autowired
    ScheduleRepository scheduleRepository;

    /** version 1. callback version **/
    @StreamListener(Streams.INPUT)
    @JsonDeserialize(as = ClazzDayRegistered.class)
    @Transactional
    public void handleClazzDay(@Payload ClazzDayRegistered clazzDayRegistered) {
        System.out.println("Received: "+ clazzDayRegistered.getDate()); //db 에 저장.

        Schedule schedule = new Schedule();
        schedule.setDate(clazzDayRegistered.getDate());
        schedule.setTitle(clazzDayRegistered.getTitle());
        schedule.setInstructorId(Long.parseLong(clazzDayRegistered.getInstructorId()));

        scheduleRepository.save(schedule);
    }

}
