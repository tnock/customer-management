package org.tnock.customers;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.tnock.model.Customer;

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
