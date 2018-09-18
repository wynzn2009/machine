/**
 * 
 */
package com.pris.machine.transaction.action;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import com.pris.machine.transaction.sm.state.TrsEvents;
import com.pris.machine.transaction.sm.state.TrsStates;

/**
 * @author LO
 *
 */
public class TransAction2 implements Action<TrsStates, TrsEvents> {

	@Override
	public void execute(StateContext<TrsStates, TrsEvents> context) {
		// TODO Auto-generated method stub
		System.out.println("---这里是action，发生异常时处理一下");
	}

}
