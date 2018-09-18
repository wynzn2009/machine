/**
 * 
 */
package com.pris.machine.demo.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import com.pris.machine.demo.state.Events;
import com.pris.machine.demo.state.States;

/**
 * @author LO
 *
 */
@Configuration
@EnableStateMachineFactory(name = "machine1")
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
		states.withStates().initial(States.UNPAID).states(EnumSet.allOf(States.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		transitions.withExternal().source(States.UNPAID).target(States.WAITING).event(Events.PAY).and()
				.withExternal().source(States.WAITING).target(States.DONE).event(Events.RECEIVE);
	}

	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
		config.withConfiguration().listener(listener());
	}

	@Bean
	public StateMachineListener<States, Events> listener() {
		return new StateMachineListenerAdapter<States, Events>() {

			@Override
			public void transition(Transition<States, Events> transition) {
				if (transition.getTarget().getId() == States.UNPAID) {
					System.out.println("订单创建，待支付");
					return;
				}

				if (transition.getSource().getId() == States.UNPAID
						&& transition.getTarget().getId() == States.WAITING) {
					System.out.println("用户完成支付，待收货");
					return;
				}

				if (transition.getSource().getId() == States.WAITING
						&& transition.getTarget().getId() == States.DONE) {
					System.out.println("用户已收货，订单完成");
					return;
				}
			}

		};
	}

}
