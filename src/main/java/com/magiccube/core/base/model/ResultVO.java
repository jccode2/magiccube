package com.magiccube.core.base.model;

/**
 * @author Xingling
 * @since JDK1.6
 * @history 2013-1-6 Xingling build
 */
public class ResultVO extends BaseVO {
	private boolean success;
	private String message;
	private int orderId;

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
	
	
	
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer(128);
		sb.append("ResultVO=\n");
		sb.append("    success:"+success+"\n");
		sb.append("    message:"+message+"\n" );
		sb.append("    orderId:"+orderId);
		return sb.toString();
	}

}
