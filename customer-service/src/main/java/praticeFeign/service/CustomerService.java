package praticeFeign.service;

import praticeFeign.model.Customer;
import praticeFeign.model.request.CustomerRequest;
import java.util.List;

public interface CustomerService {

    Customer insertCustomer(CustomerRequest customerRequest);

    List<Customer> getAllCustomer();

    Customer getCustomerById(long id);

    Customer updateCustomerById(long id, CustomerRequest customerRequest);

    void deleteCustomer(long id);
}
