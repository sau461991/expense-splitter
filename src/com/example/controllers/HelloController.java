package com.example.controllers;


import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.SheetDao;
import com.example.dao.UserDAO;
import com.example.model.Sheet;
import com.example.model.User;



@Controller
@RequestMapping("/")
public class HelloController {
	

	@Autowired
    private UserDAO userDao;
	
	@Autowired
	private SheetDao sheetDao;

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(ModelMap model1) {
		System.out.println("in /");
		model1.addAttribute("message", "Home Page");
		return "welcome";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String sayHelloAgain(ModelMap model) {
		System.out.println("in /add");
		model.addAttribute("message", "hello agaian page");
		
		User user = new User();
		user.setUsername("saurabh");
		user.setEmail("s@citrus.com");
		user.setPassword("baramati");
		
		userDao.saveOrUpdate(user);
		
		return "addingDocument";
	}
	
	@RequestMapping(value="/create",method = RequestMethod.GET)
	public String createSheet(@RequestParam("sheetName") String sheetName) {
		
		System.out.println("in /create method");
		
		Timestamp creationDate = new Timestamp(new java.util.Date().getTime());
		Timestamp modificationDate = new Timestamp(new java.util.Date().getTime());
		
		Sheet sheet = new Sheet(sheetName, creationDate, modificationDate);
		
		//sheetDao.creatSheet(sheet);
		
		sheetDao.addSheet(sheet);
		
		return "sheetCreated";
		
	}
	
	

}
