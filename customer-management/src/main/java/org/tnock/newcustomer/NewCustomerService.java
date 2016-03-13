package org.tnock.newcustomer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnock.customers.CustomerRepository;
import org.tnock.model.Customer;

/**
 * Handle creation of a new customer. Store her in DB and publish the
 * appropriate "create event".
 * @author Thomas Nock
 */
@Service
public class NewCustomerService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private CustomerRepository customerRepository;

    private CustomerEventPublisher customerEventPublisher;

    @Autowired
    public NewCustomerService(CustomerRepository customerRepository,
            CustomerEventPublisher customerEventPublisher) {
        super();
        this.customerRepository = customerRepository;
        this.customerEventPublisher = customerEventPublisher;
    }

    /**
     *
     * @param customerDto
     * @return
     */
    public Customer createNewCustomer(CustomerDto customerDto) {

        Customer customer = new Customer();

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());

        customer.setEmail(customerDto.getEmail());
        customer.setCompanyName(customerDto.getCompanyName());
        customer.setPhone(customerDto.getPhone());

        customer.setStreet(customerDto.getStreet());
        customer.setZipCode(customerDto.getZipCode());
        customer.setCity(customerDto.getCity());
        customer.setCountryCode(customerDto.getCountryCode());

        customerRepository.save(customer);
        logger.info("Customer saved: {}", customer);


        // -- event handling --
        NewCustomerEvent event = new NewCustomerEvent(customer);
        customerEventPublisher.publishNewCustomer(event);

        return customer;

    }
}
