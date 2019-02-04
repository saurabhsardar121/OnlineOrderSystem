package com.ofos.registerationandlogging.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ofos.customer.dto.CoustmerDto;
import com.ofos.customer.entity.Coustmer;
import com.ofos.customer.service.CoustomerService;
import com.ofos.item.service.ItemService;
import com.ofos.registerationandlogging.service.LoggingAndRgisteraionService;


@Controller
public class LoggingAndRgisteraionController {
	
	@Autowired
	LoggingAndRgisteraionService loggingAndRgisteraionService;
	
	@Autowired
	HttpSession session;
	
	private final static Logger logger = Logger.getLogger(LoggingAndRgisteraionController.class);

	@RequestMapping("/reg")
	public ModelAndView CoustmerRegistr() {
		return new ModelAndView("register");
	}

	@RequestMapping("/coustomer")
	public ModelAndView Registerarion(CoustmerDto coustmerDto) {
		logger.info(" Excecuttion start  LoggingAndRgisteraionController :: I am register here ");
		Long roleId=loggingAndRgisteraionService.getRollIdByUserType(coustmerDto.getUserType());
		coustmerDto.setRoleId(roleId);
		logger.info(" Excecuttion Object  LoggingAndRgisteraionController :: coustmerDto"+coustmerDto);
		System.out.println("coustmerDto"+coustmerDto);
		loggingAndRgisteraionService.Registercoustomer(coustmerDto);
		logger.info(" Excecuttion End  LoggingAndRgisteraionController :: i will come into cst");
		return new ModelAndView("message");
	}

	
	@RequestMapping("/viewcust")
	public ModelAndView listCoustomer() {
		logger.info(" Excecuttion start  LoggingAndRgisteraionController :: View of Customer");
		List<Coustmer> coustmerDtos = loggingAndRgisteraionService.listCoustomer();
		logger.info(" Excecuttion End  LoggingAndRgisteraionController :: coustmerDtos"+coustmerDtos);
		return new ModelAndView("listcoustomer", "coustmerDtos", coustmerDtos);
	}

	@RequestMapping("/cstdeletedirect")
	public ModelAndView DeleteCoustomer(CoustmerDto coustmerDto) {
		loggingAndRgisteraionService.Deletecoustomer(coustmerDto);
		logger.info(" Excecuttion start  LoggingAndRgisteraionController :: Delete for Customer");
		return new ModelAndView("redirect:viewcust.html");
	}
	
}
