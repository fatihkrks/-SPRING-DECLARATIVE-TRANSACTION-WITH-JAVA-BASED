package declarativeTransaction.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import declarativeTransaction.model.Address;

@Repository
public class AddressDAOImpl implements AddressDAO {

	@PersistenceContext
	private EntityManager entityManger;
	
	@Override
	public void insertAddress(Address address) {
		
		entityManger.persist(address);
	}

}
