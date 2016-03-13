package org.tnock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerEventLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerEventLogApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(CustomerEventRepository repo) {

        return (args) -> {

            repo.save(new CustomerEvent(34, "CREATE", "Thomas Nock, Berlin"));
            repo.save(
                    new CustomerEvent(48, "CREATE", "Fritz Fischer, Rostock"));
            repo.save(new CustomerEvent(363, "CREATE",
                    "Peter Pan, Castrop-Rauxel"));

            repo.save(new CustomerEvent(63, "UPDATE", "Regine Restapi, Mainz"));
            repo.save(new CustomerEvent(833, "UPDATE", "Alois Aloa, Apenzell"));
            repo.save(new CustomerEvent(456, "UPDATE",
                    "Bertram Borsig, Bottrop"));
            repo.save(new CustomerEvent(2167, "UPDATE", "Karl Kloss, Kassel"));
            repo.save(new CustomerEvent(34, "UPDATE", "Thomas Nock, Berlin"));

            repo.save(new CustomerEvent(34, "DELETE", "Thomas Nock, Berlin"));
        };
    }
}
