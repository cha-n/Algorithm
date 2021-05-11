// 7576, 토마토
package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_7576_토마토 {

	static int N, M;
	static int[][] tomatoes;
	static int[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Integer[]> q = new LinkedList<Integer[]>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 열
		N = Integer.parseInt(st.nextToken()); // 행
		tomatoes = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tomatoes[i][j] = Integer.parseInt(st.nextToken());
				if (tomatoes[i][j] == 1)
					q.offer(new Integer[] { i, j });
			}
		}

//		for (int[] row : tomatoes) {
//			System.out.println(Arrays.toString(row));
//		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				// 토마토가 익었고, 방문하지 않았으면
//				if (tomatoes[i][j] == 1 && visited[i][j] == 0) {
//					bfs(i, j);
//				}
//			}
//		}
		bfs();

//		for (int[] row : visited) {
//			System.out.println(Arrays.toString(row));
//		}

		System.out.println(maxVisited());
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Integer[] now = q.poll();
			int x = now[0];
			int y = now[1];

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (0 > nx || nx >= N || 0 > ny || ny >= M)
					continue;
				// 방문하지 않았고, 안 익었으면 -> 방문 표시 & 익었다고 update
				// 방문하지 않았거나, 방문 횟수가 현재 방문 횟수보다 크면
				if (tomatoes[nx][ny] == 0) {
					if (visited[nx][ny] == 0 || visited[nx][ny] > visited[x][y] + 1) {
						visited[nx][ny] = visited[x][y] + 1;
						q.offer(new Integer[] { nx, ny });
					}

				}

			}
		}
	}

	// 제일 오래 걸리는 날짜
	static int maxVisited() {
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 토마토가 모두 익지 못하는 상황
				// 토마토인데 방문되지 않은 공간이 있을 때 return -1
				if (tomatoes[i][j] == 0 && visited[i][j] == 0)
					return -1;
				max = Math.max(max, visited[i][j]);
			}
		}
		return max; // 처음부터 익어있는 토마톼는 날짜 세면 안 됨 --> -1
	}
}
