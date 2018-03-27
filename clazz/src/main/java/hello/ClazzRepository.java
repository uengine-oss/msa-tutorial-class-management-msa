package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by uengine on 2018. 1. 6..
 */

public interface ClazzRepository extends PagingAndSortingRepository<Clazz, Long> {

    List<Clazz> findByCourseId(@Param("courseId") Long courseId);
}
