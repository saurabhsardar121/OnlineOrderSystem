package com.ofos.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofos.customer.dao.CoustomerDao;
import com.ofos.customer.dto.CoustmerDto;
import com.ofos.customer.entity.Coustmer;
import com.ofos.customer.mailservises.MailService;
import com.ofos.item.dto.SelecteItemDto;
import com.ofos.item.entity.Item;
import com.ofos.item.entity.SelecteItem;

@Service
public class CoustomerServiceImpl implements CoustomerService {

	@Autowired
	CoustomerDao coustomerDao;

	private CoustmerDto entityToDto(Coustmer coustmer) {
		CoustmerDto coustmerDto = new CoustmerDto();
		coustmerDto.setId(coustmer.getId());
		coustmerDto.setFirstname(coustmer.getFirstname());
		coustmerDto.setLastname(coustmer.getLastname());
		coustmerDto.setMobno(coustmer.getMobno());
		coustmerDto.setAddress(coustmer.getAddress());
		coustmerDto.setCity(coustmer.getCity());
		coustmerDto.setEmail(coustmer.getEmail());
		coustmerDto.setPassw(coustmer.getPassw());
		coustmerDto.setRoleId(coustmer.getRoleId());
		coustmerDto.setStatus(coustmer.isStatus());
		return coustmerDto;
	}

	@Override
	public Item getItemById(Long id) throws Exception {
		return coustomerDao.getItemById(id);
	}

	@Override
	public Coustmer getCustumerByEmailId(String userName) throws Exception {
		return coustomerDao.getCustumerByEmailId(userName);
	}

	@Override
	public boolean InsertedSelectedItems(Item item, Long cid) {
		return coustomerDao.InsertedSelectedItems(item, cid);
	}

	public List<SelecteItemDto> getAllSelectedItems(Long[] allselids, Long loggedInUserId) throws Exception {
		return coustomerDao.getAllSelectedItems(allselids, loggedInUserId);

	}

	@Override
	public CoustmerDto findCustumerById(Long id) {
		Coustmer coustmer=coustomerDao.findCustumerById(id);
		CoustmerDto coustmerDto2 = entityToDto(coustmer);
		
		return coustmerDto2;
	}

	@Override
	public List<SelecteItemDto> getSelectedItemById(Long cid) throws Exception {
		List<SelecteItem> selecteItem	= coustomerDao.getSelectedItemById(cid);
		List<SelecteItemDto> selecteItemDto = EntityTodto(selecteItem);
		return selecteItemDto;
	}


	@Override
	public void sendDetailsOnMail(List<SelecteItemDto> selecteItemDto, String firstname, String lastname,
			String address, String city, String email) {
		List<Long> selectedItemIds = new ArrayList<>();
		Long cid = null;
		if (selecteItemDto.size() > 0) {
			SelecteItemDto selecteItemDto11 = null;
			StringBuffer sb = new StringBuffer("Please check your details.: Name :-" + firstname + "  " + lastname
					+ "  Address" + address + " City" + city);
			try {
				if (selecteItemDto.size() != 0) {
					selecteItemDto11 = selecteItemDto.get(0);
				}
				cid = selecteItemDto11.getCoustmer_id();
				Coustmer coustmer = coustomerDao.findCustumerById(cid);
				Long totalPrice = 0L;
				String message = "";
				for (SelecteItemDto selecteItemDto2 : selecteItemDto) {
					selectedItemIds.add(selecteItemDto2.getItemId());
					totalPrice = totalPrice + selecteItemDto2.getPrice();
					message = "Item Name" + selecteItemDto2.getItemname() + "  Price " + selecteItemDto2.getPrice();
					sb.append("\n");
					sb.append(message);
				}
				sb.append("Total Price is " + totalPrice);
				sb.append("order placed");
				System.out.println("fINAL STRING DATA " + sb);
				String subject = "Online_Food_order_system :Order placed";
				final String from = "saurabhsardar121@gmail.com";
				final String pass = "9766399635";
				MailService.send(from, pass, coustmer.getEmail(), subject, new String(sb.toString()));
				updateStatusForSelectedItem(selectedItemIds, cid);
				getCurrentAvailableItemList(selectedItemIds);
			} catch (Exception e) {
				
			}
		}
	}

	private List<Item> getCurrentAvailableItemList(List<Long> selectedItemIds) {
		return coustomerDao.getCurrentAvailableItemList(selectedItemIds);
	}

	private void updateStatusForSelectedItem(List<Long> selectedItemIds, Long cid) {
		// TODO Auto-generated method stub
		coustomerDao.updateStatusForSelectedItem(selectedItemIds, cid);
	}

	@Override
	public Long getLoggedInUserIdByEmail(String email) {
		return coustomerDao.getLoggedInUserIdByEmail(email);
	}

	
	@Override
	public String getUserTypeByLoggedInUserId(Long loggedInUserId) {
		return coustomerDao.getUserTypeByLoggedInUserId(loggedInUserId);
	}
	
	private List<SelecteItemDto> EntityTodto(List<SelecteItem> selectedItemlist) {

		List<SelecteItemDto> selecteItemDtoList = new ArrayList<SelecteItemDto>();
		for (SelecteItem selecteItem : selectedItemlist) {
			SelecteItemDto selecteItemDto = new SelecteItemDto();
			selecteItemDto.setCoustmer_id(selecteItem.getCoustmer_id());
			selecteItemDto.setId(selecteItem.getId());
			selecteItemDto.setItemId(selecteItem.getItemId());
			selecteItemDto.setItemname(selecteItem.getItemname());
			selecteItemDto.setPrice(selecteItem.getPrice());
			selecteItemDto.setBuy_event(selecteItem.getBuy_event());
			selecteItemDto.setTotalprice(selecteItem.getTotalprice());
			selecteItemDtoList.add(selecteItemDto);
		}

		return selecteItemDtoList;
	}

	@Override
	public List<SelecteItemDto> viewOfallorederdteails() {
		List<SelecteItem> selecteItem	= coustomerDao.viewOfallorederdteails();
		List<SelecteItemDto> selecteItemDto1 = EntityTodto(selecteItem);
		
		return selecteItemDto1;
	}

	@Override
	public void enablestatus(String useremail) {
		coustomerDao.enablestatus(useremail);
		
	}

}
