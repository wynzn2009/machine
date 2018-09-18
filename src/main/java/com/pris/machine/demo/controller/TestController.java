/**
 * 
 */
package com.pris.machine.demo.controller;

import javax.annotation.Resource;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pris.machine.demo.entity.Order;
import com.pris.machine.demo.state.Events;
import com.pris.machine.demo.state.States;

/**
 * @author LO
 *
 */
@RestController
public class TestController {
	// @Autowired
	// private StateMachine<States, Events> stateMachine;
	@Resource(name = "machine1")
	private StateMachineFactory<States, Events> factory;

	@RequestMapping("/test")
	@ResponseBody
	public void testMachine() {
		Order r = new Order("a", States.UNPAID, "1");
		StateMachine<States, Events> stateMachine = build(r);
		stateMachine.sendEvent(Events.PAY);
	}

	@RequestMapping("/test1")
	@ResponseBody
	public void testMachine1() {
		Order r = new Order("ab", States.WAITING, "2");
		StateMachine<States, Events> stateMachine = build(r);
		stateMachine.sendEvent(Events.RECEIVE);
	}

	private StateMachine<States, Events> build(Order order) {
		StateMachine<States, Events> t = factory.getStateMachine(order.getId());
		t.stop();
		t.getStateMachineAccessor().doWithAllRegions(a -> {
			a.resetStateMachine(new DefaultStateMachineContext<States, Events>(
					order.getState(), null, null, null));
		});
		t.start();
		return t;
	}
}
