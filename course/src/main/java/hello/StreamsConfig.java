package hello;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(Streams.class)
public class StreamsConfig {

    /** version 2. stream version **/
//    @StreamListener(Streams.INPUT)
//    //@JsonDeserialize(as = ClazzDay.class)
//    public void handleClazzDay(Flux<String> clazzDayFlux) {
//
//        System.out.println("Invocation has been come.");
//
//        clazzDayFlux
//                .log()
//                .subscribeOn(Schedulers.elastic())
//                .subscribe(clazzDay -> {
//                    System.out.println("Received greetings: "+ clazzDay);
//                });
//    }

}

