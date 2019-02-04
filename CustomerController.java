package com.ofos.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.constant.Constant;
import com.ofos.customer.dto.CoustmerDto;
import com.ofos.customer.entity.Coustmer;
import com.ofos.customer.service.CoustomerService;
import com.ofos.item.common.CommonUtility;
import com.ofos.item.dto.SelecteItemDto;
import com.ofos.item.entity.Item;
import com.ofos.item.entity.SelecteItem;
import com.ofos.item.service.ItemService;
import com.ofos.registerationandlogging.service.LoggingAndRgisteraionService;


@Controller
public class CustomerController {

	@Autowired
	CoustomerService coustomerService;

	@Autowired
	LoggingAndRgisteraionService loggingAndRgisteraionService;
	
	@Autowired
    HttpServletRequest request;

	@Autowired
	ItemService itemService;
	
	private final static Logger logger = Logger.getLogger(CustomerController.class);
	
	@RequestMapping("/logg")
	public ModelAndView logCoustomer() {
		return new ModelAndView("logging");
	}

	@RequestMapping("/loging")
	public ModelAndView Logging(CoustmerDto coustmerDto) throws Exception{
		logger.info(" Excecuttion start  CustomerController :: i am logging here ");
		boolean showMenu = false;
		HttpSession session =request.getSession();
		if (session != null) {
			session = request.getSession();
			session.invalidate();
			session = request.getSession();
		}
		Map<String, Object> model = new HashMap<String, Object>();

		Long loggedInUserId = getLoggedInUserIdByEmail(coustmerDto.getEmail());
        
		String useremail = request.getParameter("email");
		
		String userType = getUserTypeByLoggedInUserId(loggedInUserId);
		
		logger.info(" Excecuttion start  CustomerController :: please logging ");

		boolean status = loggingAndRgisteraionService.LoggingCoustomer(coustmerDto);

		if (status) { 
			List<Item> itemDtos = null;
			try {
				session.setAttribute("email", coustmerDto.getEmail().trim());
				session.setAttribute("loggedInUserId", loggedInUserId);
				coustomerService.enablestatus(useremail);
				itemDtos = itemService.ListItem();
				logger.info(" Excecuttion Object  CustomerController :: itemDtos"+itemDtos);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
			if (userType.equals("admin")) {
				showMenu = true;
			}
			model.put("itemDtos", itemDtos);
			model.put("showMenu", showMenu);
			logger.info(" Excecuttion End CustomerController :: Coming to ListItem");
			return new ModelAndView("listitem", model);
		} else {
			return new ModelAndView("logging", "message", Constant.loginFailed);
		}
	}


	@RequestMapping("/allitemid")
	public ModelAndView OrederItems(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("email")== null || session.getAttribute("email").equals("") ) {
			return new ModelAndView("login", "message",
					"Invalid Session. Please login first.");
		} else {
		try {
			Long[] allselids = CommonUtility.convertToIntarray(request.getParameterValues("itemid"));
			boolean itemsaved = saveSelectedItems(allselids);

			Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
			System.out.println("loggedInUserId " + loggedInUserId);
			
			List<SelecteItemDto> selItems =getAllSelectedItems(allselids, loggedInUserId);
			logger.info(" Excecuttion Object  CustomerController :: selItems"+selItems);
			
			SelecteItemDto selecteItemDto = selItems.get(0);
			Long cid = selecteItemDto.getCoustmer_id();

			CoustmerDto custumerDto = coustomerService.findCustumerById(cid);
			logger.info(" Excecuttion FirstName  CustomerController :: FirstName"+custumerDto.getFirstname());

			Long totalPrice = getTotalPrice(selItems);

			model.put("selItems", selItems);
			model.put("totalprice", totalPrice);
			model.put("cid", cid);
			model.put("firstname", custumerDto.getFirstname());

			logger.info(" Excecuttion FirstName  CustomerController :: selItems"+selItems);
			logger.info(" Excecuttion totalPrice  CustomerController :: totalPrice"+totalPrice);
   
		   } catch (Exception e) {
			logger.error(" Eception Occured  AA::loggerMessageTesting  ", e);
		   }
		}
		return new ModelAndView("selecteditems", model);
	}
	private String getUserTypeByLoggedInUserId(Long loggedInUserId) {
		return coustomerService.getUserTypeByLoggedInUserId(loggedInUserId);
	}
	
	private Long getLoggedInUserIdByEmail(String email) {
		return coustomerService.getLoggedInUserIdByEmail(email);
	}

	public Long getTotalPrice(List<SelecteItemDto> selItems) {
		Long price = 0L;
		for (SelecteItemDto selecteItemDto : selItems) {
			price = price + (selecteItemDto.getPrice());
		}
		return price;
	}

	public List<SelecteItemDto> getAllSelectedItems(Long[] allselids, Long loggedInUserId) throws Exception {

		return coustomerService.getAllSelectedItems(allselids, loggedInUserId);
	}

	public boolean saveSelectedItems(Long[] allselids) throws Exception {

		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("email");
		Coustmer custemer = coustomerService.getCustumerByEmailId(userName);
		
		Long loggedInUserId = custemer.getId();
		for (Long id : allselids) {

			Item item = coustomerService.getItemById(id);
			logger.info(" Excecuttion Object  CustomerController :: item"+item);
                        
			boolean isSaved = saveSelectedCustumerItem(item, loggedInUserId);
		}
		return false;
	}

	private boolean saveSelectedCustumerItem(Item item, Long cid) {
		return coustomerService.InsertedSelectedItems(item, cid);
	}

	@RequestMapping("/order")
	public ModelAndView order(HttpServletRequest request) throws Exception {

		Long cid = Long.parseLong(request.getParameter("cid"));

		logger.info(" Excecuttion Object  CustomerController :: cid"+cid);

		CoustmerDto dto = coustomerService.findCustumerById(cid);
		List<SelecteItemDto> selecteItemDtoList = coustomerService.getSelectedItemById(cid);
	
		logger.info(" Excecuttion Object  CustomerController :: selecteItemDtoList"+selecteItemDtoList);

		List<Object> confirmOrder = conformOrder(selecteItemDtoList, dto.getFirstname(), dto.getLastname(),
				dto.getAddress(), dto.getCity(), dto.getEmail());

		return new ModelAndView("order");
	}

	private List<Object> conformOrder(List<SelecteItemDto> selecteItemDto, String firstname, String lastname,
			String address, String city, String email) {
		coustomerService.sendDetailsOnMail(selecteItemDto, firstname, lastname, address, city, email);
		return null;
	}
	
	@RequestMapping("/viewhistory")
	public ModelAndView viewhistory(SelecteItemDto selecteItemDto) throws Exception {
		
		List<SelecteItemDto> selItems =coustomerService.viewOfallorederdteails();
		
		return new ModelAndView("viewOrder" ,"selItems",selItems);
		}

}
