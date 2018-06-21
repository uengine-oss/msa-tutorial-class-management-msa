package hello;

import java.util.List;

import org.metaworks.multitenancy.persistence.MultitenantRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends MultitenantRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
