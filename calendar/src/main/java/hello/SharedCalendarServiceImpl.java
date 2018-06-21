package hello;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            if(DateUtils.isSameDay(schedule.getDate(), realDate))
                list.add(new Resource<Schedule>(schedule));
        }

        Resources<Resource> halResources = new Resources<Resource>(list);
        //return new ScheduleResource(schedules);

        return halResources;
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


//
//    @KafkaListener(topics = "${kafka.topic.calendar}")
//    public void receive(String payload) throws IOException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        Schedule schedule = objectMapper.readValue(payload, Schedule.class);
//        scheduleRepository.save(schedule);
//    }
}
