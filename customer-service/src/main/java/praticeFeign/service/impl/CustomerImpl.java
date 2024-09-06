package praticeFeign.service.impl;
import lombok.AllArgsConstructor;
import praticeFeign.model.Customer;
import praticeFeign.model.request.CustomerRequest;
import org.springframework.stereotype.Service;
import praticeFeign.service.CustomerService;
import praticeFeign.repository.CustomerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer insertCustomer(CustomerRequest customerRequest) {
        Customer customer =new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    public Customer updateCustomerById(long id, CustomerRequest customerRequest) {
        Customer customer=customerRepository.findById(id).orElseThrow();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void deleteCustomer(long id) {
         customerRepository.deleteById(id);
    }

}
