package model;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Thomas Nock
 *
 */
@Controller
public class NewCustomerController {

    private Logger log = LoggerFactory.getLogger(getClass());

    private CustomerRepository customerRepository;

    @Autowired
    public NewCustomerController(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    @ModelAttribute("customer")
    public CustomerDto init() {
        return new CustomerDto();
    }

    @ModelAttribute("countryCodes")
    public List<String> initCountryCodes() {

        return Arrays.asList("DE", "UK", "NO", "FR", "ES", "DK");
    }

    @RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
    public String createCustomerInit() {

        return "newCustomer";
    }

    @RequestMapping(value = "/newCustomer", method = RequestMethod.POST)
    public String createCustomer(
            @Valid @ModelAttribute("customer") CustomerDto customerDto,
            BindingResult bindingResult) {

        log.info("New customer: " + customerDto);

        if (bindingResult.hasErrors()) {
            return "newCustomer";
        }

        Customer customer = convert(customerDto);

        customerRepository.save(customer);

        return "redirect:/customers";
    }

    private Customer convert(CustomerDto customerDto) {

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

        return customer;
    }

}
