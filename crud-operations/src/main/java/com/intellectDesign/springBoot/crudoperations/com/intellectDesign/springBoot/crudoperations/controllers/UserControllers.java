package com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.model.Result;
import com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.model.User;
import com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.services.UserServices;
import com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.util.UtilOperations;
/**
 * Controller class for the users
 * @author admin
 *
 */
@RestController
public class UserControllers {
	
	private final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserControllers.class);

	@Autowired
	UserServices userServices;
	
	/**
	 * Creates the users based on the validation
	 * @param user
	 * @param errors
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@Valid @RequestBody User user,Errors errors) throws ParseException {
		
		Result result = new Result();
		
		ResponseEntity<Result> error = handleError(errors,user,result);
	
		if(null != error)
			return error;
			
	    User createdUser = userServices.createUser(user);	
	    
	    result.setMsg("success");
	    result.setSetResult(createdUser);
	    return ResponseEntity.ok(result);
	}
    /**
     * Handles the error for the input users
     * @param errors
     * @param user
     * @param result
     * @return
     * @throws ParseException
     */
	private ResponseEntity<Result> handleError(Errors errors, User user, Result result) throws ParseException {
		String msg = null;
		if (errors.hasErrors()) {
			for(ObjectError error : errors.getAllErrors()){
				msg = msg + error.getDefaultMessage();
		}
			result.setMsg(msg);
			return ResponseEntity.badRequest().body(result);
		}
		
		if(checkBirthDateValid(user)){
			result.setMsg("User Has Invalid birthDate !");
			return ResponseEntity.badRequest().body(result);
		}
		
		if(userServices.checkAlreadyRegistered(user)){
			result.setMsg("User is already registered with this email Address !");
			return ResponseEntity.badRequest().body(result);
		}
		return null;
	}
	/**
	 * Checks birth date valid 
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	private boolean checkBirthDateValid(User user) throws ParseException {
		return UtilOperations.isDateValid(user.getBirthDate());
		
	}
	
	/**
	 * Updates the user 
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Result> update(@PathVariable Long id, @RequestBody User user) {
        LOG.info("Updating User: {}", user);
        User currentUser = userServices.findById(id);
        Result result = new Result();
        if (currentUser == null) {
        	result.setMsg("User is not found for the Id " );
        	result.setSetResult(user);
            return new ResponseEntity<Result>(result,HttpStatus.NOT_FOUND);
        }

        currentUser.setPinCode(user.getPinCode());
        currentUser.setBirthDate(user.getBirthDate());
        userServices.save(currentUser);
        result.setMsg("User is updated");
        result.setSetResult(currentUser);
        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }

	/**
	 * Deletes the users meaning sets the status to false
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Result> delete(@PathVariable("id") Long id) {
        LOG.info("Deleting user with id: {}", id);
        User currentUser = userServices.findById(id);
        Result result = new Result();
        if (currentUser == null) {
        	result.setMsg("User Can't be deleted as not found for the Id " );
            return new ResponseEntity<Result>(result,HttpStatus.NOT_FOUND);
        }
        
        currentUser.setActive(false);
        userServices.save(currentUser);
        result.setMsg("User is Deleted");
        result.setSetResult(currentUser);
        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }
    /**
     * Provides the total no of users 
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> findAllUsers() {
        LOG.info("Getting all the users: {}");
        Iterable<User> users  = userServices.findAll();
        return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
    }


}
