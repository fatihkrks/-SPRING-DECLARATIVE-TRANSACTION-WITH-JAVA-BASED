package declarativeTransaction.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import declarativeTransaction.model.Address;
import declarativeTransaction.model.Customer;
import declarativeTransaction.service.CustomerService;

public class DeclarativeTransactionxml {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"declarativetransactionxml.xml");

		// get CustomerService bean
		CustomerService customerService = context.getBean(CustomerService.class);

		// prepare Customer and Address data.
		Customer customer = new Customer(1, "Fatih", "Karakas");
		Address address = new Address(1, "bagcilar Street", "34000", "Istanbul");
		customer.setAddress(address);

		//
		Customer customer2 = new Customer(2, "Berre", "Karakas");
		Address address2 = new Address(2, "Esenler Street", "06000", "İstanbul");
		customer2.setAddress(address2);

		Customer customer3 = new Customer(3, "İrem", "Simsek");
		// duplicate Address id , throw exception
		// customers3 record will be rollbacked too.
		Address address3 = new Address(2, "Sivas street", "34000", "Sivas");
		customer3.setAddress(address3);

		customerService.insertCustomerData(customer);
		customerService.insertCustomerData(customer2);

		try {
			customerService.insertCustomerData(customer3);
		} catch (Exception e) {
			System.out.println("Rollback...");
			System.out.println(e.getMessage());
		}

		//
		System.out.println("Customer List : ");
		for (Customer customerRecord : customerService.listCustomers()) {
			System.out.println(customerRecord);
		}

		context.close();
	}
}
