package com.practiceuniversal.assignment;

public class CeilingAndFloorOfANumber {

	public static void main(String[] args) {
		int[] arr = {-7,-5,-3,0,2,4,6,7,8,9,22,33,44};
		int key =3;
		int ans = ceilingOfANum(arr, 45);
		System.out.println(ans);
	int ans1 = floorOfANumber(arr, -9);
		System.out.println(ans1);
		

	}
	public static int ceilingOfANum(int[] arr , int key) {
		int start =0;
		int end =arr.length-1;
		if(key > arr[ arr.length - 1]) {
			return -1;
		}
		while(start <=end) {
			int mid =start + (end-start)/2;
			if(arr[mid] > key) {
				end = mid - 1;
			}
			else if(arr[mid] < key) {
				start = mid + 1;
			}
			else {
				return mid;
			}
		}
		return arr[start];
	}
	
	public static int floorOfANumber(int[] arr,int key) {
		int start = 0;
		int end = arr.length-1;
		if(key < arr[ arr.length - 1]) {
			return -1;
		}
		while(start <= end) {
			int mid = start +(end - start)/2;
			if(arr[mid] > key) {
				end = mid-1;
			}
			else if(arr[mid] < key) {
				start = mid +1;
			}
			else return mid;
		}
		return arr[end];
	}

}
