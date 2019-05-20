package com.github.travelervihaan.domaincontroller.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MailList {

	private static List<String> mailList;
	
	public MailList() {
		mailList = new ArrayList<String>();
		mailList.add("example@example.pl");
		mailList.add("example@example.pl");
		mailList.add("example@example.pl");
		mailList.add("example@example.pl");
		mailList.add("example@example.pl");
	}
	
	public void addMail(String mail) {
		try {
			for(String email : mailList) {
				if(mail.equals(email)) {
					throw new IllegalArgumentException();
				}
			}
			mailList.add(mail);
		}catch(IllegalArgumentException e) {
			System.err.println("Niepoprawny index!\n");
		}
	}
	
	public void deleteMail(int index) {
		try {
			mailList.remove(index);
		}catch(IndexOutOfBoundsException e) {
			System.err.println("Niepoprawny index!\n");
		}
	}
	
	public static List<String> getMails(){
		return mailList;
	}
}
