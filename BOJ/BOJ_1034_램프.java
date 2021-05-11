package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1034_램프 {

	static int N, M, K;
	static int max = Integer.MIN_VALUE;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String S = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = S.charAt(j) - '0';
			}
		}
		K = Integer.parseInt(br.readLine());
		// 입력 끝
		visited = new boolean[N];

		// 다르게 생긴 행은 같이 켜질 수 없다.
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;

			int offLamp = 0;
			for (int lamp : map[i]) {
				if (lamp == 0) {
					offLamp++;
				}
			}

			int sameRowCnt = getSameRowCnt(i); // 같은 행 몇 갠지 세고, visited 체크
//			System.out.println(i+" "+offLamp + " "+sameRowCnt);
			if (offLamp % 2 == K % 2 && offLamp <= K) {
				max = Math.max(sameRowCnt, max);
			}
		}
		System.out.println(max);
	}

	static int getSameRowCnt(int r) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			// 배열 두 개의 내용물이 같은지 비교:  Arrays.equals(arr1, arr2)
			if (Arrays.equals(map[i], map[r])) {
				cnt++;
				visited[i] = true;
			}
		}
		return cnt;
	}

}
