package onet.grupa.domaincontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import onet.grupa.domaincontroller.service.MongoDomainService;

@Controller
public class DomainController {
	
	private MongoDomainService mongoDomainService;
	
	@Autowired
	public DomainController(MongoDomainService mongoDomainService) {
		this.mongoDomainService = mongoDomainService;
	}
	
	@GetMapping("/domains")
	public String domains(Model model) {
		//try {
			model.addAttribute("domainList",mongoDomainService.getDomains());
		//}catch(Exception e) {
		//	System.err.println("Straciles polaczenie z baza danych!!");
		//}
		return "domains/domains";
	}
	
	@PostMapping("/add_domain")
	public String dropMail(@RequestParam String domain, Model model) {
		mongoDomainService.addDomain(domain);
		return "redirect:/domains";
	}
	
	@PostMapping("/addalldomains")
	public String addAllDomains() {
		mongoDomainService.addAllDomains();
		return "redirect:/domains";
	}
	
	
	@PostMapping("/drop_domain")
	public String addMail(@RequestParam String domain, Model model) {
		mongoDomainService.deleteDomain(domain);
		return "redirect:/domains";
	}

	@PostMapping("/compare-domains")
	public String compareDomainsWithFire(){
		mongoDomainService.checkDomainsFromFile();
		return "redirect:/domains";
	}

}
