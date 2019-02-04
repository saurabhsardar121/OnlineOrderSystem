package com.ofos.customer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Coustmer {
	@Id
	@GeneratedValue
	Long id;
	String firstname;
	String lastname;
	Long mobno;
	String address;
	String city;
	String email;
	String Passw;
	Long roleId;
	boolean status;
	@Override
	public String toString() {
		return "Coustmer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", mobno=" + mobno
				+ ", address=" + address + ", city=" + city + ", email=" + email + ", Passw=" + Passw + ", roleId="
				+ roleId + ", status=" + status + "]";
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Long getMobno() {
		return mobno;
	}
	public void setMobno(Long mobno) {
		this.mobno = mobno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassw() {
		return Passw;
	}
	public void setPassw(String passw) {
		Passw = passw;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
