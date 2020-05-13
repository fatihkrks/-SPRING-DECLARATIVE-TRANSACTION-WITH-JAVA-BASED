package declarativeTransaction.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import declarativeTransaction.configuration.ConfigurationJPA;
import declarativeTransaction.model.Address;
import declarativeTransaction.model.Customer;
import declarativeTransaction.service.CustomerService;

public class DeclarativeTransactionAnnotation {
public static void main(String[] args) {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationJPA.class);

	// get CustomerService bean
	CustomerService customerService = context.getBean(CustomerService.class);

	// prepare Customer and Address data.
	Customer customer = new Customer(1, "Levent", "Erguder");
	Address address = new Address(1, "Java Street", "34000", "Istanbul");
	customer.setAddress(address);

	//
	Customer customer2 = new Customer(2, "Orcun", "Erpis");
	Address address2 = new Address(2, "Bakanliklar Street", "06000", "Ankara");
	customer2.setAddress(address2);

	Customer customer3 = new Customer(3, "Hakan", "Gencel");
	// duplicate Address id , throw exception
	// customers3 record will be rollbacked too.
	Address address3 = new Address(2, "Alemdag Road", "34000", "Istanbul");
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
