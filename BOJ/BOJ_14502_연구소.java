// 14502, 연구소
package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14502_연구소 {

	static int N, M;
	static int[][] lab;
	static List<Integer[]> empty;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][M];
		empty = new ArrayList<Integer[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if (lab[i][j] == 0) {
					empty.add(new Integer[] { i, j });
				}
			}
		}
		// 입력 끝
		
		comb(0, 0);
		System.out.println(max);
	}

	// 빈 곳 중 3 곳을 뽑아 벽 세운다.
	static int[] combs = new int[3];
	static int[][] lab_copy;
	static int max = Integer.MIN_VALUE;
	static int safeArea;	// 안전한 영역
	static void comb(int cnt, int start) {
		if (cnt == 3) {
			lab_copy = new int[N][M];
			for (int i = 0; i < N; i++) {
				lab_copy[i] = lab[i].clone();
			}
			for (int i = 0; i < 3; i++) {
				lab_copy[empty.get(combs[i])[0]][empty.get(combs[i])[1]] = 1; // 세 곳 벽으로 만듦
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (lab_copy[i][j] == 2) {
						spreadVirus(i, j);
					}
				}
			}
			safeArea = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (lab_copy[i][j] == 0) {
						safeArea++;
					}
				}
			}
			max = Math.max(max, safeArea);
			
			return;
		}

		for (int i = start; i < empty.size(); i++) {
			combs[cnt] = i;
			comb(cnt+1, i+1);
		}
	}

	// 바이러스 퍼진다.
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void spreadVirus(int r, int c) {
		lab_copy[r][c] = -1; // 바이러스 퍼짐
		for (int i = 0; i < 4; i++) {
			int nx = r + dx[i];
			int ny = c + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || lab_copy[nx][ny]==1) continue;
			if (lab_copy[nx][ny] == 0) {
				spreadVirus(nx, ny);
			}
		}
	}

	static void print() {
		for (int i=0;i<N;i++) {
			System.out.println(Arrays.toString(lab_copy[i]));
		}
	}

}
