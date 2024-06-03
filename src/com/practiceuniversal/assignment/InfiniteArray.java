package com.practiceuniversal.assignment;

public class InfiniteArray {

	public static void main(String[] args) {
		int arr[] = new int[]{3, 5, 7, 9, 10, 90, 
                100, 130, 140, 160, 170};
		int target =130;
		System.out.println(ans(arr,target));

	}
	
	public static int ans(int [] arr,int target) {
		//first find the range
		//first start with a box of size of 2
		int start =0;
		int end =1;
		
		//condition for the target lie in the range
		while(target > arr[end]) {
			int newStart =end +1;
			//double the box size
			// end =previous end + sizeOfBox*2
			end = end + ( end  - start + 1 ) * 2;
			start= newStart;
			
			
		}
		return binarySearch(arr, target , start , end);
		
		
	}
	public  static int binarySearch(int[] arr, int target,int start , int end) {
		
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

}
