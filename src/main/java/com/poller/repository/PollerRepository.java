package com.poller.repository;

import org.springframework.data.repository.CrudRepository;

import com.poller.model.Service;

public interface PollerRepository extends CrudRepository<Service, Integer> {
	

}
