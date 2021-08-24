package com.kevin.examone.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.examone.models.User;
import com.kevin.examone.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepository;
	
	public List<User> showAllUsers(){
		return this.uRepository.findAll();
	}
	
	public User getOneUser(Long id) {
		User user = this.uRepository.findById(id).orElse(null);
		return user;
	}
	
	public User createUser(User user) {
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hash);
		return this.uRepository.save(user);
	}
	
	// find user by email
    public User findByEmail(String email) {
        return uRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> eUser = uRepository.findById(id);
    	
    	if(eUser.isPresent()) {
            return eUser.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = uRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
        	// if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    // Delete
    public void deleteUser(Long id) {
    	this.uRepository.deleteById(id);
    }
}
