package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_15671_오델로 {

	static int N;
	static char map[][];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
					map[i][j] = 'W';
				} else if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
					map[i][j] = 'B';
				} else {
					map[i][j] = '.';
				}
			}
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			if (i % 2 == 0) {
				map[R][C] = 'B';
			} else {
				map[R][C] = 'W';
			}
			check(R, C);
		}

		print();
	}

	static void print() {
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

		int blackCnt = 0;
		int whiteCnt = 0;
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				if (map[i][j] == 'W') {
					whiteCnt++;
				} else if (map[i][j] == 'B') {
					blackCnt++;
				}
			}
		}
		if (whiteCnt > blackCnt) {
			System.out.println("White");
		} else {
			System.out.println("Black");
		}
	}

	static void check(int r, int c) {
		char now = map[r][c];

		int dx[] = new int[] { -1, -1, -1, 0, 1, 1, 1, 0 };
		int dy[] = new int[] { -1, 0, 1, 1, 1, 0, -1, -1 };

		for (int i = 0; i < 8; i++) {
			int nx = r;
			int ny = c;
			List<int[]> list = new ArrayList<>();
			
			boolean flag = true;
			while (true) {
				nx += dx[i];
				ny += dy[i];
				
				if (!isIn(nx, ny) || map[nx][ny]=='.') {
					flag = false;
					break;
				}
				
				if (map[nx][ny] != now) {
					list.add(new int[] {nx, ny});
				} else {
					break;
				}
			}
			
			if (flag) {
				for (int j = 0; j < list.size(); j++) {
					int[] temp = list.get(j);
					map[temp[0]][temp[1]] = now;
				}
			}
		}
	}

	static boolean isIn(int r, int c) {
		return 1 <= r && r <= 6 && 1 <= c && c <= 6;
	}

}
