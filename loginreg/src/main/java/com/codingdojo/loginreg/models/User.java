package com.codingdojo.loginreg.models;

import java.util.Date;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Size(min=2,max=255,message="Name must be present between 2-255 characters.")
	private String name;
	@Email()
	@NotNull()
	private String email;
	@Size(min=8,max=64)
	private String password;
	@Transient
	@Size(min=8,max=64)
	private String confirm;
	
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	private boolean admin;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="subscription_id")
	private Subscription subscription;
	private Date dueDate;

	

	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;
	
	
	@PrePersist
	public void OnCreate(){createdAt = new Date();}
	@PreUpdate
	public void OnUpdate(){updatedAt = new Date();}
	
	
	public Subscription getSubscription() {return subscription;}
	public void setSubscription(Subscription subscription) {this.subscription = subscription;}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	public boolean isAdmin() {return admin;}
	public void setAdmin(boolean admin) {this.admin = admin;}

	
	public User(){
		
	}
}	


