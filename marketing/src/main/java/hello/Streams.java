package hello;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Streams {
    String INPUT = "class-topic";

    @Input(INPUT)
    SubscribableChannel inboundGreetings();

}
