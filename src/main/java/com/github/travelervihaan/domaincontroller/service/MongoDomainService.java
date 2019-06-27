package onet.grupa.domaincontroller.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.mongodb.MongoSocketOpenException;

import onet.grupa.domaincontroller.model.Domain;
import onet.grupa.domaincontroller.model.DomainList;
import onet.grupa.domaincontroller.repository.DomainRepository;
import onet.grupa.domaincontroller.scheduler.Tasks;

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

	/*
	* Check domains from file
	 */

	public void checkDomainsFromFile(){
		String fileName = "src/main/resources/static/domeny.txt";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		List<String> domainsFromFile = new ArrayList<>();

		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String nextLine = null;
			while((nextLine = bufferedReader.readLine()) != null){
				domainsFromFile.add(nextLine);
			}
		}catch (IOException e){
			log.error("IO Exception with read file domeny.txt");
			e.printStackTrace();
		}finally{
			try{
				if(bufferedReader!=null)
					bufferedReader.close();
			}catch (IOException e){
				log.error("IO Exception with closing buffered reader");
			}
		}
		try {
			compareDomainsFromFileAndAddNew(domainsFromFile);
			compareDomainsFromFileAndRemove(domainsFromFile);
		}catch (MongoSocketOpenException e){
			log.info("Straciles polaczenie z baza danych!!");
		}
	}

	private void compareDomainsFromFileAndAddNew(List<String> domainsFromFile) throws MongoSocketOpenException{
		domainsFromFile
				.stream()
				.filter(d -> !Optional.ofNullable(domainRepository.findByDomainName(d)).isPresent())
				.forEach(d -> domainRepository.save(new Domain(d)));
	}

	private void compareDomainsFromFileAndRemove(List<String> domainsFromFile) throws MongoSocketOpenException {
		List<Domain> domainsFromDB = domainRepository.findAll();
		domainsFromDB
				.stream()
				.filter(d -> !domainsFromFile.contains(d.getDomainName()))
				.forEach(System.out::println);
	}
}
