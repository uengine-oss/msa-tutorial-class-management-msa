package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by uengine on 2018. 3. 28..
 */
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long>{
    List<Schedule> findByInstructorIdAndDate(@Param("instructorId") Long instructorId, @Param("Date") Date date);
    List<Schedule> findByInstructorId(@Param("instructorId") Long instructorId);
}
