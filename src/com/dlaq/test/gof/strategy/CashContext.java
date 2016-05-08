package com.dlaq.test.gof.strategy;

public class CashContext {
	private CashSuper cs;
	public CashContext(CashSuper cs){
		this.cs = cs;
	}
	public double getResult(double money){
		return cs.acceptCash(money);
	}
}
