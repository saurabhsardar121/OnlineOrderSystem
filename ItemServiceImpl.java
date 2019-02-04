package com.ofos.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofos.item.dao.ItemDao;
import com.ofos.item.dto.ItemDto;
import com.ofos.item.dto.SelecteItemDto;
import com.ofos.item.entity.Item;
import com.ofos.item.entity.SelecteItem;


@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemDao itemDao;

	@Override
	public void addItem(ItemDto itemDto) throws Exception {
		Item item = dtoToEntity(itemDto);
		itemDao.addItem(item);
	}

	private Item dtoToEntity(ItemDto itemDto) {
		Item item = new Item();
		item.setId(itemDto.getId());
		item.setItemname(itemDto.getItemname());
		item.setPrice(itemDto.getPrice());
		item.setAvilabelstock(itemDto.getAvilabelstock());
		return item;
	}

	@Override
	public List<Item> ListItem() throws Exception {
		return itemDao.ListItem();
	}

	@Override
	public void editItem(ItemDto itemDto) throws Exception {
		Item item2 = dtoToEntity(itemDto);
		itemDao.editItem(item2);
	}

	@Override
	public void deleteItem(ItemDto itemDto) throws Exception {
		Item item3 = dtoToEntity(itemDto);
		itemDao.deleteItem(item3);
	}

	@Override
	public void selectedItemdelete(SelecteItemDto selecteItemDto) throws Exception {
		SelecteItem selecteItem = dtoToEntity( selecteItemDto);
		itemDao.selectedItemdelete(selecteItem);
	}
	
	private SelecteItem dtoToEntity(SelecteItemDto selecteItemDto) {
		SelecteItem selecteItem = new SelecteItem();
		selecteItem.setId(selecteItemDto.getId());
		selecteItem.setId(selecteItemDto.getCoustmer_id());
		selecteItem.setItemname(selecteItemDto.getItemname());
		selecteItem.setPrice(selecteItemDto.getPrice());
		selecteItem.setItemId(selecteItemDto.getItemId());
		return selecteItem;
		
	}
}
