package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17136_색종이붙이기 {

	static int[] papers = new int[6];
	static boolean[][] map = new boolean[10][10];
	static boolean[][] visited = new boolean[10][10];
	static int res = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 10; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 0) {
					map[i][j] = false;
				} else {
					map[i][j] = true;
				}
			}
		}

		Arrays.fill(papers, 5);
		dfs();
		if (res==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(res);
		}
	}

	static void dfs() {
		int[] start = findOne();
		int r = start[0];
		int c = start[1];
		if (r == -1 && c == -1) {
			int tot = 0;
			for (int i=1;i<=5;i++) {
				tot += (5-papers[i]);
			}
			res = Math.min(tot, res);
			return;
		}

		for (int i = 5; i >= 1; i--) {
			if (papers[i] > 0) {
				if (isPossible(r, c, i)) {
					papers[i]--;	// 쓰고
					inOut(r, c, i, true);	// 0으로바꾸고
					dfs();	// 백트래킹
					inOut(r, c, i, false);	// 원래로 되돌림 1로 바꾸고
					papers[i]++;	// 종이 되돌림
				}
			}
		}
	}

	static void inOut(int r, int c, int l, boolean flag) {
		for (int i = r; i < r + l; i++) {
			for (int j = c; j < c + l; j++) {
				map[i][j] = !flag;
				visited[i][j] = flag; 
			}
		}
	}

	static void in(int r, int c, int l) {
		for (int i = r; i < r + l; i++) {
			for (int j = c; j < c + l; j++) {
				map[i][j] = false; // 0으로바꾸고
				visited[i][j] = true; // 방문 처리
			}
		}
	}

	static void out(int r, int c, int l) {
		for (int i = r; i < r + l; i++) {
			for (int j = c; j < c + l; j++) {
				map[i][j] = true; // 0으로바꾸고
				visited[i][j] = false; // 방문 처리
			}
		}
	}

	static boolean isPossible(int r, int c, int l) {	// 길이가 l인 색종이를 쓸 수 있는지 확인
		for (int i = r; i < r + l; i++) {
			for (int j = c; j < c + l; j++) {
				if (!isIn(i, j) || !map[i][j] || visited[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	static int[] findOne() {	// 1 찾기
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] && !visited[i][j]) {
					return new int[] { i, j }; // 1이고 방문 안 한 칸 찾는다.
				}
			}
		}
		return new int[] { -1, -1 };	// 없으면 -1, -1 리턴. 다 확인한 거
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < 10 && 0 <= c && c < 10;
	}
}
