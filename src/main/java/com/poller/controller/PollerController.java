package com.poller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poller.model.Service;
import com.poller.service.PollerService;

@RestController
@CrossOrigin
public class PollerController {
	
	@Autowired
	PollerService pollerService;

	/*
	 * Return all the services from the database
	 */
	@GetMapping("/")
	public Iterable<Service> getServices() {
		
		Iterable<Service> services = pollerService.getServices();
		
		return services;
		
	}
	
	@PostMapping("/addService")
	public String addService(@RequestBody Service service ) {
		
		String message = pollerService.addService(service);
		return message;
	}
	
	@DeleteMapping("/deleteService")
	public String deleteService(@RequestBody Service service) {
		System.out.println("DELETE SERVICE INSIDE CONTROLLER!!");
		String message = pollerService.deleteService(service);
		return message;
	}
	
	@GetMapping("/performHealthCheck")
	public void performHealthCheck() {
		pollerService.performHealthCheck();
	}
}
