// 1912, 연속합
package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1912_연속합 {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] numArr = new int[n+1];
		for (int i=1;i<=n;i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 끝
		
		int maxSum = Integer.MIN_VALUE;
		int[] dp = new int[n+1];
		dp[1] = numArr[1];
		
		for (int i=1;i<n;i++) {
			dp[i] = Math.max(dp[i-1]+numArr[i], numArr[i]);
			maxSum = Math.max(maxSum, dp[i]);
		}
		
		System.out.println(maxSum);
		
	}
}
