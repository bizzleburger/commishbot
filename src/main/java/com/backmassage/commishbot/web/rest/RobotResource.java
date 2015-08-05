package com.backmassage.commishbot.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST controller for managing CommishBot's commands and such.
 */
@RestController
@RequestMapping("/api/robot")
public class RobotResource {
	
	private final Logger log = LoggerFactory.getLogger(RobotResource.class);
	
	@RequestMapping(value = "/hello",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
	public String helloWorld(){
		
		return "Hello bitches";
	}
	
	@RequestMapping(value = "/matchup",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
	public String matchup(@RequestBody String request){
		
		log.debug("Payload :" + request);
		
		return "test matchup: " + request;
	}

}
