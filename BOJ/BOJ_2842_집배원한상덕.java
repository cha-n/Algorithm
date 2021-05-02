package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2842_집배원한상덕 {

	static int N;
	static char[][] map;
	static int[][] altitude;
	static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static boolean[][] visited;
	static int homeCnt, tempHomeCnt;
	static int min = Integer.MAX_VALUE;
	static List<Integer> altitudeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		int startX = 0;
		int startY = 0;
		homeCnt = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'P') {
					startX = i;
					startY = j;
				} else if (map[i][j] == 'K') {
					homeCnt++;
				}
			}
		}
		// 모든 집에 우편 배달해야 한다.
		altitude = new int[N][N];
		altitudeList = new ArrayList<Integer>();
		int idx = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				altitude[i][j] = Integer.parseInt(st.nextToken());
				if (altitudeList.contains(altitude[i][j]))
					continue;
				altitudeList.add(altitude[i][j]);
			}
		}
		//System.out.println("입력끝");
		// 입력 끝

		// 가능한 피로도 찾기
		Collections.sort(altitudeList);
		// 가장 작은 피로도
		solve(startX, startY);
		System.out.println(min);
	}

	static void solve(int startX, int startY) {
		//System.out.println(altitudeList);
		int left = 0, right = 0;
		tempHomeCnt = 0;
		//System.out.println("while밖 "+ altitude[startX][startY]);
		while (left < altitudeList.size()) {
			int start = altitude[startX][startY];
			
			//System.out.println(left+"~"+right);
			if (altitudeList.get(left) <= start && start <= altitudeList.get(right)) {
				tempHomeCnt = 0;
				visited = new boolean[N][N];
				dfs(startX, startY, altitudeList.get(left), altitudeList.get(right));
				//System.out.println(tempHomeCnt);
				if (homeCnt == tempHomeCnt) {
					min = Math.min(altitudeList.get(right) - altitudeList.get(left), min);
					left++;
				} else {
					if (right + 1 == altitudeList.size()) {
						break;
					} else {
						right++;
					}
				}
			}else {
				if (right + 1 == altitudeList.size()) {
					break;
				} else {
					right++;
				}
			}
		}
	}

	static void dfs(int x, int y, int min, int max) {
		visited[x][y] = true;
		//System.out.println(x+", "+y);
		if (map[x][y] == 'K')
			tempHomeCnt++;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isIn(nx, ny) && !visited[nx][ny]) {
				if (min <= altitude[nx][ny] && altitude[nx][ny] <= max) {
					dfs(nx, ny, min, max);
				}
			}
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
