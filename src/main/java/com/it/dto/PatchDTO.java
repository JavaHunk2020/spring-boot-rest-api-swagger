package com.it.dto;

import java.util.List;

public class PatchDTO {
    private int sid;    
	private String attributeName;
	private String value;
	private List<String> values;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "PatchDTO [attributeName=" + attributeName + ", value=" + value + ", values=" + values + "]";
	}

}
