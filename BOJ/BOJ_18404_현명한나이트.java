package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_18404_현명한나이트 {

	static int N, M, X, Y, A, B;
	static int[][] E;
	static int[][] visited;
	static int[] dx = new int[] { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dy = new int[] { -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		E = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			E[i][0] = A;
			E[i][1] = B;
		}
		
		visited = new int[N+1][N+1];
		
		bfs();
		
		for (int[] row: E) {
			sb.append(visited[row[0]][row[1]]-1+" ");
		}
		
		System.out.println(sb);
	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { X, Y });
		visited[X][Y] = 1;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];

			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (isIn(nx, ny) && visited[nx][ny]==0) {
					visited[nx][ny] = visited[x][y]+1;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}

	static boolean isIn(int x, int y) {
		return 1<= x && x <= N && 1 <= y && y <= N;
	}
}
