package com.dlaq.test.core.algorithm;

import java.util.Arrays;

public class QuickSortDemo01 {
	public static void main(String[] args) {
		barrelSort();
	}
	
	/**
	 * 桶排序(简单模拟)
	 */
	public static void barrelSort(){
		int[] arr = {1,3,5,0,4,7,2,6,4};
		
		int[] tmp = new int[arr.length];
		
		for(int i=0;i<arr.length;i++){
			tmp[arr[i]]++;
		}
		for(int i=0;i<tmp.length;i++){
			for(int j=0;j<tmp[i];j++){
				System.out.print(i+",");
			}
		}
	}
	
	public static void sortTest01(){
		int[] arr = {11,2,73,4,5,6,33,8,1,0,91,70,77,69,12,9};
		System.out.println(Arrays.toString(arr));
		quickSort01(arr);
		System.out.println(Arrays.toString(arr));
	}
	public static void quickSort01(int[] arr){
		qSort01(arr,0,arr.length-1);
	}
	/**
	 * 快速排序01
	 * 第一个数为基准a，i(最左)j(最右)两个游标，j--、i++(左数为基准则先j--，右数为基准则先i++)
	 * j--到一个比a小的数时进行i++
	 * i++到一个比a大的数时将i，j位置的数进行交换
	 * 直到i++或j--的过程中，i==j了，则将a与i位置的数进行交换
	 * 然后对i位置左边的部分和i位置右边的部分分别在进行此操作
	 */
	public static void qSort01(int[] arr,int leftIndex,int rightIndex){
		if(leftIndex > rightIndex){
			return;
		}
		int i,j,tmp,inTmp;
		i = leftIndex;
		j = rightIndex;
		tmp = arr[leftIndex];
		while(i!=j){
			while(arr[j]>=tmp && i<j){
				j--;
			}
			while(arr[i]<=tmp && i<j){
				i++;
			}
			if(i<j){
				inTmp = arr[i];
				arr[i] = arr[j];
				arr[j] = inTmp;
			}
		}
		arr[leftIndex] = arr[i];
		arr[i] = tmp;
		
		qSort01(arr,leftIndex,i-1);
		qSort01(arr,i+1,rightIndex);
	}
	
	
}















