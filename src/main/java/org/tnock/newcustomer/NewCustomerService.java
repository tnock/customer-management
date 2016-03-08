package org.tnock.newcustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnock.model.Customer;
import org.tnock.model.CustomerRepository;

/**
 *
 * @author Thomas Nock
 *
 */
@Service
public class NewCustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public NewCustomerService(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

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

        return customer;

    }
}
