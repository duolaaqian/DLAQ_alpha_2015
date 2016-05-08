package com.dlaq.test.core.generic.gen01;

public class MyStudent<T> {
	private T sorce;

	public MyStudent(){
	}
	public MyStudent(T sorce){
		this.sorce = sorce;
	}
	
	public T getSorce() {
		return sorce;
	}

	public void setSorce(T sorce) {
		this.sorce = sorce;
	}
	
}
