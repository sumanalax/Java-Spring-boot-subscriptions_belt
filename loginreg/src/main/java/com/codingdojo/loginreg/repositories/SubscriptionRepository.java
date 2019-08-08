package com.codingdojo.loginreg.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.loginreg.models.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription,Long>{
	

}
