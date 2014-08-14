package com.riviere.moomoney.domain;

public class TransactionCategoryKeyword extends AbstractDomainObject {

	private static final long serialVersionUID = 1528882750478018552L;
	private String tranCategoryCode;
	private String tranCategoryKeyword;
	private int tranCategoryKeywordId;
	private String buttonType;
	
	/**
	 * @return the tranCategoryCode
	 */
	public String getTranCategoryCode() {
		return tranCategoryCode;
	}
	/**
	 * @param tranCategoryCode the tranCategoryCode to set
	 */
	public void setTranCategoryCode(String tranCategoryCode) {
		this.tranCategoryCode = tranCategoryCode;
	}
	
	/**
	 * @return the buttonType
	 */
	public String getButtonType() {
		return buttonType;
	}
	/**
	 * @param buttonType the buttonType to set
	 */
	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	/**
	 * @return the tranCategoryKeyword
	 */
	public String getTranCategoryKeyword() {
		return tranCategoryKeyword;
	}
	/**
	 * @param tranCategoryKeyword the tranCategoryKeyword to set
	 */
	public void setTranCategoryKeyword(String tranCategoryKeyword) {
		this.tranCategoryKeyword = tranCategoryKeyword;
	}
	/**
	 * @return the tranCategoryKeywordId
	 */
	public int getTranCategoryKeywordId() {
		return tranCategoryKeywordId;
	}
	/**
	 * @param tranCategoryKeywordId the tranCategoryKeywordId to set
	 */
	public void setTranCategoryKeywordId(int tranCategoryKeywordId) {
		this.tranCategoryKeywordId = tranCategoryKeywordId;
	}
	
	
	

}
