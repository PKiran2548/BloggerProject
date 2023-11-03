package com.bikkadit.helper;

public class FileResponse {
	
	private String fileName ;
	private String massage ;
	public FileResponse(String fileName, String massage) {
		super();
		this.fileName = fileName;
		this.massage = massage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	

}
