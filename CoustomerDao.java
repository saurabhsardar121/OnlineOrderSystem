package com.ofos.customer.dao;

import java.util.List;

import com.ofos.customer.entity.Coustmer;
import com.ofos.item.dto.SelecteItemDto;
import com.ofos.item.entity.Item;
import com.ofos.item.entity.SelecteItem;

public interface CoustomerDao {

	public boolean InsertedSelectedItems(Item item,Long cid);
	
	public  abstract Item getItemById(Long id) throws Exception;
	
	public Coustmer getCustumerByEmailId(String email) throws Exception ;

	public List<SelecteItemDto> getAllSelectedItems(Long[] allselids,Long loggedInUserId) throws Exception;
	
	public abstract Coustmer findCustumerById(Long id) ;

	public abstract List<SelecteItem> getSelectedItemById(Long cid) throws Exception;
	
	public abstract Long getLoggedInUserIdByEmail(String email);

	public abstract String getUserTypeByLoggedInUserId(Long loggedInUserId);

	public List<SelecteItem> viewOfallorederdteails();

	public List<Item> getCurrentAvailableItemList(List<Long> selectedItemIds);

	public void updateStatusForSelectedItem(List<Long> selectedItemIds, Long cid);

	public void enablestatus(String useremail);
}
