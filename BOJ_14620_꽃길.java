// 14620, 꽃길
package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14620_꽃길 {
	static int N;
	static int[][] G;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		G = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				G[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝
		dfs(0, 0);
		System.out.println(min);
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int min = Integer.MAX_VALUE;
	static void dfs(int cnt, int sum) {
		
		if (cnt == 3) {
			min = Math.min(min, sum);
			return;
		}
		
		if (sum >= min) return;
		
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if (canGo(i, j)) {
					int cost = go(i, j);
					dfs(cnt+1, sum+cost);
					back(i, j);
				}
			}
		}

	}

	static boolean canGo(int r, int c) {	// 꽃이 필 수 있는지 확인
		if (visited[r][c])
			return false;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (visited[nr][nc])
				return false;
		}
		return true;
	}

	static int go(int r, int c) {	
		int cost = G[r][c];
		visited[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			visited[nr][nc] = true;
			cost += G[nr][nc];
		}
		return cost;
	}

	static void back(int r, int c) {
		visited[r][c] = false;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			visited[nr][nc] = false;
		}
	}
}
