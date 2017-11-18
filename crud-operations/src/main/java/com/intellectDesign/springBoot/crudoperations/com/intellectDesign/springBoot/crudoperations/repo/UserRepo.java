package com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.repo;

import org.springframework.data.repository.CrudRepository;

import com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.model.User;
/**
 * User repo
 * @author admin
 *
 */
public interface UserRepo extends CrudRepository<User, Long>{

	User findByEmail(String email);
	
	User findById(Long id);
	
}
