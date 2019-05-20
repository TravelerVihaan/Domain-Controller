package com.github.travelervihaan.domaincontroller.repository;

import com.github.travelervihaan.domaincontroller.model.Domain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends MongoRepository<Domain, String>{
	
	public Domain findByDomainName(String domainName);
	
	public Domain findFirstByDomainName(String domainName);
	
}
