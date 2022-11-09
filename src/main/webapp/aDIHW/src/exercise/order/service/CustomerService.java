package exercise.order.service;

import exercise.order.domain.Customer;

public interface CustomerService {

	void saveCustomer(Customer customer);

	void deleteCustomer(long id);
}
