package com.github.travelervihaan.domaincontroller.controller;

import com.github.travelervihaan.domaincontroller.model.Alert;
import com.github.travelervihaan.domaincontroller.scheduler.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {
	
    private Logger logger = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping("/")
    public String check(Model model) {
        model.addAttribute("time", Tasks.getLastCheckedDomains());
        model.addAttribute("timeDatabase", Tasks.getLastCheckedDatabase());
        return "check";
    }

    @RequestMapping("/alarm/off")
    public String turnOff() {
        Alert.setActive(false);
        logger.info("Wylaczylem alarm, aktualny status " + Alert.isActive());
        return "alarm";
    }
}

