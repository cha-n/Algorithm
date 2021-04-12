package swea;

import java.io.*;
import java.util.*;

public class Solution_1249_보급로 {

	static int T, N;
	static int[][] map;
	static int[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			// 입력 끝

			
//			int min = bfs();
//			System.out.println("min: "+min);
			sb.append(String.format("#%d %d%n", t + 1, bfs()));
		}
		System.out.println(sb);
	}

	static int bfs() {
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(new Integer[] { 0, 0 });
		visited[0][0] = 1;

		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.poll()[1];
//			System.out.println(x + ", " + y);
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
//				if (nx == N - 1 && ny == N - 1) {
//					return visited[x][y];
//				}
				if (!isIn(nx, ny))
					continue;

//				if (map[nx][ny] == 0) {
//					visited[nx][ny] = visited[x][y];
//					q.offer(new Integer[] { nx, ny });
//				} else if (map[nx][ny] == 1) {
//					visited[nx][ny] = visited[x][y] + 1;
//					q.offer(new Integer[] { nx, ny });
//				}

				if (visited[nx][ny] == 0 || visited[nx][ny] > visited[x][y] + map[nx][ny]) {
					visited[nx][ny] = visited[x][y] + map[nx][ny];
					q.offer(new Integer[] { nx, ny });
				}

//				if (map[nx][ny] == 0 && (visited[nx][ny] == 0 || visited[nx][ny] > visited[x][y])) {
//					visited[nx][ny] = visited[x][y];
//					q.offer(new Integer[] { nx, ny });
//				} else if (map[nx][ny] == 1 && (visited[nx][ny] == 0 || visited[nx][ny] > visited[x][y] + 1)) {
//					visited[nx][ny] = visited[x][y] + 1;
//					q.offer(new Integer[] { nx, ny });
//				}
			}
//			for (int[] row : visited) {
//				System.out.println(Arrays.toString(row));
//			}
//			System.out.println("==================================");
		}
		return visited[N - 1][N - 1] - 1;
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

}
