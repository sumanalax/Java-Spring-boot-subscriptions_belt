package com.codingdojo.loginreg.validators;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingdojo.loginreg.models.User;
import com.codingdojo.loginreg.services.UserService;

@Component
public class UserValidator implements Validator{
	private UserService uS;
	
	public UserValidator() {
		
	}
	
	public UserValidator(UserService uS,Object target,Errors errors){
		this.uS = uS;
		this.validate(target,errors);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		if(!user.getPassword().equals(user.getConfirm()) )
			errors.rejectValue("password","Match");

		User exists = uS.findByEmail(user.getEmail());
		if(exists != null) errors.rejectValue("email","Exists");
	}
}


