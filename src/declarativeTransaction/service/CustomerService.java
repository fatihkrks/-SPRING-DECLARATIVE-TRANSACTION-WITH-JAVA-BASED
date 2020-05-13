package declarativeTransaction.service;

import java.util.List;

import declarativeTransaction.model.Customer;

public interface CustomerService {
	public void insertCustomerData(Customer customer);
	public List<Customer> listCustomers();
}
