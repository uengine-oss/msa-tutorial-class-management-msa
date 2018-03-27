package hello;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by uengine on 2018. 1. 27..
 */
@FeignClient("calendar")
public interface SharedCalendarService {

    List<ClazzDay> getSchedules(Date calendar, Instructor instructor);

}
