package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_21610_마법사상어와비바라기 {

	static int N, M, A[][];
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static List<Cloud> clouds, newClouds;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		clouds = new ArrayList<>();
		newClouds = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				clouds.add(new Cloud(N - i, j + 1));
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			solve(d, s);
//			break;
		}
		
		int res = 0;
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				res += A[i][j];
			}
		}
		System.out.println(res);
	}

	static void solve(int d, int s) {

		// 이동
		s = s % N;
		for (int i = 0; i < clouds.size(); i++) {
			Cloud temp = clouds.get(i);
			temp.r += dx[d] * s;
			temp.c += dy[d] * s;
			if (!isIn(temp.r))
				temp.r = toIn(temp.r);
			if (!isIn(temp.c))
				temp.c = toIn(temp.c);
			clouds.get(i).r = temp.r;
			clouds.get(i).c = temp.c;
		}

		// 2
		for (int i = 0; i < clouds.size(); i++) {
			Cloud temp = clouds.get(i);
			A[temp.r][temp.c] += 1;
		}

		// 3
		// 4
		for (int i = 0; i < clouds.size(); i++) {
			int x = clouds.get(i).r;
			int y = clouds.get(i).c;
			for (int j = 2; j <= 8; j += 2) {
				int nx = x + dx[j];
				int ny = y + dy[j];
				if (isIn2(nx, ny) && A[nx][ny] != 0) {
					A[x][y]++; // 물이 있는 바구니 수만큼 박구니 물 양 증가
				}
			}
		}
		
		// 3
		newClouds.clear();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (A[i][j] >= 2) {
					boolean flag = true;
					for (int c = 0; c < clouds.size(); c++) {
						if (clouds.get(c).r == i && clouds.get(c).c==j) {
							flag = false;
							
						}
					}
					if (flag) {
						newClouds.add(new Cloud(i, j));
						A[i][j] -= 2;
					}
				}
			}
		}
		
		clouds.clear();
		for (int i=0;i<newClouds.size();i++) {
			clouds.add(newClouds.get(i));
		}
	}

	static int toIn(int x) {
		if (x < 1) {
			return x + N;
		} else if (x > N) {
			return x - N;
		}
		return x;
	}

	static boolean isIn(int x) {
		return 1 <= x && x <= N;
	}

	static boolean isIn2(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= N;
	}

	static class Cloud {
		int r;
		int c;

		public Cloud() {
			super();
		}

		public Cloud(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public String toString() {
			return "[r: " + r + " c: " + c+"]";
		}
	}
}
