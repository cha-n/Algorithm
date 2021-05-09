package LeetCode;

import java.io.*;
import java.util.*;

public class LC_011_ContainerWithMostWater {

	static int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };

	public static void main(String[] args) {

		int start = 0;
		int end = height.length - 1;
		int max = Integer.MIN_VALUE;
		
		
		while (start<=end) {
			System.out.println("start: "+start+ " end: "+end);
			max = Math.max(max, Math.min(height[start], height[end])*(end-start));
		
			if (height[start]>=height[end]) {
				end--;
			}else {
				start++;
			}
		
		}
		System.out.println(max);
		
	}
}
