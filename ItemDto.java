package com.ofos.item.dto;

public class ItemDto {

	private Long id;
	private String itemname;
	private Long price;
	private Long avilabelstock;

	
	@Override
	public String toString() {
		return "ItemDto [id=" + id + ", itemname=" + itemname + ", price=" + price + ", avilabelstock=" + avilabelstock
				+  "]";
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

	public Long getAvilabelstock() {
		return avilabelstock;
	}

	public void setAvilabelstock(Long avilabelstock) {
		this.avilabelstock = avilabelstock;
	}


}
