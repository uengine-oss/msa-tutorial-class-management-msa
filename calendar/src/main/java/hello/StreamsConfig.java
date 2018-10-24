package hello;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@EnableBinding(Streams.class)
public class StreamsConfig {

    /** version 2. stream version **/
    @StreamListener(Streams.INPUT)
    //@JsonDeserialize(as = ClazzDay.class)
    public void handleClazzDay(Flux<String> clazzDayFlux) {

        System.out.println("Invocation has been come.");

        clazzDayFlux
                .log()
                .subscribeOn(Schedulers.elastic())
                .subscribe(clazzDay -> {
                    System.out.println("Received greetings: "+ clazzDay);
                });
    }

}

