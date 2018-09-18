/**
 * 
 */
package com.pris.machine.transaction.entity;

/**
 * @author LO
 *
 */
public class ApplyEntity {
	private String applyId;
	private String userId;
	private boolean accept;

	public ApplyEntity(String applyId, String userId) {
		super();
		this.applyId = applyId;
		this.userId = userId;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ApplyEntity [applyId=" + applyId + ", userId=" + userId + ", accept=" + accept + "]";
	}

}
