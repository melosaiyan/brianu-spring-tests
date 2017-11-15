package com.brianu.tutorial.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.support.RequestContextUtils;

//import com.boraji.tutorial.spring.controller.HelloWorldController;

/**
 * @author imssbora
 */
@Controller
public class HelloWorldController {
	
	private static final Logger logger = LogManager.getLogger(HelloWorldController.class);
   
   @RequestMapping(path={"/"},method=RequestMethod.GET)
   public String sayHello(Model model) {
      model.addAttribute("message","Hello Spring MVC!");
     
      logger.info("Getting date!");
      //Java 8 LocalDate
      DateTimeFormatter formatter=DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
      LocalDate date=LocalDate.now();
      logger.info("Add Date to Page Attribute!");
      model.addAttribute("date", date.format(formatter));
      
      logger.info("Return index!");
      return "index";
   }
   
   //Handles GET or POST requests
   @RequestMapping("/welcome")
   public String handlerWelcome() {
      return "welcome";
   }

}