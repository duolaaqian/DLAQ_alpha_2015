package com.dlaq.test.gof.proxy;

/**
 * 静态代理
 * 	1、真实角色
 * 	2、代理角色：持有真实角色引用
 * 	3、二者实现相同接口
 */
public class StaticProxy {
	public static void main(String[] args) {
		IStar jay = new RealStar();
		IStar Jolin = new ProxyStar(jay);
		Jolin.sing();
	}
}

interface IStar{
	/**
	 * 面谈
	 */
	void confer();
	/**
	 * 签合同
	 */
	void signContract();
	/**
	 * 唱歌
	 */
	void sing();
	/**
	 * 收钱
	 */
	void collectMoney();
}

class RealStar implements IStar{

	@Override
	public void confer(){
		System.err.println("明星不负责面谈");
	}

	@Override
	public void signContract(){
		System.err.println("明星不负责签合同");
	}

	@Override
	public void sing(){
		System.out.println("明星开始唱歌....");
	}

	@Override
	public void collectMoney(){
		System.err.println("明星不负责收尾款");
	}
	
}
class ProxyStar implements IStar{
	private IStar realStar;
	
	public ProxyStar(IStar realStar){
		this.realStar = realStar;
	}

	@Override
	public void confer(){
		System.out.println("经纪人面谈....");
	}

	@Override
	public void signContract(){
		System.out.println("经纪人签合同....");
	}

	@Override
	public void sing(){
		confer();
		signContract();
		realStar.sing();
		collectMoney();
	}

	@Override
	public void collectMoney(){
		System.out.println("经纪人收钱....");
	}
}