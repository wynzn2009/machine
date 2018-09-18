/**
 * 
 */
package com.pris.machine.transaction.sm.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;

import com.pris.machine.transaction.action.TransAction1;
import com.pris.machine.transaction.action.TransAction2;
import com.pris.machine.transaction.sm.guard.AcceptGuard;
import com.pris.machine.transaction.sm.guard.RejectedGuard;
import com.pris.machine.transaction.sm.listener.StateMachineEventListener;
import com.pris.machine.transaction.sm.state.TrsEvents;
import com.pris.machine.transaction.sm.state.TrsStates;

/**
 * @author LO
 *
 */
@Configuration
@EnableStateMachineFactory(name = "transSM")
public class TransStateMachineConfig extends EnumStateMachineConfigurerAdapter<TrsStates, TrsEvents> {
	@Override
	public void configure(StateMachineStateConfigurer<TrsStates, TrsEvents> states) throws Exception {
		states.withStates().initial(TrsStates.DRAFT).choice(TrsStates.CHOICE).states(EnumSet.allOf(TrsStates.class));
	}
	
	@Override
	public void configure(StateMachineTransitionConfigurer<TrsStates, TrsEvents> transitions) throws Exception {
		transitions
		.withExternal().source(TrsStates.DRAFT).target(TrsStates.DRAFT_REG_WAITING).event(TrsEvents.REGISTER).and()
		.withExternal().source(TrsStates.DRAFT_REG_WAITING).target(TrsStates.REGISTERED).event(TrsEvents.REPLY).guard(acceptGuard()).and()
		.withExternal().source(TrsStates.DRAFT_REG_WAITING).target(TrsStates.DRAFT).event(TrsEvents.REPLY).guard(rejectedGuard()).and()
		.withExternal().source(TrsStates.REGISTERED).target(TrsStates.REG_REL_WAITING).event(TrsEvents.RELEASE).action(transAction1(), transAction2()).and()
		.withExternal().source(TrsStates.REG_REL_WAITING).target(TrsStates.RELEASED).event(TrsEvents.REPLY).guard(acceptGuard()).action(transAction1()).and()
		.withExternal().source(TrsStates.REG_REL_WAITING).target(TrsStates.REGISTERED).event(TrsEvents.REPLY).guard(rejectedGuard()).action(transAction1()).and()
//		.withExternal().source(TrsStates.REG_REL_WAITING).target(TrsStates.CHOICE).event(TrsEvents.REPLY).and()
//		.withChoice().source(TrsStates.CHOICE).first(TrsStates.RELEASED, acceptGuard(),transAction1()).last(TrsStates.REGISTERED,transAction1()).and()
		.withExternal().source(TrsStates.RELEASED).target(TrsStates.REL_ABL_WAITING).event(TrsEvents.ABOLISH).and()
		.withExternal().source(TrsStates.REL_ABL_WAITING).target(TrsStates.ABOLISHED).event(TrsEvents.REPLY).guard(acceptGuard()).and()
		.withExternal().source(TrsStates.REL_ABL_WAITING).target(TrsStates.RELEASED).event(TrsEvents.REPLY).guard(rejectedGuard()).and()
		.withExternal().source(TrsStates.ABOLISHED).target(TrsStates.ABL_CNC_WAITING).event(TrsEvents.CANCEL).and()
		.withExternal().source(TrsStates.ABL_CNC_WAITING).target(TrsStates.CANCELLED).event(TrsEvents.REPLY).guard(acceptGuard()).and()
		.withExternal().source(TrsStates.ABL_CNC_WAITING).target(TrsStates.ABOLISHED).event(TrsEvents.REPLY).guard(rejectedGuard()).and()
		.withExternal().source(TrsStates.REGISTERED).target(TrsStates.REG_CNC_WAITING).event(TrsEvents.CANCEL).and()
		.withExternal().source(TrsStates.REG_CNC_WAITING).target(TrsStates.REGISTERED).event(TrsEvents.REPLY).guard(rejectedGuard()).and()
		.withExternal().source(TrsStates.REG_CNC_WAITING).target(TrsStates.CANCELLED).event(TrsEvents.REPLY).guard(acceptGuard()).and()
		.withExternal().source(TrsStates.RELEASED).target(TrsStates.REL_MNT_CHANGING).event(TrsEvents.MNT_CHANGE).and()
		.withExternal().source(TrsStates.REL_MNT_CHANGING).target(TrsStates.RELEASED).event(TrsEvents.REPLY).guard(acceptGuard()).and()
		.withExternal().source(TrsStates.RELEASED).target(TrsStates.REL_CNT_CHANGING).event(TrsEvents.CNT_CHANGE).and()
		.withExternal().source(TrsStates.REL_CNT_CHANGING).target(TrsStates.RELEASED).event(TrsEvents.REPLY).guard(acceptGuard()).and()
		.withExternal().source(TrsStates.VERSION_REL_WAITING).target(TrsStates.RELEASED).event(TrsEvents.REPLY).guard(acceptGuard()).and()
		.withExternal().source(TrsStates.VERSION_REL_WAITING).target(TrsStates.VERSION_CANCELLED).event(TrsEvents.REPLY).guard(rejectedGuard()).and();
	}

	@Override
	public void configure(StateMachineConfigurationConfigurer<TrsStates, TrsEvents> config) throws Exception {
		config.withConfiguration().listener(listener());
	}

	@Bean
	public StateMachineListener<TrsStates, TrsEvents> listener() {
		return stateMachineEventListener();
	}

	@Bean
	public AcceptGuard acceptGuard() {
		return new AcceptGuard();
	}

	@Bean
	public RejectedGuard rejectedGuard() {
		return new RejectedGuard();
	}

	@Bean
	public StateMachineEventListener stateMachineEventListener() {
		return new StateMachineEventListener();
	}

	@Bean
	public TransAction1 transAction1() {
		return new TransAction1();
	}

	@Bean
	public TransAction2 transAction2() {
		return new TransAction2();
	}
}
