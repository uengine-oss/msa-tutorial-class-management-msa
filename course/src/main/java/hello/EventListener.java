package hello;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    /** version 1. callback version **/
    @StreamListener(Streams.INPUT)
    public void handleClazz(@Payload Clazz clazz) {
        System.out.println("Received: "+ clazz);
    }


}
