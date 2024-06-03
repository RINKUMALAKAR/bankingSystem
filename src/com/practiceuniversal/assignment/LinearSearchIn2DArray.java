package com.practiceuniversal.assignment;

import java.util.Arrays;

public class LinearSearchIn2DArray {

	public static void main(String[] args) {
		int[][] arr = {
				{2,4,5,6},
				{55,66,77},
				{33,11,33}
				
		};
		int target = 6;
		System.out.println(Arrays.toString(linearSearch2DArray(arr,target)));
		int[][] ar = {
				{10,20,30,40},
				{15,25,35,45},
				{33,34,38,50}
				
		};
		int tar = 15;
		System.out.println(Arrays.toString(binarySearch2DArray(ar,tar)));
		System.out.println(Arrays.toString(search(ar,50)));
		

	}
	public static int[] linearSearch2DArray(int[][] arr, int target) {
		int n =arr.length;
		for(int i =0; i<n;i++) {
			for(int j=0; j<arr[i].length;j++) {
				if(arr[i][j]==target) {
					return new int [] {i,j};
				}
				
			}
		}
		return new int[] {-1,-1};
	}
	public static int[] binarySearch2DArray(int[][] arr, int target) {
		  int rows = arr.length;
		  int cols = arr[0].length;
		  int start = 0;
		  int end = rows * cols - 1;

		  while (start <= end) {
		    int mid = start + (end - start) / 2;
		    int row = mid / cols;
		    int col = mid % cols;
		    int midPointValue = arr[row][col];

		    if (target == midPointValue) {
		      return new int[]{row, col};
		    } else if (target < midPointValue) {
		      end = mid - 1;
		    } else {
		      start = mid + 1;
		    }
		  }

		  return new int[]{-1, -1};
		}
	public static int[] search(int[][] arr, int target) {
		int r =0;
		int c=arr.length-1;
		while(r < arr.length && c >=0) {
			if(arr[r][c]==target) {
				return new int[] {r,c};
			}
			if(arr[r][c] < target) {
				r++;
			}
			else {
				c--;
			}
			
		}
		return new int[] {-1,-1};
	}

	}

