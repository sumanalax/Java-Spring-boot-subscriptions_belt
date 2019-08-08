package com.codingdojo.loginreg.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.loginreg.models.Subscription;
import com.codingdojo.loginreg.models.User;
import com.codingdojo.loginreg.services.SubscriptionService;
import com.codingdojo.loginreg.services.UserService;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {
	private SubscriptionService sS;
	private UserService  uS;
	
	public SubscriptionController(SubscriptionService sS, UserService uS) {
		this.sS=sS;
		this.uS=uS;
	}
	
	@PostMapping("")
	public String create( @RequestParam("name") String name, @RequestParam("cost") double cost) {
		Subscription sub = new Subscription();
		if(cost<1) {
			return "redirect:/users/admin";
		}
		sub.setAvailable(true);
		sub.setName(name);
		sub.setCost(cost);
		sS.create(sub);
		
		return "redirect:/users/admin";
	}
	
	@PostMapping("{id}/toggle")
	public String toggle( @PathVariable("id") Long id, HttpSession session) {
		Long uid = (Long)session.getAttribute("user");
		User user = uS.findById(uid);
		if(uid == null) {
			return "redirect:/users";
		}
		if(!user.isAdmin()) {
			return "redirect:/users/dashboard";
		}		
		Subscription sub = sS.findById(id);
		if(sub.getUsers().size() < 1) {
			sub.setAvailable(!sub.isAvailable());
			sS.update(sub);
		}
		
		return "redirect:/users/admin";
	}
	
	@GetMapping("{id}/edit")
	public String GetEdit( @PathVariable("id") Long id, HttpSession session, Model model) {
		Long uid = (Long)session.getAttribute("user");
		User user = uS.findById(uid);
		if(uid == null) {
			return "redirect:/users";
		}
		if(!user.isAdmin()) {
			return "redirect:/users/dashboard";
		}
				
		Subscription sub = sS.findById(id);
		model.addAttribute("subscription", sub);
		
		return "edit";	
	}
	
	@PostMapping("{id}/update")
	public String edit(@PathVariable("id") Long id, @RequestParam("cost") double cost) {
		Subscription sub=sS.findById(id);
		sub.setCost(cost);
		sS.update(sub);
		return "redirect:/users/admin";
	}
	
	@PostMapping("{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		Subscription sub = sS.findById(id);
		if(sub.getUsers().size() < 1) {
			sS.destroy(id);
		}
		return "redirect:/users/admin";
	}
	
	@PostMapping("/change")
	public String change(HttpSession session, @RequestParam("subscription") Long subId) {
		Long uid = (Long)session.getAttribute("user");
		User user = uS.findById(uid);
		Subscription sub = sS.findById(subId);
		user.setSubscription(sub);
		Date d = new Date();
		d.setMonth(d.getMonth()+3);
		user.setDueDate(d);
		uS.update(user);
		return "redirect:/users/dashboard";
	}

}
