package com.dlaq.test.gof.templateMethod;

import java.util.Random;

/**
 * 
 */
public class TemplateMethodDemo02 {
	public static void main(String[] args) {
		Life l = new Life(){
			@Override
			protected boolean isStrive() {
				return false;
			}
		};
		l.lifeTime();
	}
}
abstract class Life{
	private static Random r = new Random();
	private final int wealth = r.nextInt(3);
	private int schoolLevel;
	private int achievement;
	private int work;
	
	public void lifeTime(){
		goToSchool();
		graduation();
		work();
		printInfo();
	}
	
	private void goToSchool(){
		switch(wealth){
		case 2:{
			schoolLevel = 2;
			break;
		}
		case 1:{
			if(r.nextInt(100)<1){
				schoolLevel = 2;
			}else{
				schoolLevel = 1;
			}
			break;
		}
		default:{
			if(r.nextInt(10)<1){
				schoolLevel = 1;
			}else{
				schoolLevel = 0;
			}
			break;
		}
		}
	}
	private void graduation(){
		if(isStrive()&&r.nextInt(1000)<1){
			achievement = (schoolLevel==2)?2:(schoolLevel+1);
		}else if(isStrive()){
			achievement = schoolLevel;
		}else{
			achievement = (schoolLevel==0)?0:(schoolLevel-1);
		}
	}
	protected boolean isStrive() {
		return false;
	}
	private void work(){
		if(wealth==2){
			work = 2;
		}else if(isStrive()&&achievement==1){
			work = (r.nextInt(10000)<1)?2:1;
		}else{
			work = 0;
		}
	}
	private void printInfo(){
		String bron[] = {"出生在贫困家庭","出生在普通家庭","出生在富裕家庭"};
		String school[] = {"在乡村学校上学","在普通学校上学","在重点学校上学"};
		String source[] = {"辍学","普通成绩","优秀成绩"};
		String work[] = {"农民工","普通职员","少东家"};
		
		System.out.println(bron[wealth]);
		System.out.println(school[schoolLevel]);
		System.out.println(source[achievement]);
		System.out.println(work[this.work]);
		System.out.println("[我编不下去了....]");
	}
}