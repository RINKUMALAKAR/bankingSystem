package com.practiceuniversal.assignment;
import java.util.Arrays;
public class DSAPractice {

	public static void main(String[] args) {



        System.out.println("Try programiz.pro");
        int [] nums={33,14,22,44,55,22,11};
        int target =44;
        int ans = linearSearch(nums, target);
        System.out.println(ans);
        boolean ans1 = linearSearch1(nums, target);
        System.out.println(ans1);
         int ans2 = linearSearch2(nums, target ,0,2);
        System.out.println(ans2);
        String name ="RinkuMalakar";
        char tar='u';
        int ans3=searchInString(name, tar);
        System.out.println(ans3);
        int an = minInArray(nums);
        System.out.println(an);
         int an1 = maxInArray(nums);
        System.out.println(an1);
       int arr[][]={
           {2,55,66,7,8},
           {55,6,88,9},
           {1,2,3}
       };
       
       
        int[] b=searchIn2DArray( arr,  77);
        System.out.println(Arrays.toString(b));
        int max = maxIn2DArray(arr);
        System.out.println(max);
        int min = minIn2DArray(arr);
        System.out.println(min);
    }
    
    public static int searchInString(String str, char ch){
        if(str.length()==0){
            return -1;
        }
        for(int index=0; index<str.length() ;index++){
            char element = str.charAt(index);
            if(element == ch){
            return index;}
          
        }
         return -1;
    }
    public static int linearSearch(int[] arr , int target){
        if(arr.length==0){
            return -1;
            
        }
        for(int index=0; index<arr.length;index++){
            int element=arr[index];
            if(element==target){
                return index;
            }
        }
        return -1;
    }
    public static boolean linearSearch1(int[] arr , int target){
        if(arr.length==0){
            return false;
            
        }
        for(int index=0; index<arr.length;index++){
            int element=arr[index];
            if(element==target){
                return true;
            }
        }
        return false;
    }
    //search in the range
public static int linearSearch2(int[] arr , int target,int start, int end){
        if(arr.length==0){
            return -1;
            
        }
        for(int index=start; index<=end;index++){
            int element=arr[index];
            if(element==target){
                return index;
            }
        }
        return -1;
    }
    public static int minInArray(int[] arr){
        int min = arr[0];
        for(int i=0; i<arr.length; i++){
            if(min>arr[i]){
                min =arr[i];
            }
        }
        return min;
        
    }
    public static int maxInArray(int[] arr){
        int max = arr[0];
        for(int i=0; i<arr.length; i++){
            if(max<arr[i]){
                max =arr[i];
            }
        }
        return max;
        
    }
    public static int[] searchIn2DArray(int[][] arr, int target){
        for(int i=0; i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int []{-1,-1};
        
    }
    public static int maxIn2DArray(int[][] arr){
        int max =Integer.MIN_VALUE;
        for(int i=0; i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j]>max){
                    max =arr[i][j];
                }
            }
        }
        return max;
        
    }
    public static int minIn2DArray(int[][] arr){
        int min =Integer.MAX_VALUE;
        for(int i=0; i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j]<min){
                    min =arr[i][j];
                }
            }
        }
        return min;
        
    }
    


	}


