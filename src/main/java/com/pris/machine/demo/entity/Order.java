/**
 * 
 */
package com.pris.machine.demo.entity;

import com.pris.machine.demo.state.States;

/**
 * @author LO
 *
 */
public class Order {
	private String id;
	private States state;
	private String stateCode;

	public Order(String id, States state, String stateCode) {
		super();
		this.id = id;
		this.state = state;
		this.stateCode = stateCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", state=" + state + ", stateCode="
				+ stateCode + "]";
	}

}
