package declarativeTransaction.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import declarativeTransaction.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String ALL_CUSTOMER="Select c from Customer c";
	
	@Override
	public void insertCustomer(Customer customer) {
		entityManager.persist(customer);
		
	}

	@Override
	public List<Customer> findAllCustomers() {
		TypedQuery<Customer> query=entityManager.createQuery(ALL_CUSTOMER,Customer.class);
		return query.getResultList();
		
	}

}
