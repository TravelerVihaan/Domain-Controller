package com.github.travelervihaan.domaincontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.travelervihaan.domaincontroller.model.MailList;

@Controller
public class MailController {
	
	private MailList mailListComponent;
	
	@Autowired
	public MailController(MailList mailListComponent) {
		this.mailListComponent = mailListComponent;
	}

	@GetMapping("/mails")
	public String mails(Model model) {
		model.addAttribute("mailList", MailList.getMails());
		return "mails/mails";
	}
	
	@PostMapping("/dropmail")
	public String dropMail(@RequestParam int index, Model model) {
		mailListComponent.deleteMail(index);
		return "redirect:/mails";
	}
	
	@PostMapping("/addmail")
	public String addMail(@RequestParam String email, Model model) {
		if(email!=null && email!="") {
			mailListComponent.addMail(email);
		}
		return "redirect:/mails";
		
	}
}
