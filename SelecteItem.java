package com.ofos.item.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SelecteItem {
	@Id
	@GeneratedValue
	Long id;
	@Column(name = "item_id")
	Long itemId;
	Long coustmer_id;
	String itemname;
	Long price;
	Long totalprice;
	boolean is_active;
	Date buy_event;

	public Date getBuy_event() {
		return buy_event;
	}

	public void setBuy_event(Date buy_event) {
		this.buy_event = buy_event;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCoustmer_id() {
		return coustmer_id;
	}

	public void setCoustmer_id(Long coustmer_id) {
		this.coustmer_id = coustmer_id;
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

	@Override
	public String toString() {
		return "SelecteItem [id=" + id + ", coustmer_id=" + coustmer_id + ", itemname=" + itemname + ", price=" + price
				+ ", totalprice=" + totalprice + ", is_active=" + is_active + "]";
	}
}
