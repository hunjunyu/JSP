package exercise.order.domain;

public class Customer {
	
	private long id;
	private String name;
	private String address;
	private String email;

	// 2. toString
	public String toString() {
		return "id : " + id + ", name : " + name + ", address : " + address + ", email : " + email;
	}
	
	// 1. setter, gettter
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
