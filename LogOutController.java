package com.ofos.logout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LogOutController {
	
	

	@Autowired
	HttpSession session;

	@RequestMapping("/logout")
	public ModelAndView helloWorld(HttpServletRequest request) {
		
		if (session != null) {
			session.invalidate();
			System.out.println("Destroyed Now  In Logout Controller ");
		}
		return new ModelAndView("logging");
	}


}
