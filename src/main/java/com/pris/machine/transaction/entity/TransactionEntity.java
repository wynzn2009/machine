/**
 * 
 */
package com.pris.machine.transaction.entity;

import com.pris.machine.transaction.sm.state.TrsStates;

/**
 * @author LO
 *
 */
public class TransactionEntity {
	private String transId;
	private TrsStates states;
	private String userId;

	public TransactionEntity(String transId, TrsStates states, String userId) {
		super();
		this.transId = transId;
		this.states = states;
		this.userId = userId;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public TrsStates getStates() {
		return states;
	}

	public void setStates(TrsStates states) {
		this.states = states;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TransactionEntity [transId=" + transId + ", states=" + states + ", userId=" + userId + "]";
	}

}
