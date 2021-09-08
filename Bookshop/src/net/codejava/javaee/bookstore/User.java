package net.codejava.javaee.bookstore;


public class User {
	private int id;
	private String fullname;
	private String email;
	private String password;
	private String role;
	private String cart="";
	
	public User() {
		
	}
	
	public User(int id) {
		this.id=id;
	}
	
	public User(int id, String email, String password, String fullname, String role, String cart) {
		 this(email, password, fullname, role, cart);
	     this.id = id;
	}
	
	public User(String email, String password, String fullname, String role, String cart) {
		this.email=email;
		this.fullname=fullname;
		this.password=password;
		this.role=role;
		this.cart=cart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCart() {
		return cart;
	}

	public void addCart(String cart) {
		this.cart = this.cart+cart+"/";
	}
	public void deleteCart(String cart) {
		int index=this.cart.indexOf(cart);
		String result = this.cart.substring(0, index) + this.cart.substring(index+2);
		this.cart=result;
	}
	
	
	
}
