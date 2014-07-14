package com.riviere.moomoney.util;

import java.io.Serializable;

/**
 * A url holder utility
 * 
 * @author Richard Riviere
 * @date 08/05/2014
 */
public class URLHolder implements Serializable {
	
	private static final long serialVersionUID = 7321158656424851941L;
	private String url;
	private boolean included;
	private String servletPath;
	
	public URLHolder(String url, boolean included) {
		this.url = url;
		this.included = included;		
	}
	
	public URLHolder(String url, String servletPath, boolean included) {
		this.url = url;
		this.included = included;
		this.servletPath = servletPath;
	}
	
	public boolean isIncluded() {
		return included;
	}

	public void setIncluded(boolean included) {
		this.included = included;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
	
	public String getServletPath() {
		return servletPath;
	}

	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}

	public String toString() {
		StringBuffer str = new StringBuffer(this.getClass().toString() + " [");
		
		str.append("\n url:" + this.url);
		str.append("\n servletPath:" + this.servletPath);
		str.append("\n includes:" + this.included);		
		str.append(" ]");
		return str.toString();
		
	}
}
