package com.borges.projectPDV.resources.exception;

import java.io.Serializable;

public class FieldMessage  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String fildName;
	private String message;
	
	public FieldMessage() {}

	public FieldMessage(String fildName, String message) {
		super();
		this.fildName = fildName;
		this.message = message;
	}

	public String getFieldName() {
		return fildName;
	}

	public void setFieldName(String fildName) {
		this.fildName = fildName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
