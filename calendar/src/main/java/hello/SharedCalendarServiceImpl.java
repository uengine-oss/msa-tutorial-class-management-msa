package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by uengine on 2018. 1. 27..
 */
@RestController
public class SharedCalendarServiceImpl implements SharedCalendarService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    @RequestMapping(method = RequestMethod.GET, path="/calendar/{instructorId}/{date}")
    public Resources<Resource> getSchedules(@PathVariable("instructorId") Long instructorId, @PathVariable("date") String date) {
        Date realDate = convertToDate(date);

        List<Schedule> schedules = scheduleRepository.findByInstructorId(instructorId);

        List<Resource> list  = new ArrayList<Resource>();

        for(Schedule schedule : schedules) {
            if(DateUtils.isSameDay(schedule.getDate(), realDate)){
                Resource<Schedule> resource = new Resource<Schedule>(schedule);
                resource.add(linkTo(methodOn(SharedCalendarServiceImpl.class).getSchedules(instructorId, date)).withSelfRel());
                resource.add(linkTo(methodOn(SharedCalendarServiceImpl.class).delaySchedule(instructorId, date, 0)).withRel("delay"));
                //new Link("http://localhost:8180/" + instructorId + "/" + date, "_self"));
                list.add(resource);
            }

        }

        Resources<Resource> halResources = new Resources<Resource>(list);
        //return new ScheduleResource(schedules);

        return halResources;
    }

    @RequestMapping(method = RequestMethod.POST, path="/calendar-schedule/{date}/{instructorId}/delay")
    public Resources<Resource> delaySchedule(@PathVariable("instructorId") Long instructorId, @PathVariable("date") String date, @RequestParam("delayDays") int days) {

        Resources<Resource> schedules = getSchedules(instructorId, date);

        //TODO: 1일씩 연기

        return schedules;



    }

    //TODO due to feign client's bug, we had to convert string to date
    private Date convertToDate(String dateStr) {

        Date date = new Date();

        if(dateStr.trim().indexOf(" ") > -1) dateStr = dateStr.split(" ")[0];
        String[] yearMonthDate = dateStr.split("-");

        if(yearMonthDate.length < 3) throw new IllegalArgumentException();

        date.setYear(Integer.parseInt(yearMonthDate[0])-1900);
        date.setMonth(Integer.parseInt(yearMonthDate[1])-1);
        date.setDate(Integer.parseInt(yearMonthDate[2]));
        date.setMinutes(0);
        date.setSeconds(0);

        return date;
    }




}
