package com.riviere.moomoney.domain;

import java.util.Date;

/**
 * @author rriviere
 *
 */
public class Transaction extends AbstractDomainObject {

	private static final long serialVersionUID = -1289753822767914393L;
	private Date tranDate;
	private String tranDescription;
	private Double debit;
	private Double credit;
	private String tranCategoryCode;
	
	/**
	 * @return the tranDate
	 */
	public Date getTranDate() {
		return tranDate;
	}
	/**
	 * @param tranDate the tranDate to set
	 */
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}
	/**
	 * @return the tranDescription
	 */
	public String getTranDescription() {
		return tranDescription;
	}
	/**
	 * @param tranDescription the tranDescription to set
	 */
	public void setTranDescription(String tranDescription) {
		this.tranDescription = tranDescription;
	}
	/**
	 * @return the debit
	 */
	public Double getDebit() {
		return debit;
	}
	/**
	 * @param debit the debit to set
	 */
	public void setDebit(Double debit) {
		this.debit = debit;
	}
	/**
	 * @return the credit
	 */
	public Double getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer();
		bf.append("FinancialTransaction[");
		bf.append(" tranDate: " + tranDate);
		bf.append(" tranDescription: " + tranDescription);
		bf.append(" debit: " + debit);
		bf.append(" credit: " + credit);
		bf.append(" ]");
		return bf.toString();
	}
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
	
}
