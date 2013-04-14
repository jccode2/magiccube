package com.magiccube.core.base.model;

/**
 * @author Xingling
 * @since JDK1.6
 * @history 2013-1-6 Xingling build
 */
public class ResultVO extends BaseVO {
	private boolean success;
	private String message;

	public ResultVO(boolean s, String m) {
		success = s;
		message = m;
	}

	public ResultVO(String m) {
		success = false;
		message = m;
	}

	public ResultVO() {
		success = true;
		message = "success";
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer(128);
		sb.append("ResultVO=\n");
		sb.append("    success:"+success+"\n");
		sb.append("    message:"+message);
		return sb.toString();
	}

}
