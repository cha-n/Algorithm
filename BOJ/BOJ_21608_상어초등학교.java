package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_21608_상어초등학교 {

	static int N;
	static int[][] map;
	static int[][] students;
	static int[] students_;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		students = new int[N * N + 1][4];
		students_ = new int[N * N + 1];
		for (int i = 1; i <= N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				students[student][j] = Integer.parseInt(st.nextToken());
			}
			students_[i] = student;
		}
		// 입력 끝

//		System.out.println(Arrays.toString(students_));
		for (int i = 1; i <= N * N; i++) {
			solve(students_[i]);
//			for (int[] arr : map) {
//				System.out.println(Arrays.toString(arr));
//			}
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int student = map[i][j];
				int like = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (isIn(nx, ny) && map[nx][ny] != 0) {
						if (map[nx][ny] == students[student][0] || map[nx][ny] == students[student][1]
								|| map[nx][ny] == students[student][2] || map[nx][ny] == students[student][3]) {
							like++; // 인접한 칸에 좋아하는 학생이 있음
						}
					}
				}
				if (like==4) {
					result += 1000;
				} else if (like==3) {
					result+=100;
				}else if (like==2) {
					result+=10;
				}else if (like==1) {
					result+=1;
				}
			}
		}
		System.out.println(result);
//		for (int[] arr: map) {
//			System.out.println(Arrays.toString(arr));
//		}
	}

	static void solve(int student) {
//		System.out.println("=======================================student: " + student);
		int max = Integer.MIN_VALUE;
		int[] temp = new int[2];
		List<int[]> list = new ArrayList<int[]>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (map[i][j] == 0) { // 비어있는 칸 중
					int like = 0;
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (isIn(nx, ny) && map[nx][ny] != 0) {
							if (map[nx][ny] == students[student][0] || map[nx][ny] == students[student][1]
									|| map[nx][ny] == students[student][2] || map[nx][ny] == students[student][3]) {
								like++; // 인접한 칸에 좋아하는 학생이 있음
							}
						}
					}
					if (like > max) {
						list.clear();
						list.add(new int[] { i, j });
						max = like;

					} else if (like == max) {
						list.add(new int[] { i, j });
					}
				}
			}
		}

		if (list.size() == 1) { // 1번을 만족하는 칸이 한 개
			map[list.get(0)[0]][list.get(0)[1]] = student;
//			System.out.println("1번");
//			return true;
		} else {
//			System.out.println("list: " + list.size());
//			for (int i = 0; i < list.size(); i++) {
//				System.out.print(Arrays.toString(list.get(i)) + " ");
//			}
//			System.out.println();
			int idx = -1;
			max = Integer.MIN_VALUE;

			List<int[]> list2 = new ArrayList<int[]>();

			for (int i = 0; i < list.size(); i++) {
				int cnt = 0;

				int x = list.get(i)[0];
				int y = list.get(i)[1];

				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (isIn(nx, ny) && map[nx][ny] == 0) {
						cnt++;
					}
				}

				if (cnt > max) {
					list2.clear();
					list2.add(list.get(i));
					max = cnt;

				} else if (cnt == max) {
					list2.add(list.get(i));
				}
			}

//			System.out.println("인접한 칸 제일 많이 빈 칸 : " + idx);
			if (list2.size() == 1) {
				map[list2.get(0)[0]][list2.get(0)[1]] = student;
//				System.out.println("2번");
			} else {
				Collections.sort(list2, new Comparator<int[]>() {

					@Override
					public int compare(int[] o1, int[] o2) {
						if (o1[0] == o2[0]) {
							return Integer.compare(o1[1], o2[1]);
						}
						return Integer.compare(o1[0], o2[0]);
					}
				});

				map[list2.get(0)[0]][list2.get(0)[1]] = student; // 행 가장 작은 칸, 열 가장 작은 칸
//				System.out.println("3번");
			}

		}

	}

	static int likeNext(int x, int y) {
		int student = map[x][y];
		int like = 0;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (isIn(nx, ny) && map[nx][ny] != 0) {
				if (map[nx][ny] == students[student][0] || map[nx][ny] == students[student][1]
						|| map[nx][ny] == students[student][2] || map[nx][ny] == students[student][3]) {
					like++; // 인접한 칸에 좋아하는 학생이 있음
				}
			}
		}
		return like;
	}

//	static int getScore(int x, int y) {
//		int student = map[x][y];
//		int like = 0;
//		for (int i=0;i<4;i++) {
//			int nx = x+dx[i];
//			int ny = y+dy[i];
//			if (isIn(nx, ny) && map[nx][ny] != 0) {
//				if (map[nx][ny] == students[student][0] || map[nx][ny] == students[student][1]
//						|| map[nx][ny] == students[student][2] || map[nx][ny] == students[student][3]) {
//					like++; // 인접한 칸에 좋아하는 학생이 있음
//				}
//			}
//		}
//	}
//	

	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
