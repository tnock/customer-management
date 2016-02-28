package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author Thomas Nock
 *
 */
@Controller
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController() {
        super();
    }

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    @ModelAttribute("customers")
    public Iterable<Customer> findAllCustomers() {

        return customerRepository.findAll();
    }

    /**
     * @return view name of matching thymeleaf template
     */
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomers() {

        return "customers"; // customers.html
    }
}
