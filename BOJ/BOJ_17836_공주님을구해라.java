package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17836_공주님을구해라 {

	static int N, M, T;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝

		int result = bfs();
//		System.out.println(result);
		if (result != -1 && result <= T) {
			System.out.println(result);
		} else {
			System.out.println("Fail");
		}
	}

	static int bfs() {
		boolean[][][] visited = new boolean[N][M][2]; // 0: gram X, 1: gram O
		Queue<Yong> q = new LinkedList<>();
		q.add(new Yong(0, 0, false, 0));
		visited[0][0][0] = true;

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		while (!q.isEmpty()) {
			Yong now = q.poll();
			int x = now.x;
			int y = now.y;
			boolean gram = now.gram;
			int time = now.time;
//			System.out.println(x + "," + y + ":" + gram + " "+time);
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx == N - 1 && ny == M - 1) {
					return time+1;
				}

				if (isIn(nx, ny)) {
					if (gram) { // 다 갈 수 있음
						if (visited[nx][ny][1] == false) {
							visited[nx][ny][1] = true;
							q.add(new Yong(nx, ny, true, time + 1));
						}
					} else {
						if (visited[nx][ny][0] == false) {
							if (map[nx][ny] == 0) {
								q.add(new Yong(nx, ny, false, time + 1));
								visited[nx][ny][0] = true;
							} else if (map[nx][ny] == 2) {
								q.add(new Yong(nx, ny, true, time + 1));
								visited[nx][ny][1] = true;
							}

						}

					}
				}

			}
//			for (int[] v : visited) {
//				System.out.println(Arrays.toString(v));
//			}
//			System.out.println();
		}

		return -1;

	}

	static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

	static class Yong {
		int x;
		int y;
		boolean gram;
		int time;

		public Yong(int x, int y, boolean gram, int time) {
			this.x = x;
			this.y = y;
			this.gram = gram;
			this.time = time;
		}
	}
}
