package org.dieschnittstelle.jee.esa.ejb.client.ejbclients;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.TouchpointAccessRemote;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.ejb.client.Constants;

public class TouchpointAccessClient implements TouchpointAccessRemote {
	
	private TouchpointAccessRemote ejbProxy;
	
	public TouchpointAccessClient() throws Exception {
		Context context = new InitialContext();
		this.ejbProxy = (TouchpointAccessRemote) context.lookup(Constants.TOUCHPOINT_ACCESS_BEAN);
	}
	
	
	public List<AbstractTouchpoint> readAllTouchpoints() {
		return ejbProxy.readAllTouchpoints();
	}

	@Override
	public AbstractTouchpoint createTouchpoint(AbstractTouchpoint touchpoint) {
		AbstractTouchpoint created = ejbProxy.createTouchpoint(touchpoint);
		touchpoint.setId(created.getId());
		touchpoint.setErpPointOfSaleId(created.getErpPointOfSaleId());
		
		return created;
	}
		
}
