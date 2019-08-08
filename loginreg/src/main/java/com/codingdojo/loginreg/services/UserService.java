package com.codingdojo.loginreg.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.codingdojo.loginreg.models.User;
import com.codingdojo.loginreg.repositories.UserRepository;

@Service
public class UserService{
	private UserRepository uR;
	
	public UserService(UserRepository uR){
		this.uR = uR;
	}

	public User create(User user){
		return uR.save(user);
	}
	
	public ArrayList<User> findAll(){
		return (ArrayList<User>) uR.findAll();
	}
	
	public User findById(Long id){
		return uR.findById(id).get();
	}
	
	public User findByEmail(String email) {
		return uR.findByEmail(email);
	}
	
	public User findByEmailAndPassword(String email,String password) {
		return uR.findByEmailAndPassword(email, password);
	}
	
	public User update(User user){
		return uR.save(user);
	}
	
	public void destroy(Long id) {
		uR.deleteById(id);
	}
}



