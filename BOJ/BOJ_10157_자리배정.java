// 10157, 자리배정
package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_10157_자리배정 {

	static int C, R, K;
	static int[][] map;
	// delta: 상, 우, 하, 좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[R + 1][C + 1];
		K = Integer.parseInt(br.readLine());
		// 입력 끝

		int n = 1, r = R + 1, c = 1, d = 0;
		while (n <= R * C) {
			if ((r + dx[d] > R || r + dx[d] < 1 || c + dy[d] > C || c + dy[d] < 1) || map[r + dx[d]][c + dy[d]] != 0) {
				d += 1;
				if (d == 4)
					d = 0;
				// System.out.println("방향 전환");
			}

			int nr = r + dx[d];
			int nc = c + dy[d];
			if (n == K) {
				sb.append(String.format("%d %d", nc, R - nr + 1));
				break;
			}
			// System.out.println(nr+" "+nc);
			map[nr][nc] = n++;
			r = nr;
			c = nc;
//			for (int [] row: map) {
//				System.out.println(Arrays.toString(row));
//			}
//			System.out.println();
		}
		if (n == R * C + 1) {
			sb.append(0);
		}
		System.out.println(sb);
	}

}
