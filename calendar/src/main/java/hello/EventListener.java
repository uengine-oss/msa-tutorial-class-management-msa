package hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Component
@Slf4j
public class EventListener {

    /** version 1. callback version **/
    @StreamListener(Streams.INPUT)
    public void handleClazzDay(@Payload ClazzDay clazzDay) {
        System.out.println("Received greetings: "+ clazzDay.getDate());
    }


}
