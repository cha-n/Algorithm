package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17144_미세먼지안녕 {

	static int R, C, T;
	static int[][] map;
	static Queue<int[]> q;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		int[] cleaner = new int[2];
		q = new LinkedList<int[]>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) { // 공기청정기 위치
					if (cleaner[0] == 0) {
						cleaner[0] = i;
					} else {
						cleaner[1] = i;
					}
				} else if (map[i][j] != 0) { // 먼지 좌표
					q.offer(new int[] { i, j });
				}
			}
		}

		while (T-- > 0) {
			dust();
			diffuse(cleaner);
			purify(cleaner);
		}

		int result = 2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result += map[i][j];
			}
		}
		System.out.println(result);

	}

	static void dust() {
		q.clear();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					q.offer(new int[] { i, j });
				}
			}
		}
	}

	static void diffuse(int[] cleaner) {
		int[][] tempSpreadDust = new int[R][C];

		int size = q.size();
		for (int i = 0; i < size; i++) {
			int x = q.peek()[0];
			int y = q.poll()[1];
			if (map[x][y] == 0) {
				continue;
			}
			if (map[x][y] >= 5) {
				int spreadDust = map[x][y] / 5;
				for (int d = 0; d < 4; d++) { // 몇 칸 퍼질 수 있는지
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (isIn(nx, ny) && map[nx][ny] != -1) { // 공기청정기 없거나, 범위 안이면 확산 가능
						tempSpreadDust[nx][ny] += spreadDust;
						tempSpreadDust[x][y] -= spreadDust;
					}
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += tempSpreadDust[i][j];
				if (map[i][j] < 0) {
					if ((i == cleaner[0] && j == 0) || (i == cleaner[1] && j == 0))
						continue;
					map[i][j] = 0;
				}
			}
		}
	}

	static void purify(int[] cleaner) {
		int up = cleaner[0];
		int[] upCycleDx = new int[] { 0, -1, 0, 1 };
		int[] upCycleDy = new int[] { 1, 0, -1, 0 };
		int x = up;
		int y = 0;
		int ex = 0;
		int next = map[x][y];
		for (int i = 0; i < 4; i++) {
			while (isIn(x + upCycleDx[i], y + upCycleDy[i]) && !((x + upCycleDx[i] == up) && (y + upCycleDy[i] == 0))) {
				next = map[x + upCycleDx[i]][y + upCycleDy[i]];
				map[x + upCycleDx[i]][y + upCycleDy[i]] = ex;
				ex = next;
				x += upCycleDx[i];
				y += upCycleDy[i];
			}
		}

		int down = cleaner[1];
		int[] downCycleDx = new int[] { 0, 1, 0, -1 };
		int[] downCycleDy = new int[] { 1, 0, -1, 0 };
		x = down;
		y = 0;
		ex = 0;
		next = 0;
		for (int i = 0; i < 4; i++) {
			while (isIn(x + downCycleDx[i], y + downCycleDy[i])
					&& !((x + downCycleDx[i] == down) && (y + downCycleDy[i] == 0))) {
				next = map[x + downCycleDx[i]][y + downCycleDy[i]];
				map[x + downCycleDx[i]][y + downCycleDy[i]] = ex;
				ex = next;
				x += downCycleDx[i];
				y += downCycleDy[i];
			}
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	static void print() {
		for (int i = 0; i < R; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}
