package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_18428_감시피하기 {

	static int N;
	static char[][] map;
	static List<Point> empty, teachers;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		empty = new ArrayList<>();
		teachers = new ArrayList<>();
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == 'X') {
					empty.add(new Point(i, j));
				} else if (map[i][j] == 'T') {
					teachers.add(new Point(i, j));
				}
			}
		}

//		for (char[] row : map) {
//			System.out.println(Arrays.toString(row));
//		}

		// 조합
		combs = new int[3];
		combination(0, 0);
		System.out.println("NO");
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static boolean avoid() {
		for (int t = 0; t < teachers.size(); t++) {
			Point teacher = teachers.get(t);
			int x = teacher.x;
			int y = teacher.y;
			for (int i = 0; i < 4; i++) {
				int nx = x;
				int ny = y;
				while (isIn(nx + dx[i], ny + dy[i])) {
					if (map[nx + dx[i]][ny + dy[i]] == 'S') { // 학생 만나면 False
						return false;
					} else if (map[nx + dx[i]][ny + dy[i]] == 'O') {
						break; // 장애물 만나면 그 방향 그만
					}
					nx += dx[i];
					ny += dy[i];
				}
			}
		}
		return true; // 다 돌면 학생 안 들킨 거
	}

	static void makeWall(int[] combs) { // 장애물 세움
		for (int i = 0; i < combs.length; i++) {
			Point temp = empty.get(combs[i]);
			map[temp.x][temp.y] = 'O';
		}
	}

	static void cancelWall(int[] combs) { // 장애물 없앰
		for (int i = 0; i < combs.length; i++) {
			Point temp = empty.get(combs[i]);
			map[temp.x][temp.y] = 'X';
		}
	}

	// 조합
	static int[] combs;
	static void combination(int cnt, int start) {

		if (cnt == 3) { // 장애물 세 개 까지 놓을 수 있다.
//			System.out.println(Arrays.toString(combs));
			makeWall(combs);
			if (avoid()) {		// 피할 수 있으면 YES 출력하고 프로그램 종료
				System.out.println("YES");
				System.exit(0);
			}
			cancelWall(combs);
			return;
		}

		for (int i = start; i < empty.size(); i++) {
			combs[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

}