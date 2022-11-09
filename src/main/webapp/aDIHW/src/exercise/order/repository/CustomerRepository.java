package exercise.order.repository;

import exercise.order.domain.Customer;

public interface CustomerRepository {
	void save(Customer customer);
	void delete(long id);
}
