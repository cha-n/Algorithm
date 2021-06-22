package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1520_내리막길 {

	static int N, M, map[][], dp[][];
	static long pathCnt;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		System.out.println(dfs(0, 0));
	}

	static int dfs(int r, int c) {

		if (r == N - 1 && c == M - 1) {
			return 1;
		}

		if (dp[r][c] == -1) {
			dp[r][c] = 0;
			for (int i = 0; i < 4; i++) {
				int nx = r + dx[i];
				int ny = c + dy[i];
				if (isIn(nx, ny) && map[r][c] > map[nx][ny]) {
					if (dp[nx][ny]>=0) {
						dp[r][c] += dp[nx][ny];
					} else {
						dp[r][c] += dfs(nx, ny);
					}
				}
			}
		}
		return dp[r][c];
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}

}
