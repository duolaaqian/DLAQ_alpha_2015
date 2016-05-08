package com.dlaq.test.core.test.Sorting;

import java.util.Arrays;

public class Compare {
	public static void main(String[] args) {
		String arr[] = {"123","324","awe1","231x"};
		CompareUtil.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}


class CompareUtil{
	//从大到小排序
	public static <T extends Comparable<T>> void sort(T[] arr){
		for(int i=0;i<arr.length-1;i++){
			for(int j=i+1;j<arr.length;j++){
				if(arr[i].compareTo(arr[j])<0){
					T tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}
}