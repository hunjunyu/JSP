package exercise.order.service;

import exercise.order.domain.Customer;
import exercise.order.repository.CustomerRepository;


public class CustomerServiceImpl  implements CustomerService {

	
	private CustomerRepository repository;


	@Override
	public void saveCustomer(Customer customer) {
		repository.save(customer);
	}


	@Override
	public void deleteCustomer(long id) {
		repository.delete(id);
	}
}
