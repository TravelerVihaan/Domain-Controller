package com.github.travelervihaan.domaincontroller.service;

import com.github.travelervihaan.domaincontroller.model.MailList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private JavaMailSender javaMailSender;
    private final String MAIL_FROM = "example@example";
    private final String MAIL_SUBJECT = "BŁĄD Z DOMENĄ!!";
    private String[] recipentList;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendDomainProblemMail(String address) {
    	SimpleMailMessage simpleMailMessage = createMessage(MAIL_SUBJECT);
        simpleMailMessage.setText("<b>Wystąpił problem z domeną " + address + ".\n"
                + "Przejdź do https://www.domeny.pl, zaloguj się na nasze konto, zdiagnozuj problem z domeną " + address + " następnie kliknij w poniższy link.\n"
                + "Link: http://example:9211/alarm/off </b>");
        javaMailSender.send(simpleMailMessage);
    }

    public void sendRequestProblemMail(String s) {
    	SimpleMailMessage simpleMailMessage = createMessage(MAIL_SUBJECT);
        simpleMailMessage.setText("Wystąpił problem z zapytaniem do API.\n" + "Błąd: " + s);
        javaMailSender.send(simpleMailMessage);
    }
    
    public void sendDatabaseProblemMail() {
    	SimpleMailMessage simpleMailMessage = createMessage("PROBLEM Z BAZA DOMEN!!");
        simpleMailMessage.setText("Wystąpił problem z pobraniem danych z bazy domen!\n");
        javaMailSender.send(simpleMailMessage);
    }

    public void sendAutoRenewProblemLink() {
        SimpleMailMessage simpleMailMessage = createMessage(MAIL_SUBJECT);
        simpleMailMessage.setText("Problem z domeną nadal nie został rozwiązany. \n"
                + "Przejdź do https://www.example.pl, zaloguj się na nasze konto, zdiagnozuj problem następnie kliknij w poniższy link.\n"
                + "Link: http://example:9211/alarm/off");
        javaMailSender.send(simpleMailMessage);
    }
    
    private void setRecipentList() {
    	recipentList = new String[MailList.getMails().size()];
    	for(int i=0;i<MailList.getMails().size();i++) {
    		recipentList[i] = MailList.getMails().get(i);
    	}
    }
    
    private SimpleMailMessage createMessage(String subject) {
    	SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    	setRecipentList();
    	simpleMailMessage.setTo(recipentList);
        simpleMailMessage.setFrom(MAIL_FROM);
        simpleMailMessage.setSubject(subject);
        return simpleMailMessage;
    }
}