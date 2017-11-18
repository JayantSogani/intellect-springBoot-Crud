package com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * Spring boot launcher to start 
 * @author admin
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class CrudOperationsApplication {

	private final Logger log = org.slf4j.LoggerFactory.getLogger(CrudOperationsApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(CrudOperationsApplication.class, args);
	}
	
	
	@PostConstruct
	public void started(){
		log.info("Hey i am started sucessfully !!");
	}
}
