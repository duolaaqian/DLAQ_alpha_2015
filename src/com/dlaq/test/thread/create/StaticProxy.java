package com.dlaq.test.thread.create;

/**
 * 静态代理
 * 	1、真实角色
 * 	2、代理角色：持有真实角色引用
 * 	3、二者实现相同接口
 */
public class StaticProxy {
	public static void main(String arg[]){
		IMarry you = new You();
		WeddingCompany company = new WeddingCompany(you);
		company.marry();
	}

}


interface IMarry{
	public abstract void marry();
}

class You implements IMarry{
	@Override
	public void marry() {
		System.out.println("you marry....");
	}
}

class WeddingCompany implements IMarry{
	private IMarry marry;
	public WeddingCompany(){
		
	}
	public WeddingCompany(IMarry marry){
		this.marry = marry;
	}
	@Override
	public void marry() {
		beforeMarry();
		marry.marry();
		afterMarry();
	}
	private void beforeMarry(){
		System.out.println("before marry......");
	}
	private void afterMarry(){
		System.out.println("after marry......");
	}
	
}