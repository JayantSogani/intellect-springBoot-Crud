package com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.model.User;
import com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.repo.UserRepo;
/**
 * User Service Impl class
 * @author admin
 *
 */
@Component
public class UserServiceImpl implements UserServices{

	@Autowired
	UserRepo userRepo;
	
	public User createUser(User user) {
		user.setActive(Boolean.TRUE);
		return userRepo.save(user);
	}

	public boolean checkAlreadyRegistered(User user) {
		return null==userRepo.findByEmail(user.getEmail())?false:true;
	}

	public User save(User user) {
		return userRepo.save(user);
	}

	public User findById(Long id) {
		return userRepo.findById(id);
	}

	public Iterable<User> findAll() {
		return userRepo.findAll();
	}

}
