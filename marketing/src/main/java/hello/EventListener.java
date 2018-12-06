package hello;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    /** version 1. callback version **/
    @StreamListener(Streams.INPUT)
    @JsonDeserialize(as = ClazzDayRegistered.class)
    public void handleClazzDay(@Payload ClazzDayRegistered clazzDayRegistered) {
        System.out.println("제목 : [마감임박] "+ clazzDayRegistered.getTitle() + " 강의가 개설되었습니다"); //db 에 저장.
        System.out.println("내용 : 일시: "+ clazzDayRegistered.getDate()); //db 에 저장.
        System.out.println("*** 기념품 증정 ***: ");
    }

}
