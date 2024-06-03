package com.practiceuniversal.assignment;

public class BinarySearch {

	public static void main(String[] args) {
		int arr[]= {1,2,3,44,55,11,44,77,-11,-55};
		int target= -11;
		int ans = binarySearch(arr,target);
		System.out.println(ans);
		int[] arr1= {-18,-12,-6,-5,3,5,7,8,9,33,35};
		int ans1=orderAgnosticBinarySearch(arr1, 7);
		System.out.println(ans1);

	}
	
	public  static int binarySearch(int[] arr, int target) {
		int start =0;
		int end= arr.length-1;
		while(start <=end) {
			int mid =start +(end -start)/2;
			if(arr[mid] <target) {
				end = mid-1;
			}
			else if (arr[mid]>target) {
				start=mid+1;
			}
			else {
				return mid;
			}
		}
		return -1;
	}
	
	public  static int orderAgnosticBinarySearch(int[] arr, int target) {
		int start =0;
		int end= arr.length-1;
		boolean isAsc= arr[start]<arr[end];
		while(start <=end) {
			int mid =start +(end -start)/2;
			if(arr[mid]==target) {
				return target;
			}
			
			if(isAsc) {
				if(target < arr[mid]) {
					end = mid-1;
				}
				else  {
					start=mid+1;
				}
			}
			
			else if  (target > arr[mid]) {
				if(target > arr[mid]) {
					end = mid-1;
				}
				else  {
					start=mid+1;
				}
			}
			}
		
		return -1;
	}


}
