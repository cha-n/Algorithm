package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11497_통나무건너뛰기 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			PriorityQueue<Integer> pq = new PriorityQueue<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
			// 입력 끝

			int[] logs = new int[N];
			for (int i = 0; i < N / 2; i++) {	// 양 끝에서부터 작은 거 순서대로 넣음
				logs[i] = pq.poll();
				logs[N - 1 - i] = pq.poll();
			}

			if (!pq.isEmpty()) { // 홀수개
				logs[N / 2] = pq.poll();
			}

			int level = logs[N - 1] - logs[0];	// 난이도
			for (int i = 0; i < N - 1; i++) {
				level = Math.max(Math.abs(logs[i + 1] - logs[i]), level);
			}

			sb.append(level + "\n");
		}
		System.out.println(sb);
	}

}
