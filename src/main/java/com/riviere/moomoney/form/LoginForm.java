package com.riviere.moomoney.form;


/**
 * Domain object to store user authentication details
 *  
 * @author Richard Riviere
 * @date 21/11/2013
 */
public class LoginForm extends AbstractForm {
	
	private static final long serialVersionUID = 4855216914882190286L;
	public String j_username;
	public String j_password;
	public String project;
	
	public String getJ_password() {
		return j_password;
	}
	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}
	public String getJ_username() {
		return j_username;
	}
	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}
	public String getProject() {
		return project;
	}
	/**
	 * @param myStore the myStore to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("j_username=");
		buffer.append(j_username);
		buffer.append(",j_password=");
		buffer.append(j_password);
		return buffer.toString();
	}	
}
