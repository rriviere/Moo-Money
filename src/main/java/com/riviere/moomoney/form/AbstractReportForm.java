package com.riviere.moomoney.form;


/**
 * @author Richard Riviere
 * @date 12/02/2014
 */
public abstract class AbstractReportForm extends AbstractForm {

	private static final long serialVersionUID = 4530495868678151686L;

	private String store;

	/**
	 * @return the store
	 */
	public String getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(String store) {
		this.store = store;
	}
}
