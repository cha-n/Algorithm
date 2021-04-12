package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2193_이친수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[N + 1];

		dp[1] = 1;
		if (N > 1)
			dp[2] = 1;
		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		// System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);
	}
}


//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(br.readLine());
//
//		//String s = "1";
//		dfs(1, 1, N);
//		System.out.println(result);
//	}
//
//	static String[] zeroone = { "0", "1" };
//	static int result = 0;
//	static void dfs(int cnt, int ex, int N) {
//		if (cnt == N) {
//			result++;
//			return;
//		}
//
//		if (ex==0) {
//			dfs(cnt+1, 1, N );
//		}
//		dfs(cnt+1, 0, N);
//		
//	}
// }
