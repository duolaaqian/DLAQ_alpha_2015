package com.dlaq.test.core.test.Sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 分拣
 * 一个班级有多个学生，根据学生的成绩求每个班级的平均成绩
 */
public class Client {
	public static void main(String[] args) {
		List<Student> l = testDate();
		Map<String,ClassRoom> m = new HashMap<String,ClassRoom>();
		for(Student stu : l){
			String num = stu.getClassNum();
			if( !m.containsKey(num) ){
				m.put(num, new ClassRoom(num));
			}
			m.get(stu.getClassNum()).addStudent(stu);
		}
		Set<Entry<String, ClassRoom>> entry = m.entrySet();
		for(Entry<String, ClassRoom> e : entry){
			ClassRoom cr = e.getValue();
			System.out.println(cr.getClassNum() + " --> " 
								+ cr.getCountSource() + " --> " 
								+ (cr.getCountSource()/cr.getStudentNum()) );
		}
	}
	
	public static List<Student> testDate(){
		List<Student> l = new ArrayList<Student>();
		l.add(new Student("范旭超","01",80));
		l.add(new Student("范22","01",84));
		l.add(new Student("于泽辉","02",70));
		l.add(new Student("鹿芊","02",87));
		l.add(new Student("胡彬","03",88));
		l.add(new Student("王涛","03",86));
		return l;
	}
}

class ClassRoom{
	private String classNum;
	List<Student> stuList = new ArrayList<Student>();
	private double countSource;
	public ClassRoom(String classNum){
		this.classNum = classNum;
	}
	public String getClassNum(){
		return classNum;
	}
	public void addStudent(Student stu){
		stuList.add(stu);
		countSource += stu.getSource();
	}
	public double getCountSource(){
		return countSource;
	}
	public int getStudentNum(){
		return stuList.size();
	}
}
class Student{
	private String name;
	private String classNum;
	private int source;
	
	public Student(String name,String classNum,Integer source){
		this.name = name;
		this.classNum = classNum;
		this.source = source;
	}
	public String getName() {
		return name;
	}
	public String getClassNum() {
		return classNum;
	}
	public int getSource() {
		return source;
	}
}