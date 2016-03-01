package model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Thomas Nock
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    /**
     * "where email like ?1"
     */
    List<Customer> findByEmailContainingIgnoreCase(String email);
}
