package com.codingdojo.loginreg.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.codingdojo.loginreg.models.Subscription;
import com.codingdojo.loginreg.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
	private SubscriptionRepository sR;
	
	public SubscriptionService(SubscriptionRepository sR) {
		this.sR = sR;
	}
	
	public Subscription create(Subscription subscription) {
		return sR.save(subscription);
	}
	
	public ArrayList<Subscription> findAll(){
		return (ArrayList<Subscription>) sR.findAll();
	}
	
	public Subscription findById(Long id) {
		return sR.findById(id).get();
	}
	
	public Subscription update(Subscription subscription) {
		return sR.save(subscription);
	}
	
	public void destroy(Long id) {
		sR.deleteById(id);		
	}
	
}
