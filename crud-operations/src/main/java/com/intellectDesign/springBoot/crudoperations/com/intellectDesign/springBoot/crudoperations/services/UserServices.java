package com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.services;

import javax.transaction.Transactional;

import com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.model.User;

/**
 * User services 
 * @author admin
 *
 */
@Transactional
public interface UserServices {
    /**
     * Creates the user
     * @param user
     * @return
     */
	User createUser(User user);
	/**
	 * Checks if user is already created or not
	 * @param user
	 * @return
	 */
	boolean checkAlreadyRegistered(User user);
	/**
	 * Save the user
	 * @param user
	 * @return
	 */
	User save(User user);
	/**
	 * Find the user based on the id
	 * @param id
	 * @return
	 */
	User findById(Long id);
	/**
	 * Returns all the users
	 * @return
	 */
	Iterable<User> findAll();
	
}
