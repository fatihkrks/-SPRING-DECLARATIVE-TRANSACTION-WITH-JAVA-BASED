package declarativeTransaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import declarativeTransaction.dao.AddressDAO;
import declarativeTransaction.dao.CustomerDAO;
import declarativeTransaction.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Transactional
	@Override
	public void insertCustomerData(Customer customer) {
		customerDAO.insertCustomer(customer);
		addressDAO.insertAddress(customer.getAddress());
		
	}

	@Override
	public List<Customer> listCustomers() {
		
		return customerDAO.findAllCustomers();
	}

}
