package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11055_가장큰증가부분수열 {

	static int N, A[];
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		int[] dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=0;i<N;i++) {
			int temp = Integer.parseInt(st.nextToken());
			A[i] = temp;
			dp[i] = temp;
		}
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<=i;j++) {
				if (A[i]>A[j]) {
					if (dp[j]+A[i]>dp[i]) {
						dp[i] = dp[j]+A[i];
					}
				}
			}
		}
		
//		System.out.println(Arrays.toString(dp));
		Arrays.sort(dp);
		System.out.println(dp[N-1]);
	}

}
