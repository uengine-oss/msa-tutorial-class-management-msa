package hello;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by uengine on 2018. 1. 27..
 */
@FeignClient("calendar")
public interface SharedCalendarService {

    @RequestMapping(method = RequestMethod.GET, path="/instructors/{instructorId}/schedule/{date}")
    ResourceSupport getSchedules(@PathVariable("instructorId") Long instructorId, @PathVariable("dateStr") String dateStr);

}
