package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2239_스도쿠 {

	static int[][] map;
	static List<int[]> zeros;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[10][10];
		zeros = new ArrayList<int[]>();
		for (int i = 1; i <= 9; i++) {
			String s = br.readLine();
			for (int j = 1; j <= 9; j++) {
				map[i][j] = s.charAt(j - 1) - '0';
				if (map[i][j] == 0) { // 0이면 숫자 채워야 함. 0의 좌표 저장해둔다.
					zeros.add(new int[] { i, j });
				}
			}
		}

		dfs(0);

	}

	static void dfs(int cnt) {
		if (cnt == zeros.size()) {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= 9; i++) {
				for (int j = 1; j <= 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
			return;
		}

		int r = zeros.get(cnt)[0];
		int c = zeros.get(cnt)[1];
		for (int i = 1; i <= 9; i++) {
			if (isPossible(r, c, i)) {
				map[r][c] = i;
				dfs(cnt + 1);
				map[r][c] = 0;
			}
		}

	}

	static boolean isPossible(int r, int c, int n) {
		return rowPossible(r, c, n) && colPossible(r, c, n) && boxPossible(r, c, n);
	}

	static boolean rowPossible(int r, int c, int n) {
		for (int j = 1; j <= 9; j++) {
			if (map[r][j] == n)
				return false;
		}
		return true;
	}

	static boolean colPossible(int r, int c, int n) {
		for (int i = 1; i <= 9; i++) {
			if (map[i][c] == n)
				return false;
		}
		return true;
	}

	static boolean boxPossible(int r, int c, int n) {
		int rStart = 0, cStart = 0;
		if (r <= 3) {
			rStart = 1;
		} else if (r <= 6) {
			rStart = 4;
		} else {
			rStart = 7;
		}

		if (c <= 3) {
			cStart = 1;
		} else if (c <= 6) {
			cStart = 4;
		} else {
			cStart = 7;
		}

		boolean[] check = new boolean[10];
		check[n] = true;
		for (int i = rStart; i < rStart + 3; i++) {
			for (int j = cStart; j < cStart + 3; j++) {
				if (map[i][j] == n)
					return false;
			}
		}
		return true;
	}
}
