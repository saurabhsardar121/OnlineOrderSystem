package com.ofos.item.dao;

import java.util.List;

import com.ofos.item.entity.Item;
import com.ofos.item.entity.SelecteItem;


public interface ItemDao {
	
	public abstract void addItem(Item item) throws Exception;
	public List<Item> ListItem() throws Exception;
    public abstract void editItem (Item item) throws Exception;
    public abstract void deleteItem(Item item) throws Exception;
    
    public void selectedItemdelete(SelecteItem selecteItem) throws Exception;


}

