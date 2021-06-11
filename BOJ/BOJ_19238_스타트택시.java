import java.io.*;
import java.util.*;

public class BOJ_19238_스타트택시 {

	static int N, M, fuel;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] map, visited, info;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int start_x = Integer.parseInt(st.nextToken()) - 1;
		int start_y = Integer.parseInt(st.nextToken()) - 1;

		info = new int[M + 1][4];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			info[i][0] = Integer.parseInt(st.nextToken()) - 1;
			info[i][1] = Integer.parseInt(st.nextToken()) - 1;
			info[i][2] = Integer.parseInt(st.nextToken()) - 1;
			info[i][3] = Integer.parseInt(st.nextToken()) - 1;
			map[info[i][0]][info[i][1]] = i * (-1);
		}


		visited = new int[N][N];

		int cnt = 0;
		while (true) {
			int[] start = findStart(start_x, start_y);
			int x = start[0];
			int y = start[1];

			int usedfuel = start[2];
			if (x == -1) break;
			fuel -= usedfuel;
			if (fuel < 0) {
				break;
			}
			int target = map[x][y] * (-1);
			map[x][y] = 0;
			int[] end = bfs(x, y, target);
			start_x = end[0];
			start_y = end[1];
			if (start_x == -1) break;
			usedfuel = end[2];
			fuel -= usedfuel;
			if (fuel < 0) {
				break;
			} else {
				fuel += 2 * usedfuel;
			}
			cnt++;
            
		}
        

		if (cnt == M && fuel >= 0) {
			System.out.println(fuel);
		} else {
			System.out.println(-1);
		}
	}

	static int[] bfs(int r, int c, int n) {
		int R = info[n][2];
		int C = info[n][3];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], 0);
		}
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { r, c });
		visited[r][c] = 1;

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
            
			if (x == R && y == C) {
				return new int[] { x, y, visited[x][y] - 1 };
			}
            
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (isIn(nx, ny) && visited[nx][ny] == 0 && map[nx][ny]!=1) {

					visited[nx][ny] = visited[x][y] + 1;
					q.offer(new int[] { nx, ny });
				}
			}
		}
		return new int[] { -1, -1, -1 };
	}

	static int[] findStart(int r, int c) { // 다음 시작 위치 찾기
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], 0);
		}
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { r, c });
		visited[r][c] = 1;
		if (map[r][c]!=0 && map[r][c]!=1) {	// 최단거리 0
			return new int[] { r, c, 0 };
		}

		while (!q.isEmpty()) {
			int size = q.size();
			// 여러 명이면 그 중 행 번호가 가장 승객, 여러 명이면 열 번호가 가장 작은 승객
			int start_x = Integer.MAX_VALUE;
			int start_y = Integer.MAX_VALUE;

			for (int s = 0; s < size; s++) {
				int[] temp = q.poll();
				int x = temp[0];
				int y = temp[1];
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (isIn(nx, ny) && visited[nx][ny] == 0 && map[nx][ny]!=1) {
						if (map[nx][ny]!=0 && map[nx][ny]!=1) {
							int num = map[nx][ny];
							if (num < 0) {
								if (nx < start_x) {
									start_x = nx;
									start_y = ny;
								} else if (nx == start_x) {
									if (ny < start_y) {
										start_y = ny;
									}
								}
							}
						}
						visited[nx][ny] = visited[x][y] + 1;
						q.offer(new int[] { nx, ny });
					}
				}
			}
			if (start_x != Integer.MAX_VALUE) { // 이동할 곳이 있으면
				return new int[] { start_x, start_y, visited[start_x][start_y] - 1 }; // r, c, 사용한 연료 양
			}
		}

		return new int[] { -1, -1, -1 }; // 다 본 거..
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

}
