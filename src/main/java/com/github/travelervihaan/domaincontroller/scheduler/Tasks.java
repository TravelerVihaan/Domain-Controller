package com.github.travelervihaan.domaincontroller.scheduler;

import com.github.travelervihaan.domaincontroller.model.Alert;
import com.github.travelervihaan.domaincontroller.model.Domain;
import com.github.travelervihaan.domaincontroller.model.DomainRequest;
import com.github.travelervihaan.domaincontroller.service.DomainService;
import com.github.travelervihaan.domaincontroller.service.MongoDomainService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoSocketException;

import com.github.travelervihaan.domaincontroller.service.MailService;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Tasks {
    private static LocalDateTime lastCheckedDomains = LocalDateTime.now();
    private static LocalDateTime lastCheckedDatabase = LocalDateTime.now();
    private final Logger log = LoggerFactory.getLogger(Tasks.class);

    //@Autowired
    private MailService mailService;
    private DomainService domainService;
    private MongoDomainService mongoDomainService;
    
    @Autowired
    public Tasks(MailService mailService, DomainService domainService, MongoDomainService mongoDomainService) {
    	this.mailService = mailService;
    	this.domainService = domainService;
    	this.mongoDomainService = mongoDomainService;
    }

    //second, minute, hour, day of month, month, day(s) of week
    //(*) match any     ? No specific value
    @Scheduled(cron = "0 0 6 * * ?")
    public void checkDomains() {
        log.info("[DOMAIN] LOOP - Zaczynam sprawdzanie maili");
		String previousCode = null;
        //for (String domain : DomainList.DOMAIN_LIST) {
		
		for (Domain domain : mongoDomainService.getDomains()) {
			JSONObject json = createJSON(domain.getDomainName());
            try {
                String result = domainService.sendRequest(json);
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                DomainRequest request = gson.fromJson(result, DomainRequest.class);
                if (!request.getResult().getDomain_autorenew()) {
                    Alert.setActive(true);
                    mailService.sendDomainProblemMail(request.getResult().getDomain_name());
                    log.error("[DOMAIN] LOOP - Problem z domena " + request.getResult().getDomain_name());
                }
            } catch (Exception e) {
				if(previousCode != e.toString()){
					previousCode = e.toString();
					mailService.sendRequestProblemMail(e.toString());
					log.error(e.toString());
				}
            }
        }
		lastCheckedDomains = LocalDateTime.now();
        log.info("[DOMAIN] LOOP - Sprawdzono wszystkie maile.");
    }
    
    private JSONObject createJSON(String domainName) {
    	Map<String, String> headers = new LinkedHashMap<>();
        headers.put("login", "example");
        headers.put("password", "example");
        headers.put("command", "domain_info");
        headers.put("domain_name", domainName);
        JSONObject json = new JSONObject(headers);
    	return json;
    }

    @Scheduled(fixedRate = 3600000)
    public void sendAlertLink() {
        if (Alert.isActive()) {
            log.info("[MAIL] Problem z domena, alert nadal aktywny.");
            mailService.sendAutoRenewProblemLink();
        }
    }
    
    private static String formatDate(LocalDateTime time) {
    	return time.format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
    }
    
    public static String getLastCheckedDomains() {
    	return formatDate(lastCheckedDomains);
    }
    
    public static String getLastCheckedDatabase() {
    	return formatDate(lastCheckedDatabase);
    }
    
    //second, minute, hour, day of month, month, day(s) of week
    //(*) match any     ? No specific value
    @Scheduled(cron = "0 0 5 * * ?")
    public void checkDatabaseConnection() {
    	log.info("[DATABASE] Sprawdzam polaczenie z baza danych.");
    	lastCheckedDatabase = LocalDateTime.now();
    	try {
    		mongoDomainService.getDomains();
    	}catch(MongoSocketException e) {
    		log.info("[DATABASE] Wystapil problem z polaczeniem z baza danych.");
    		mailService.sendDatabaseProblemMail();
    	}
    }
}