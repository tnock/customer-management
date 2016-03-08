package org.tnock.newcustomer;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tnock.model.Customer;

/**
 *
 * @author Thomas Nock
 *
 */
@Controller
public class NewCustomerController {

    private Logger log = LoggerFactory.getLogger(getClass());

    private NewCustomerService newCustomerService;;

    @Autowired
    public NewCustomerController(NewCustomerService newCustomerService) {
        super();
        this.newCustomerService = newCustomerService;
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
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        log.info("New customer: " + customerDto);

        if (bindingResult.hasErrors()) {
            return "newCustomer";
        }

        Customer customer = newCustomerService.createNewCustomer(customerDto);

        redirectAttributes.addFlashAttribute("message",
                "Successfully added customer nr. " + customer.getId());

        return "redirect:/customers";
    }

}
