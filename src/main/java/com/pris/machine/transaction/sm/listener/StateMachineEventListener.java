/**
 * 
 */
package com.pris.machine.transaction.sm.listener;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import com.pris.machine.transaction.sm.state.TrsEvents;
import com.pris.machine.transaction.sm.state.TrsStates;

/**
 * @author LO
 *
 */
public class StateMachineEventListener extends StateMachineListenerAdapter<TrsStates, TrsEvents> {
	@Override
	public void stateChanged(State<TrsStates, TrsEvents> from, State<TrsStates, TrsEvents> to) {
		System.out.println("---listener @ stateChanged---");
		System.out.println("---from " + from.toString());
		System.out.println("---to " + to.toString());
	}

	@Override
	public void stateEntered(State<TrsStates, TrsEvents> state) {
		System.out.println("---listener @ stateEntered---");
		System.out.println("---state " + state.toString());
	}

	@Override
	public void stateExited(State<TrsStates, TrsEvents> state) {
		System.out.println("---listener @ stateExited---");
		System.out.println("---state " + state.toString());
	}

	@Override
	public void eventNotAccepted(Message<TrsEvents> event) {
		System.out.println("---listener @ eventNotAccepted---");
		System.out.println("---event " + event.toString());
	}

	@Override
	public void transition(Transition<TrsStates, TrsEvents> transition) {
		System.out.println("---listener @ transition---");
		System.out.println("---transition " + transition.toString());
	}

	@Override
	public void transitionStarted(Transition<TrsStates, TrsEvents> transition) {
		System.out.println("---listener @ transitionStarted---");
		System.out.println("---transition " + transition.toString());
	}

	@Override
	public void transitionEnded(Transition<TrsStates, TrsEvents> transition) {
		System.out.println("---listener @ transitionEnded---");
		System.out.println("---transition " + transition.toString());
	}

	@Override
	public void stateMachineStarted(StateMachine<TrsStates, TrsEvents> stateMachine) {
		System.out.println("---listener @ stateMachineStarted---");
		System.out.println("---stateMachine " + stateMachine.toString());
	}

	@Override
	public void stateMachineStopped(StateMachine<TrsStates, TrsEvents> stateMachine) {
		System.out.println("---listener @ stateMachineStopped---");
		System.out.println("---stateMachine " + stateMachine.toString());
	}

	@Override
	public void stateMachineError(StateMachine<TrsStates, TrsEvents> stateMachine, Exception exception) {
		System.out.println("---listener @ stateMachineError---");
		System.out.println("---stateMachine " + stateMachine.toString());
		System.out.println("---exception " + exception.getMessage());
	}

	@Override
	public void extendedStateChanged(Object key, Object value) {
		System.out.println("---listener @ extendedStateChanged---");
		System.out.println("---key " + key.toString());
		System.out.println("---value " + value.toString());
	}

	@Override
	public void stateContext(StateContext<TrsStates, TrsEvents> stateContext) {
		System.out.println("---listener @ stateContext---");
		System.out.println("stateContext " + stateContext.toString());
	}
}
