package com.riviere.moomoney.domain;

public class TransactionCategory extends AbstractDomainObject {

	private static final long serialVersionUID = 1528882750478018552L;
	private String tranCategoryCode;
	private String tranCategoryDesc;
	private int tranCategorySeq;
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
	 * @return the tranCategoryDesc
	 */
	public String getTranCategoryDesc() {
		return tranCategoryDesc;
	}
	/**
	 * @param tranCategoryDesc the tranCategoryDesc to set
	 */
	public void setTranCategoryDesc(String tranCategoryDesc) {
		this.tranCategoryDesc = tranCategoryDesc;
	}
	/**
	 * @return the tranCategorySeq
	 */
	public int getTranCategorySeq() {
		return tranCategorySeq;
	}
	/**
	 * @param tranCategorySeq the tranCategorySeq to set
	 */
	public void setTranCategorySeq(int tranCategorySeq) {
		this.tranCategorySeq = tranCategorySeq;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer transactionCategory = new StringBuffer();
		transactionCategory.append(getClass().getSimpleName());
		transactionCategory.append(" [ ");
		transactionCategory.append(" tranCategoryCode : " + tranCategoryCode);
		transactionCategory.append(" tranCategoryDesc : " + tranCategoryDesc);
		transactionCategory.append(" tranCategorySeq : " + tranCategorySeq);
		transactionCategory.append(" buttonType : " + buttonType);
		transactionCategory.append(" ] ");
		return transactionCategory.toString();
	}	

}
