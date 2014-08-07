package com.riviere.moomoney.domain;

public class Tag {
	private Long id;
	private String itemCode;
	private String itemDescription;
	private String buttonType;
	
		
	public Tag() {
		super();
	}

	public Tag(Long id, String itemCode, String itemDescription,
			String buttonType) {
		super();
		this.id = id;
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.buttonType = buttonType;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getButtonType() {
		return buttonType;
	}
	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	
	
}
