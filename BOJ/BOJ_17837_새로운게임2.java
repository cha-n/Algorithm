package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17837_새로운게임2 {

	static int N, K;
	static int[][] map;
	static List<List<List<Integer>>> list;
	static int[][] info;

	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 0:흰, 1:빨, 2:파

		list = new ArrayList<List<List<Integer>>>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<List<Integer>>());
			for (int j = 0; j <= N; j++) {
				list.get(i).add(new ArrayList<Integer>());
			}
		}

		info = new int[K + 1][3]; // 행, 열, 이동방향

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
			info[i][2] = Integer.parseInt(st.nextToken());
			list.get(info[i][0]).get(info[i][1]).add(i);
		}
		// 입력 끝
		System.out.println(solve());
	}

	static int solve() {
		int t = 0;
		while (t++ < 1000) {
			for (int i = 1; i <= K; i++) {
				int r = info[i][0];
				int c = info[i][1];
				int d = info[i][2];
				int nr = r + dx[d];
				int nc = c + dy[d];
				// 위에 있는 거 다 가지고 가야함..힘들다...
				int startIdx = list.get(r).get(c).indexOf(i);
				int size = list.get(r).get(c).size();
				List<Integer> temp = new ArrayList<>(list.get(r).get(c).subList(startIdx, size));
				for (int j = size - 1; j >= startIdx; j--) {
					int rm = list.get(r).get(c).remove(j);
				}
				
				// 이동
				if (!isIn(nr, nc) || map[nr][nc] == 2) {
					d = switchDir(d);
					nr = r + dx[d];
					nc = c + dy[d];
					if (!isIn(nr, nc) || map[nr][nc] == 2) {
						// 방향을 반대로 바꾼 후 이동하려는 칸이 파란색인 경우 가만히
						list.get(r).get(c).addAll(temp);
						info[i][2] = d;
						
					} else { // 움직일 수 있는 경우
						info[i][2] = d;
						if (map[nr][nc]==1) Collections.reverse(temp);
						list.get(nr).get(nc).addAll(temp);
						if (list.get(nr).get(nc).size() >= 4) { // 4개 이상 쌓이면 종료
							return t;
						}
						for (int j = 0; j < temp.size(); j++) { // 위에서 따라온 말 위치 바꿈
							info[temp.get(j)][0] += dx[d];
							info[temp.get(j)][1] += dy[d];
						}
					}
				} else if (map[nr][nc] == 0) { // 흰색
					list.get(nr).get(nc).addAll(temp);
					
					if (list.get(nr).get(nc).size() >= 4) { // 4개 이상 쌓이면 종료
						return t;
					}
					for (int j = 0; j < temp.size(); j++) { // 위에서 따라온 말 위치 바꿈
						info[temp.get(j)][0] += dx[d];
						info[temp.get(j)][1] += dy[d];
					}
				} else { // 빨간색
					Collections.reverse(temp);
					list.get(nr).get(nc).addAll(temp);
					if (list.get(nr).get(nc).size() >= 4) { // 4개 이상 쌓이면 종료
						return t;
					}
					for (int j = 0; j < temp.size(); j++) { // 위에서 따라온 말 위치 바꿈
						info[temp.get(j)][0] += dx[d];
						info[temp.get(j)][1] += dy[d];
					}
				}
			}
		}
		
		return -1;
	}
	
	static int switchDir(int x) {
		if (x == 1) return 2;
		else if (x == 2) return 1;
		else if (x == 3) return 4;
		else return 3;
	}

	static boolean isIn(int r, int c) {
		return 1 <= r && r <= N && 1 <= c && c <= N;
	}
}
