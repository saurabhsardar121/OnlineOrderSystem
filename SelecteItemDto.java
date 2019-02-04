package com.ofos.item.dto;

import java.util.Date;

public class SelecteItemDto {
	Long id;
	String itemname;
	Long price;
	Long totalprice;
	Long coustmer_id;
	Long itemId;
	Date buy_event;
	
	@Override
	public String toString() {
		return "SelecteItemDto [id=" + id + ", itemname=" + itemname + ", price=" + price + ", totalprice=" + totalprice
				+ ", coustmer_id=" + coustmer_id + ", itemId=" + itemId + ", buy_event=" + buy_event + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Long totalprice) {
		this.totalprice = totalprice;
	}
	public Long getCoustmer_id() {
		return coustmer_id;
	}
	public void setCoustmer_id(Long coustmer_id) {
		this.coustmer_id = coustmer_id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Date getBuy_event() {
		return buy_event;
	}
	public void setBuy_event(Date buy_event) {
		this.buy_event = buy_event;
	}

}
