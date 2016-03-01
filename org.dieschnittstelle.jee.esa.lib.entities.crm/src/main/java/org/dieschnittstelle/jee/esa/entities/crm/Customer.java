package org.dieschnittstelle.jee.esa.entities.crm;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.builder.EqualsBuilder;

/*
 * 
 */
@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7461272049473919251L;

	/*
	 * UE: move the annotation to the getter
	 */
	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private Gender gender;

	private String firstName;

	private String lastName;

	private String mobilePhoneId;

	private String email;

	@ManyToOne(cascade={CascadeType.ALL})
	private Address address;

	@ManyToMany(mappedBy="customers")
	private Collection<AbstractTouchpoint> touchpoints = new HashSet<AbstractTouchpoint>();

	@ManyToOne
	private AbstractTouchpoint preferredTouchpoint;
	
	/*
	 * UE JPA1.2 
	 */
	@OneToMany(mappedBy="customer", fetch=FetchType.LAZY)
	//@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
	private Collection<CustomerTransaction> transactions;
	
	public void addTouchpoint(AbstractTouchpoint touchpoint) {
		this.touchpoints.add(touchpoint);
	}
	
	public Customer() {
		System.out.println("<constructor>");	
	}

	public Customer(String firstName, String lastName, Gender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public Customer(String firstName, String lastName, Gender gender,
			String mobilePhoneId) {
		this(firstName, lastName, gender);
		this.mobilePhoneId = mobilePhoneId;
	}

	public Customer(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobilePhoneId() {
		return mobilePhoneId;
	}

	public void setMobilePhoneId(String mobilePhoneID) {
		this.mobilePhoneId = mobilePhoneID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Collection<AbstractTouchpoint> getTouchpoints() {
		return touchpoints;
	}

	public void setTouchpoints(HashSet<AbstractTouchpoint> touchpoints) {
		this.touchpoints = touchpoints;
	}

	public AbstractTouchpoint getPreferredTouchpoint() {
		return preferredTouchpoint;
	}

	public void setPreferredTouchpoint(AbstractTouchpoint preferredTouchpoint) {
		this.preferredTouchpoint = preferredTouchpoint;
	}

	public String toString() {
		return "{Customer " +  this.id + " " + this.firstName + " " + this.lastName
				+ " (" + this.gender + ") " + this.email + ", "
				+ this.mobilePhoneId + ", " + this.address + "}";
	}
	
	public void setGender(Gender gd) {
		this.gender = gd;
	}
	
	public Gender getGender() {
		return this.gender;
	}

	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	public Collection<CustomerTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Collection<CustomerTransaction> transactions) {
		this.transactions = transactions;
	}
	
	/*
	 * lifecycle logging
	 */
	
	@PostLoad
	public void onPostLoad() {
		System.out.println("@PostLoad: " + this);
	}
	
	@PostPersist
	public void onPostPersist() {
		System.out.println("@PostPersist: " + this);		
	}
	
	@PostRemove
	public void onPostRemove() {
		System.out.println("@PostRemove: " + this);
	}

	@PostUpdate
	public void onPostUpdate() {
		System.out.println("@PostUpdate: " + this);
	}
	
	@PrePersist
	public void onPrePersist() {
		System.out.println("@PrePersist: " + this);
	}

	@PreRemove
	public void onPreRemove() {
		System.out.println("@PreRemove: " + this);
	}

	@PreUpdate
	public void onPreUpdate() {
		System.out.println("@PreUpdate: " + this);		
	}
	
}