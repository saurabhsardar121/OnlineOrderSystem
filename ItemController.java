package com.ofos.item.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ofos.customer.controller.CustomerController;
import com.ofos.item.dto.ItemDto;
import com.ofos.item.dto.SelecteItemDto;
import com.ofos.item.entity.Item;
import com.ofos.item.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemService;
	private final static Logger logger = Logger.getLogger(ItemController.class);

	@RequestMapping("/Viewitem")
	public ModelAndView listitem() {
		logger.info(" Excecuttion start  ItemController :: Inside the list of item ");
		List<Item> itemDtos = null;
		try {
			itemDtos = itemService.ListItem();
			logger.info(" Excecuttion Object  ItemController :: itemDtos"+itemDtos);
		} catch (Exception e) {
			logger.error(" Eception Occured  ItemController::loggerMessageTesting  ", e);		}
		return new ModelAndView("listitem", "itemDtos", itemDtos);
	}

	@RequestMapping("/addredirect")
	public ModelAndView adding() {
		return new ModelAndView("Add");
	}

	@RequestMapping("/add")
	public ModelAndView addItem(ItemDto itemDto) {
		logger.info(" Excecuttion start  ItemController :: Inside Controller ");
		try {
			itemService.addItem(itemDto);
		} catch (Exception e) {
			logger.error(" Eception Occured  ItemController::loggerMessageTesting  ", e);
		}
		return new ModelAndView("redirect:Viewitem.html");
	}

	@RequestMapping("deletedirect")
	public ModelAndView deletitems(ItemDto itemdto) {

		try {
			itemService.deleteItem(itemdto);
			logger.info(" Excecuttion start  ItemController :: Delete  the data item");
		} catch (Exception e) {
			logger.error(" Eception Occured  ItemController::loggerMessageTesting  ", e);
			}
		return new ModelAndView("redirect:Viewitem.html");
	}

	@RequestMapping("/editredirect")
	public ModelAndView redirectedititems(Long id, String itemname, Long price, Long avilabelstock) {
		logger.info(" Excecuttion start  ItemController :: Inside the editor");
		ItemDto itemdto = new ItemDto();
		itemdto.setId(id);
		itemdto.setItemname(itemname);
		itemdto.setPrice(price);
		itemdto.setAvilabelstock(avilabelstock);
		logger.info(" Excecuttion Object  ItemController :: itemdto"+itemdto);
		return new ModelAndView("Edit", "itemdto", itemdto);
	}

	@RequestMapping("/edit")
	public ModelAndView editItems(@ModelAttribute("command") ItemDto itemDto, BindingResult result) throws Exception {
		logger.info(" Excecuttion start  ItemController :: Updated Values"+itemDto);
		itemService.editItem(itemDto);
		logger.info(" Excecuttion End  ItemController :: Updated Values");
		return new ModelAndView("redirect:Viewitem.html");

	}

	@RequestMapping("seleteddelete")
	public ModelAndView deletitems(SelecteItemDto selecteItemDto) {

		try {
			itemService.selectedItemdelete(selecteItemDto);
			logger.info(" Excecuttion start  ItemController :: delete  the data item");
		} catch (Exception e) {
			logger.error(" Eception Occured  ItemController::loggerMessageTesting  ", e);
			}
		return new ModelAndView("redirect:allitemid.html");
	}

}
