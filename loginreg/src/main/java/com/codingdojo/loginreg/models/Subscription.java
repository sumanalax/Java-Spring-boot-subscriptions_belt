package com.codingdojo.loginreg.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Subscription {
	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private boolean available;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Size(min=1,max=255,message="Name must be present between 1 to 255 characters")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@NotNull
	private double cost;
	
	@OneToMany(mappedBy="subscription",fetch=FetchType.LAZY)
	private List<User> users;
	
	public Subscription() {
		
	}

}
