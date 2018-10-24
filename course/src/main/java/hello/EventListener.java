package hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListener {

    /** version 1. callback version **/
    @StreamListener(Streams.INPUT)
    public void handleClazz(@Payload Clazz clazz) {
        System.out.println("Received: "+ clazz);
    }


}
