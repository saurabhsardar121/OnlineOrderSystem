package com.ofos.item.service;

import java.util.List;

import com.ofos.item.dto.ItemDto;
import com.ofos.item.dto.SelecteItemDto;
import com.ofos.item.entity.Item;


public interface ItemService {
	
	public abstract void addItem(ItemDto itemDto)throws Exception;
	public List<Item> ListItem() throws Exception;
    public abstract void editItem (ItemDto itemDto) throws Exception;
    public abstract void deleteItem(ItemDto itemDto) throws Exception;
    
    public void selectedItemdelete(SelecteItemDto selecteItemDto) throws Exception;

}
