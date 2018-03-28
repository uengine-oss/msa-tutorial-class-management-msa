package hello;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by uengine on 2018. 1. 27..
 */
@FeignClient("calendar")
public interface SharedCalendarService {

    @RequestMapping(method = RequestMethod.GET, path="/calendar/{instructorId}/{date}")
    Resources getSchedules(@PathVariable("instructorId") Long instructorId, @PathVariable("date") String date);
//    ResourceSupport getSchedules(@PathVariable("instructorId") Long instructorId, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") Date date);

}
