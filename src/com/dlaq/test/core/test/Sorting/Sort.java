package com.dlaq.test.core.test.Sorting;

public class Sort {
	public static void main(String[] args) {
//		int[] arr = {12,34,26,2,5,87,24,9,0,43};
		int[] arr = {9,8,6,7,5};
//		print(arr);
		sort1(arr);
//		print(arr);
	}
	public static void print(int[] arr){
		for(int i : arr){
			System.out.print(i+"、");
		}
		System.out.println();
	}
	//从小到大
	public static int[] sort1(int[] arr){
		int count=0;
		for(int i=0;i<arr.length-1;i++){
			System.out.println("****** "+i+" ******");
			for(int j=i+1;j<arr.length;j++){
				count++;
				System.out.print("["+j+"]");
				print(arr);
				if(arr[i]>arr[j]){
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
				System.out.print("   ");
				print(arr);
			}
		}
		System.out.println("==>"+count+"<==");
		return arr;
	}
	
}
