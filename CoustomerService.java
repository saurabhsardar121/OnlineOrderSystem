package com.ofos.customer.service;

import java.util.List;

import com.ofos.customer.dto.CoustmerDto;
import com.ofos.customer.entity.Coustmer;
import com.ofos.item.dto.SelecteItemDto;
import com.ofos.item.entity.Item;
import com.ofos.item.entity.SelecteItem;

public interface CoustomerService {
	
	public  abstract Item getItemById(Long id) throws Exception;

	public abstract Coustmer getCustumerByEmailId(String userName) throws Exception;
	
	public boolean InsertedSelectedItems(Item item,Long cid);
	
	public List<SelecteItemDto> getAllSelectedItems(Long[] allselids,Long loggedInUserId) throws Exception;

	public abstract CoustmerDto findCustumerById(Long id);

	public abstract 	List<SelecteItemDto> getSelectedItemById(Long cid) throws Exception;

	public abstract void sendDetailsOnMail(List<SelecteItemDto> selecteItemDto, String firstname, String lastname, String address, String city, String email);

	public abstract Long getLoggedInUserIdByEmail(String email);

	public abstract String getUserTypeByLoggedInUserId(Long loggedInUserId);

	public abstract List<SelecteItemDto> viewOfallorederdteails();

	public abstract void enablestatus(String useremail);
	
}
