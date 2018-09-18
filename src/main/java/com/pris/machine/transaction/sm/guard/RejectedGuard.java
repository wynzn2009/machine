/**
 * 
 */
package com.pris.machine.transaction.sm.guard;

import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

import com.pris.machine.transaction.entity.ApplyEntity;
import com.pris.machine.transaction.sm.state.TrsEvents;
import com.pris.machine.transaction.sm.state.TrsStates;
import com.pris.machine.transaction.sm.utils.ConstVars;

/**
 * @author LO
 *
 */
public class RejectedGuard implements Guard<TrsStates, TrsEvents> {

	@Override
	public boolean evaluate(StateContext<TrsStates, TrsEvents> context) {
		ExtendedState extendedState = context.getExtendedState();
		ApplyEntity apply = (ApplyEntity) extendedState.getVariables().get(ConstVars.APPLY_VAR_CODE);
		if (apply == null) {
			throw new RuntimeException();
		}
		return !apply.isAccept();
	}

}
