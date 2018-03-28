package hello;

import org.metaworks.multitenancy.persistence.MultitenantRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by uengine on 2018. 1. 6..
 */

public interface ClazzRepository extends MultitenantRepository<Clazz, Long> {

    List<Clazz> findByCourseId(@Param("courseId") Long courseId);
}
