package com.poller.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.poller.model.Service;
import com.poller.repository.PollerRepository;

@org.springframework.stereotype.Service
public class PollerService {

	@Autowired
	PollerRepository pollerRepository;
	
	public synchronized Iterable<Service> getServices(){
		
		Iterable<Service> services = pollerRepository.findAll();
		
		return services;
	}
	
	
	public String addService(Service service) {
		
		try {
			Date currentDate = new Date();
			Timestamp currentTimeStamp = new Timestamp(currentDate.getTime());
			service.setDate(currentTimeStamp);
			service.setStatus("Checking");
			Service savedService = pollerRepository.save(service);
			if(null != savedService) return "Service saved successfully with id: "+service.getId();
			else return "Save was unsuccessfull";
			 
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Save was unsuccessfull";
		}
		
		
	}
	
	public synchronized String deleteService(Service service) {
		
		try {
			Integer id = service.getId();
			Optional<Service> optionalService = pollerRepository.findById(id);
			if(optionalService.isPresent()) {
				Service deleteService = optionalService.get();
				pollerRepository.delete(deleteService);
				return "Service with id: "+deleteService.getId()+" and name: "+deleteService.getName()+" deleted successfully!";
			}
			else {
				return "Delete was unsuccessfull";
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Delete was unsuccessfull";
		}
	}
	
	/*public void performHealthCheck() {
		Iterable<Service> services = pollerRepository.findAll();
		Integer code = 200;
		
		for(Service currentService : services) {
			String url = currentService.getUrl();
			try {
				URL siteURL = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(3000);
				connection.connect();
	 
				code = connection.getResponseCode();
				if (code == 200) {
					currentService.setStatus("Healthy");
					pollerRepository.save(currentService);
				} else {
					currentService.setStatus("Not Healthy");
					pollerRepository.save(currentService);
				}
			} catch (Exception e) {
				currentService.setStatus("Not Healthy");
				pollerRepository.save(currentService);
	 
			}
		}
	}*/
	
	public void performHealthCheck() {
		
		Iterable<Service> services = pollerRepository.findAll();
		for(Service service : services) {
			String serviceUrl = service.getUrl();
			URI uri;
			URL url;
			try {
				url = new URL(serviceUrl);
				//url = uri.toURL();
				InputStream in = url.openStream();
				
				service.setStatus("Healthy");
				pollerRepository.save(service);
				in.close();
				
			
			}
			
			catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				service.setStatus("Incorrect URI");
				pollerRepository.save(service);
				e.printStackTrace();
			}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				service.setStatus("Not Healthy");
				pollerRepository.save(service);
				e.printStackTrace();
			}
			catch(Exception e) {
				service.setStatus("Not Healthy");
				pollerRepository.save(service);
				e.printStackTrace();
			}
		}//For loop ends
		
	}
	
	@Scheduled(fixedRate = 4000L)
	public void sendMessage() {
	    this.performHealthCheck();
	}
	
	
}
