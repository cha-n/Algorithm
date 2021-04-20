package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14501_퇴사 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] counseling = new int[N + 2][2];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			counseling[i][0] = Integer.parseInt(st.nextToken());
			counseling[i][1] = Integer.parseInt(st.nextToken());
		}
		// 입력 끝

		int[] dp = new int[N + 2];
		for (int i = N; i >= 0; i--) {
			if (i + counseling[i][0] > N+1) {	// N 범위 벗어나면 상담 못 함
				dp[i] = dp[i+1];
			} else {
				dp[i] = Math.max(dp[i+1], dp[i + counseling[i][0]] + counseling[i][1]);		// 상담 안 함 (dp[i+1]),  상담 함(dp[i+counseling[i][0]] + counseling[i][1]) 중 최대값으로 갱신
			}
		}
		System.out.println(dp[1]);
	}


}
