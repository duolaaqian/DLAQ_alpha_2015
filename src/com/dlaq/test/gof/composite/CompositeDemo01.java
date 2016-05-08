package com.dlaq.test.gof.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * 树形结构
 * 
 */
public class CompositeDemo01 {
	public static void main(String[] args) {
		Compnent root = new Composite("总部");
		Leaf rl1 = new Leaf("总部财务部门");
		Leaf rl2 = new Leaf("总部人事部门");
		root.add(rl1);
		root.add(rl2);
		
		Compnent f1 = new Composite("北京分公司");
		Leaf fl1 = new Leaf("北京财务部门");
		Leaf fl2 = new Leaf("北京人事部门");
		f1.add(fl1);
		f1.add(fl2);
		root.add(f1);
		
		root.Display(0);
	}
}

interface Compnent{
	public void add(Compnent c);
	public void remove(Integer index);
	public void Display(int len);
}
class Leaf implements Compnent{
	private String name;
	public Leaf(String name){
		this.name = name;
	}
	@Override
	public void add(Compnent c) {
		System.out.println("no add fn");
	}
	@Override
	public void remove(Integer index) {
		System.out.println("no remove fn");
	}
	@Override
	public void Display(int len) {
		System.out.println(this.name);
	}
}
class Composite implements Compnent{
	private String name;
	private List<Compnent> children = new ArrayList<Compnent>();
	public Composite(String name){
		this.name = name;
	}
	@Override
	public void add(Compnent c) {
		children.add(c);
	}
	@Override
	public void remove(Integer index) {
		children.remove(index);
	}
	@Override
	public void Display(int len) {
		System.out.println(this.name);
		len++;
		for(Compnent c : children){
//			System.out.print("--");
			System.out.print(getLine(len));
			c.Display(len);
		}
	}
	public String getLine(int len){
		String str = "";
		for(int i=0;i<len;i++){
			str += "--";
		}
		return str;
	}
}




