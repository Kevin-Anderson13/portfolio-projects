package com.kevin.examone.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.kevin.examone.models.User;
import com.kevin.examone.repositories.UserRepository;

@Component
public class UserValidator {
	@Autowired
	private UserRepository uRepository;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("password", "Mismatch", "Confirmed Password must match the original Password!");
		}
		
		if(this.uRepository.existsByEmail(user.getEmail())) {
			errors.rejectValue("email", "PreviousMatch", "Email has already been used");
		}
		
	}
}
