package com.dlaq.test.file.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.webframe.support.util.SystemLogUtils;

import com.dlaq.test.file.FileGenerator;
import com.dlaq.test.file.FileTest1;

public class FileThreadTest1 {

	public static void main(String arg[]){
		List<Map<String, Object>> l = FileTest1.getList();
		Stack<Map<String,Object>> stack = new Stack<Map<String,Object>>();
		for(Map<String,Object> m : l){
			stack.push(m);
		}
		
		create1(stack);
//		create2(stack);
		
/*		Stack<Map<String,Object>> stack2 = new Stack<Map<String,Object>>();
		int num = stack.size()/2;
		for(int i=0;i<num;i++){
			stack2.push(stack.pop());
		}
		create3(stack,stack2);*/
	}
	
	public static void create1(Stack<Map<String,Object>> stack){
		FileFactory ff = new FileFactory(stack);
		SystemLogUtils.rootPrintln("start");
		
		List<Thread> ts = new ArrayList<Thread>();
		int count = 4;
		for(int i=0;i<count;i++){
			ts.add(new Thread(ff,"线程"+(i+1)));
		}
		
		for(Thread t : ts){
			t.start();
		}
		
/*		Thread t1 =new Thread(ff,"线程1");
		Thread t2 =new Thread(ff,"线程2");
		Thread t3 =new Thread(ff,"线程3");
		Thread t4 =new Thread(ff,"线程4");
//		Thread t5 =new Thread(ff,"线程5");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
//		t5.start();
*/		
	}
	public static void create3(Stack<Map<String,Object>> stack1,Stack<Map<String,Object>> stack2){
		System.out.println("[stack1 --> "+stack1.size()+"][stack2 --> "+stack2.size()+"]");
		FileFactory ff1 = new FileFactory(stack1);
		FileFactory ff2 = new FileFactory(stack2);
		SystemLogUtils.rootPrintln("start");
		Thread t1 =new Thread(ff1,"线程1");
		Thread t2 =new Thread(ff2,"线程2");
		Thread t3 =new Thread(ff1,"线程3");
		Thread t4 =new Thread(ff2,"线程4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	public static void create2(Stack<Map<String,Object>> stack){
		FileGenerator fg = new FileGenerator();
		
		SystemLogUtils.rootPrintln("start");
		for(Map<String,Object> m : stack){
			fg.createFile(m);
		}
		SystemLogUtils.rootPrintln("end");
	}
	
}


class FileFactory implements Runnable{
	
	private Stack<Map<String,Object>> stack;
	
	FileGenerator fg = new FileGenerator();
	
	public FileFactory(){}
	
	public FileFactory(Stack<Map<String,Object>> stack){
		this.stack = stack;
	}
	
	public int getSize(){
		if(null!=stack){
			return stack.size();
		}else{
			return -1;
		}
	}

	@Override
	public void run() {
		if(null!=stack){
			while(stack.size()>0){
				if(stack.size()==1){
					fg.createFile(stack.pop());
					SystemLogUtils.rootPrintln("end  "+Thread.currentThread().getName());
				}else{
					fg.createFile(stack.pop());
				}
			}
		}
	}
	
}
