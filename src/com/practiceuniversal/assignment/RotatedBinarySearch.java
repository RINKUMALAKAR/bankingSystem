package com.practiceuniversal.assignment;

public class RotatedBinarySearch {

	public static void main(String[] args) {
		int [] arr = {1,2,3,4,5,6,0,1,2};
//		System.out.println(findPivot(arr));
		int target =0;
		System.out.println(search(arr, target));

	}
	public  static int binarySearch(int[] arr, int target ,int start , int end) {
		
		while(start <=end) {
			int mid =start +(end -start)/2;
			if(arr[mid] > target) {
				end = mid-1;
			}
			else if (arr[mid] < target) {
				start=mid+1;
			}
			else {
				return mid;
			}
		}
		return -1;
	}
	
	static int search(int []nums , int target) {
		int pivot = findPivot(nums);
		//if u do not find pivot thats means array is not rotated
		if(pivot ==-1) {
			//just do normal binary search
			return binarySearch(nums , target ,0, nums.length-1);
			
		}
		//if pivot is found u have found 2 asc sorted arrays 
		if( nums[pivot] ==target) {
			return pivot;
		}
		if(target >= nums[0]) {
			return binarySearch(nums , target ,0, pivot-1);
		}
		return binarySearch(nums , target ,pivot+1, nums.length-1);
	}
	public static int findPivot(int[] arr) {
		int start = 0;
		int end = arr.length-1;
		while(start <= end) {
			int mid = start + (end - start)/2;
			//4 cases over here
			if(mid < end && arr[mid] > arr[mid+1]) {
				return mid;
			}
			if(mid > start && arr[mid] < arr[mid-1]) {
				return mid -1;
			}
			if(arr[mid] <= arr[start]) {
				end =mid-1;
			}
			else {
				start = mid +1;
			}
			
		}
		return -1;
		
	
	}

}
