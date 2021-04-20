package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_15990_123더하기5 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		int max = 100000;
		int[][] dp = new int[max + 1][4];

		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;
		
		for (int i = 4; i <= max; i++) {
			dp[i][1] = (dp[i - 1][2] + dp[i - 1][3])%1000000009;
			dp[i][2] = (dp[i - 2][1] + dp[i - 2][3])%1000000009;
			dp[i][3] = (dp[i - 3][1] + dp[i - 3][2])%1000000009;
		}
		
//		for (int i=0;i<=max;i++) {
//			System.out.println(i + " " + Arrays.toString(dp[i]));
//		}
		
//		System.out.println(955514179+152471664+ " : "+(955514179+152471664)%1000000009);
//		System.out.println(639004194+889836102);
//		System.out.println(442046700+358817742);
//
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(1000000008+1000000008);
//		System.out.println((1l*1000000008+1l*1000000008+1l*1000000008));
		
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			
			sb.append((1l*dp[n][1]+1l*dp[n][2]+1l*dp[n][3])%1000000009+"\n");
			// Integer.MAX_VALUE: 2147483647 --> 1000000008 세 개 더하면 오버플로우
			// --> long 형변환 필요
		}
		System.out.println(sb);

	}

}
