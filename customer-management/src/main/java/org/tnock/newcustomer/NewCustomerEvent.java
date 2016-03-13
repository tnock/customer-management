package org.tnock.newcustomer;

import org.tnock.model.Customer;

/**
 * @author Thomas Nock
 *
 */
public class NewCustomerEvent {

    private long customerId;

    private String event;

    private String data;

    /**
     *
     * @param customer
     */
    public NewCustomerEvent(Customer customer) {
        this.customerId = customer.getId();
        this.event = "CREATE";
        this.data = customer.toString();
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewCustomerEvent [customerId=" + customerId + ", event=" + event
                + "]";
    }

}
