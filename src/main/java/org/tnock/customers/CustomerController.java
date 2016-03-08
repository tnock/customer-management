package org.tnock.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tnock.model.Customer;
import org.tnock.model.CustomerRepository;

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

    @ModelAttribute("customerSearchForm")
    public CustomerSearchForm initForm() {
        return new CustomerSearchForm();
    }

    /**
     * @return view name of matching thymeleaf template
     */
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomers(Model model) {

        model.addAttribute("customers", customerRepository.findAll());

        return "customers"; // customers.html
    }

    /**
     * @return view name of matching thymeleaf template
     */
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String searchCustomers(
            @ModelAttribute("customerSearchForm") CustomerSearchForm form,
            Model model) {

        if (form.getEmail() != null && !"".equals(form.getEmail())) {

            List<Customer> result = customerRepository
                    .findByEmailContainingIgnoreCase(form.getEmail());
            model.addAttribute("customers", result);
        }
        else {
            model.addAttribute("customers", customerRepository.findAll());
        }

        return "customers"; // customers.html
    }
}
