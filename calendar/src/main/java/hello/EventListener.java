package hello;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Profile("kafka")
@Component
public class EventListener {

    /** version 1. callback version **/
    @StreamListener(Streams.INPUT)
    @JsonDeserialize(as = ClazzDayRegistered.class)
    public void handleClazzDay(@Payload ClazzDayRegistered clazzDayRegistered) {
        System.out.println("Received: "+ clazzDayRegistered.getDate()); //db 에 저장.
    }

}
