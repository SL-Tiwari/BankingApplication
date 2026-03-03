package repository;

import domain.Customer;

import java.util.*;

public class CustomerRepository {

    private final Map<String , Customer> customerHashMap = new HashMap<>();

    public String addCustomer(Customer customer) {
        customerHashMap.put(customer.getId(), customer);
        return "Customer added successfully";
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customerHashMap.values());
    }

}
