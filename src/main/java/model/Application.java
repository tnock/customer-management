package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Thomas Nock
 *
 */
@SpringBootApplication
// @ComponentScan(basePackageClasses = { CustomerController.class,
// Application.class })
// @ComponentScan()
public class Application {

    private static final Logger log = LoggerFactory
            .getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository) {

        return (args) -> {

            customerRepository
                    .save(new Customer("Jack", "Bauer", "bauer@ctu.gov"));
            customerRepository
                    .save(new Customer("Bruce", "Wayne", "bruce@wayne.com"));
            customerRepository
                    .save(new Customer("Evil", "Knevil", "info@evil.org"));
            customerRepository
                    .save(new Customer("Bruce", "Banner", "hulk@avengers.org"));
            customerRepository
                    .save(new Customer("John", "Starks", "starks@nyn.sports"));
            customerRepository.save(
                    new Customer("Charles", "Barkley", "barks@phoenix.suns"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : customerRepository.findAll()) {
                log.info(customer.toString());
            }

            log.info("");

            // fetch an individual customer by ID
            Customer customer = customerRepository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : customerRepository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }
}