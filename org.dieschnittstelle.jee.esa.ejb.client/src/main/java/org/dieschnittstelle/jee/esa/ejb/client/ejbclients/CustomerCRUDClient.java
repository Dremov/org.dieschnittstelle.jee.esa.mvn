package org.dieschnittstelle.jee.esa.ejb.client.ejbclients;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.crud.CustomerCRUDRemote;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.ejb.client.Constants;

public class CustomerCRUDClient implements CustomerCRUDRemote {

	private CustomerCRUDRemote ejbProxy;

	public CustomerCRUDClient() throws Exception {
		Context context = new InitialContext();

		ejbProxy = (CustomerCRUDRemote) context
				.lookup(Constants.CUSTOMER_CRUD_BEAN);
	}

	@Override
	public Customer readCustomerForEmail(String email) {
		return ejbProxy.readCustomerForEmail(email);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		Customer created = ejbProxy.createCustomer(customer);
		
		// as a side-effect, we set the id on the customer object
		customer.setId(created.getId());
		
		return created;
	}

	@Override
	public Customer readCustomer(long id) {
		return ejbProxy.readCustomer(id);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return ejbProxy.updateCustomer(customer);
	}

	@Override
	public Customer updateCustomerWithSleep(Customer customer, long sleep) {
		return ejbProxy.updateCustomerWithSleep(customer, sleep);
	}

	@Override
	public boolean deleteCustomer(int id) {
		return ejbProxy.deleteCustomer(id);
	}

}
