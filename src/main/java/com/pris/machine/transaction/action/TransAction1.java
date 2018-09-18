/**
 * 
 */
package com.pris.machine.transaction.action;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.state.State;

import com.pris.machine.transaction.entity.ApplyEntity;
import com.pris.machine.transaction.entity.TransactionEntity;
import com.pris.machine.transaction.sm.state.TrsEvents;
import com.pris.machine.transaction.sm.state.TrsStates;
import com.pris.machine.transaction.sm.utils.ConstVars;

/**
 * @author LO
 *
 */
public class TransAction1 implements Action<TrsStates, TrsEvents> {

	@Override
	public void execute(StateContext<TrsStates, TrsEvents> context) {
		// TODO Auto-generated method stub
		System.out.println("---这里是action，状态变化时做些事情");
		ApplyEntity apply = context.getExtendedState().get(ConstVars.APPLY_VAR_CODE, ApplyEntity.class);
		TransactionEntity trans = context.getExtendedState().get(ConstVars.TRANS_VAR_CODE,
				TransactionEntity.class);
		State<TrsStates, TrsEvents> t = context.getTarget();
		System.out.println("---目标状态  " + t.toString());
		System.out.println("---获取了交易信息  " + trans.toString());
		System.out.println("---获取了申请信息  " + apply.toString());
		System.out.println("---根据业务逻辑需要   更新数据库持久化信息  ");
		System.out.println("---交易信息更新  ");
		System.out.println("---申请信息更新  ");
	}

}
