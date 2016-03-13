package org.tnock.newcustomer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Thomas Nock
 */
@Service
public class CustomerEventPublisher {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Public event
     * @param event
     */
    @HystrixCommand(fallbackMethod = "publishFallback")
    public void publishNewCustomer(NewCustomerEvent event) {
        Class<Void> responseType = null;

        // use the "smart" Eureka-aware RestTemplate
        ResponseEntity<Void> postResult = restTemplate.postForEntity(
                "http://customer-event-log/events", event, responseType);

        logger.info(
                "NewCustomer event published for customer event {}. Result: {}",
                event, postResult);
    }

    /**
     * Fallback, when {@link #publishNewCustomer(NewCustomerEvent)} fails.
     * @param event
     */
    public void publishFallback(NewCustomerEvent event) {
        logger.warn("Publishing failed for event: {}", event);
    }
}
