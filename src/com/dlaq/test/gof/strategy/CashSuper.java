package com.dlaq.test.gof.strategy;

public interface CashSuper {
	double acceptCash(double money);
}

class CashNormal implements CashSuper{
	@Override
	public double acceptCash(double money) {
		return money;
	}
}
class CashRebate implements CashSuper{
	private double moneyRebate;
	public CashRebate(double moneyRebate){
		this.moneyRebate = moneyRebate;
	}
	@Override
	public double acceptCash(double money) {
		return money*moneyRebate;
	}
}
class CashReturn implements CashSuper{
	private double moneyCondition;
	private double moneyReturn;
	public CashReturn (double moneyCondition,double moneyReturn){
		this.moneyCondition = moneyCondition;
		this.moneyReturn = moneyReturn;
	}
	@Override
	public double acceptCash(double money) {
		if(money>=moneyCondition){
			return money-moneyReturn;
		}
		return money;
	}
}

