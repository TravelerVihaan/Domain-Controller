package com.github.travelervihaan.domaincontroller.service;

import java.util.List;

import com.github.travelervihaan.domaincontroller.model.Domain;
import com.github.travelervihaan.domaincontroller.model.DomainList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.mongodb.MongoSocketOpenException;

import com.github.travelervihaan.domaincontroller.repository.DomainRepository;
import com.github.travelervihaan.domaincontroller.scheduler.Tasks;

@Service
public class MongoDomainService {
	
	private DomainRepository domainRepository;
	private final Logger log = LoggerFactory.getLogger(Tasks.class);
	
	@Autowired
	public MongoDomainService(DomainRepository domainRepository) {
		this.domainRepository = domainRepository;
	}
	
	public void addDomain(String domainName) {
		try {
			if(domainName!="" && domainName.length()>0) {
				if(checkDuplicateDomain(domainName)) {
					Domain domain = new Domain(domainName);
					domainRepository.save(domain);
				}
			}else {
				throw new IllegalArgumentException();
			}
		}catch(MongoSocketOpenException e) {
			log.info("Straciles polaczenie z baza danych!!");
		}catch(IllegalArgumentException e) {
			log.info("Niepoprawna nazwa domeny!!");
		}
	}
	
	boolean checkDuplicateDomain(String domainName) {
		for(Domain domain : this.getDomains()) {
			if(domain.getDomainName().equalsIgnoreCase(domainName))
				return false;
		}
		return true;
	}
	
	public void deleteDomain(String domainName) {
		try {
			domainRepository.delete(domainRepository.findByDomainName(domainName));
		}catch(MongoSocketOpenException e) {
			log.info("Straciles polaczenie z baza danych!!");
		}catch(IllegalArgumentException e) {
			log.info("Podana domena nie istnieje w bazie danych!!");
		}catch(IncorrectResultSizeDataAccessException e) {
			domainRepository.delete(domainRepository.findFirstByDomainName(domainName));
			log.info("Domena byla zdublowana w bazie danych!!");
		}
	}
	
	public List<Domain> getDomains() /*throws MongoSocketOpenException*/ {
		try {
			return domainRepository.findAll();
		}catch(MongoSocketOpenException e) {
			log.info("Straciles polaczenie z baza danych!!");
			return null;
		}
	}
	
	public void addAllDomains() {
		try {
			for(String domainName : DomainList.DOMAIN_LIST) {
				Domain domain = new Domain(domainName);
				domainRepository.save(domain);
			}
		}catch(MongoSocketOpenException e) {
			log.info("Straciles polaczenie z baza danych!!");
		}
	}
}
