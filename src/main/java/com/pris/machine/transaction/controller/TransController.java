/**
 * 
 */
package com.pris.machine.transaction.controller;

import javax.annotation.Resource;

import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultExtendedState;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pris.machine.transaction.entity.ApplyEntity;
import com.pris.machine.transaction.entity.TransactionEntity;
import com.pris.machine.transaction.sm.state.TrsEvents;
import com.pris.machine.transaction.sm.state.TrsStates;
import com.pris.machine.transaction.sm.utils.ConstVars;

/**
 * @author LO
 *
 */
@RestController
public class TransController {
	@Resource(name = "transSM")
	private StateMachineFactory<TrsStates, TrsEvents> factory;

	@RequestMapping("/release")
	@ResponseBody
	public void testMachine() {
		TransactionEntity trans = new TransactionEntity("1", TrsStates.REGISTERED, "wangyuning");
		ApplyEntity apply = new ApplyEntity("T11", "chichao");
		StateMachine<TrsStates, TrsEvents> stateMachine = build(trans, apply);
		stateMachine.sendEvent(TrsEvents.RELEASE);
	}

	@RequestMapping("/release/accepted")
	@ResponseBody
	public void testMachineAccepted() {
		TransactionEntity trans = new TransactionEntity("1", TrsStates.REG_REL_WAITING, "wangyuning");
		ApplyEntity apply = new ApplyEntity("T11", "chichao");
		apply.setAccept(true);
		StateMachine<TrsStates, TrsEvents> stateMachine = build(trans, apply);
		stateMachine.sendEvent(TrsEvents.REPLY);
	}

	@RequestMapping("/release/rejected")
	@ResponseBody
	public void testMachineRejected() {
		TransactionEntity trans = new TransactionEntity("1", TrsStates.REG_REL_WAITING, "wangyuning");
		ApplyEntity apply = new ApplyEntity("T11", "chichao");
		apply.setAccept(false);
		StateMachine<TrsStates, TrsEvents> stateMachine = build(trans, apply);
		stateMachine.sendEvent(TrsEvents.REPLY);
	}

	private StateMachine<TrsStates, TrsEvents> build(TransactionEntity trans, ApplyEntity apply) {
		StateMachine<TrsStates, TrsEvents> t = factory.getStateMachine(trans.getTransId());
		t.stop();
		t.getStateMachineAccessor().doWithAllRegions(a -> {
			ExtendedState extendedState = new DefaultExtendedState();
			extendedState.getVariables().put(ConstVars.TRANS_VAR_CODE, trans);
			extendedState.getVariables().put(ConstVars.APPLY_VAR_CODE, apply);
			a.resetStateMachine(new DefaultStateMachineContext<TrsStates, TrsEvents>(trans.getStates(), null,
					null, extendedState));
		});
		t.start();
		return t;
	}
}
