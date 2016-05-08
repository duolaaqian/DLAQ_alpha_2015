package com.dlaq.test.gof.templateMethod;

/**
 * 模板方法模式
 * 
 * 实现算法时，整体过程很固定，只有部分易改变
 * 则可以将固定的部分提取到父类中实现，由子类实现易变的部分
 * 
 * 基本方法：
 * 	1、抽象方法：父类声明，由子类具体实现
 * 	2、具体方法：父类实现，子类可以根据情况进行覆盖或直接继承
 * 	3、钩子方法：子类重写的父类方法
 * 
 * 优点：
 * 	1、简化了子类的代码，方便维护
 * 	2、如有特殊情况也可重写父类多个方法，具有一定扩展性
 * 
 * 场景：
 * 	JDBCTemplate、HibernateTemplate
 * 	Junit单元测试、servlet中doGet/doPost
 */
public class TemplateMethodDemo01 {
	public static void main(String[] args) {
		ATMTemplateMethod atm1 = new ATMTemplateMethod(){
			@Override
			public void transact() {
				System.out.println("取钱");
			}
		};
		ATMTemplateMethod atm2 = new ATMTemplateMethod(){
			@Override
			public void transact() {
				System.out.println("转账");
			}
		};
		ATMTemplateMethod atm3 = new ATMTemplateMethod(){
			@Override
			public void transact() {
				System.out.println("查询余额");
			}
		};
		ATMTemplateMethod atm4 = new ATMTemplateMethod(){
			@Override
			public void insertBankCard() {}
			@Override
			public void enterPassword() {}
			@Override
			public void transact() {
				System.out.println("无卡存款");
			}
			@Override
			public void removeTheBankCard() {}
		};
		System.out.println("[路人甲]：");
		atm1.process();
		System.out.println("[路人乙]：");
		atm2.process();
		System.out.println("[路人丙]：");
		atm3.process();
		System.out.println("[路人丁]：");
		atm4.process();
	}
}
/**
 * ATM机模板方法
 */
abstract class ATMTemplateMethod{
	public void insertBankCard(){
		System.out.println("******插入银行卡******");
	}
	public void enterPassword(){
		System.out.println("******输 入  密 码******");
	}
	public abstract void transact();
	public void removeTheBankCard(){
		System.out.println("******取出银行卡******");
	}
	public final void process(){
		this.insertBankCard();
		this.enterPassword();
		this.transact();
		this.removeTheBankCard();
	}
}