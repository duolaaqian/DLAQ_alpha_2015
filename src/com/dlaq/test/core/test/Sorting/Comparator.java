package com.dlaq.test.core.test.Sorting;

import java.util.Arrays;

public class Comparator {
	public static void main(String[] args) {
		String arr[] = {"123","32400","awe1","231x"};
		System.out.println(Arrays.toString(arr));
		Arrays.sort(arr, new MyComp());
		System.out.println(Arrays.toString(arr));
	}
}

class MyComp implements java.util.Comparator<String>{
	@Override
	public int compare(String o1, String o2) {
		int len1 = o1.length();
		int len2 = o2.length();
		return len1 - len2;
	}
}