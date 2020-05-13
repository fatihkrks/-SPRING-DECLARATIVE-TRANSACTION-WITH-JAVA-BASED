package declarativeTransaction.dao;

import java.util.List;

import declarativeTransaction.model.Customer;

public interface CustomerDAO {

	public void insertCustomer(Customer customer);
	
	public List<Customer> findAllCustomers();
}
