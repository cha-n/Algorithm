package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16946_벽부수고이동하기4 {

	static int N, M;
	static int[][] map;
	static int[][] zeroGroup;	// 0 묶음의 인덱스 저장
	static boolean[][] visited;
	static List<Integer[]> zeroCoors;	// map값이 0인 곳의 좌표 저장

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		zeroCoors = new ArrayList<Integer[]>();
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				int temp = s.charAt(j) - '0';
				if (temp == 1) {
					map[i][j] = -1;		// 0인 칸의 값을 이동할 수 있는 칸의 개수로 바꿈 -> 1이 입력되면 -1로 저장해둔다.
				} else {
					map[i][j] = 0;
					zeroCoors.add(new Integer[] { i, j });	// 입력이 0이면 리스트에 저장해둔다.
				}
			}	
		}
		// 입력 끝

		zeroGroup = new int[N][M];	
		for (int i = 0; i < zeroCoors.size(); i++) {
			Integer[] zeroCoor = zeroCoors.get(i);
			findZero(zeroCoor[0], zeroCoor[1]);
		}

//		for (int[] row : zeroGroup) {
//			System.out.println(Arrays.toString(row));
//		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != -1) {
					sb.append(0);
				}

				else {
					int canGoSum = 1;
					HashSet<Integer> zeroGroupSelected = new HashSet<>();
					for (int d = 0; d < 4; d++) {
						int temp_x = i + dx[d];
						int temp_y = j + dy[d];
						if (isIn(temp_x, temp_y) && map[temp_x][temp_y] != -1) {
							if (!zeroGroupSelected.contains(zeroGroup[temp_x][temp_y])) {	// 사방으로 갈 수 있는 칸 수 셀 때, idx가 중복되면 안 됨 -> Set으로 중복 제거
								canGoSum += map[i + dx[d]][j + dy[d]];
								zeroGroupSelected.add(zeroGroup[temp_x][temp_y]);
							}
						}
					}
					sb.append(canGoSum % 10);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static List<Coor> zeros;
	static int idx = 1;		// 0 그룹의 인덱스

	static void findZero(int r, int c) {
		zeros = new ArrayList<>();
		if (zeroGroup[r][c] != 0)		// 값이 0이 아니면 이미 탐색된 것 -> return
			return;

		bfs(r, c);
		int canGo = zeros.size();		// 갈 수 있는 칸 수: zeros List의 size
		for (int i = 0; i < zeros.size(); i++) {
			Coor coor = zeros.get(i);
			map[coor.x][coor.y] = canGo;			// map의 값이 0이라면 갈 수 있는 칸의 수로 값 바꿈
			zeroGroup[coor.x][coor.y] = idx;		// zeroGroup: 2차원 배열 --> 0 그룹 인덱스 저장
		}
		idx++;
	}

	static void bfs(int r, int c) {		// 인접해있는 0의 개수 센다.
										// zeros List: 값이 0인 좌표 넣음 -> zeros.size(): 0
		visited[r][c] = true;
		zeros.add(new Coor(r, c));

		Queue<Coor> q = new LinkedList<Coor>();
		q.offer(new Coor(r, c));

		while (!q.isEmpty()) {
			Coor now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (isIn(nx, ny) && visited[nx][ny] == false && map[nx][ny] == 0) {
					visited[nx][ny] = true;
					zeros.add(new Coor(nx, ny));
					q.offer(new Coor(nx, ny));
				}
			}

		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}

	static class Coor {
		int x;
		int y;

		Coor(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Coor [x=" + x + ", y=" + y + "]";
		}
	}
}
