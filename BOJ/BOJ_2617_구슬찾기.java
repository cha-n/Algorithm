package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2617_구슬찾기 {

	static int N, M;
	static List<List<Integer>> light, heavy;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		light = new ArrayList<>();
		heavy = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			light.add(new ArrayList<Integer>());
			heavy.add(new ArrayList<Integer>());
		}

		// heavy: 무거운->가벼운
		// light: 가벼운->무거운
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int heavyTemp = Integer.parseInt(st.nextToken());
			int lightTemp = Integer.parseInt(st.nextToken());
			heavy.get(heavyTemp).add(lightTemp);
			light.get(lightTemp).add(heavyTemp);
		}

		int result = 0;
		for (int i = 1; i <= N; i++) {
			if (!hBfs(i)) result++;
			if (!lBfs(i)) result++;
		}
		
		System.out.println(result);
	}

	
	// 현재 구슬보다 무거운 게(혹은 가벼운 게) 절반 이상이면 중간이 될 수 없다.
	
	static boolean lBfs(int x) {
		int[] visited = new int[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.offer(x);
		visited[x] = 1;
		int cnt = 1;

		while (!q.isEmpty()) {
			int now = q.poll();
			for (int bead : light.get(now)) {
				if (visited[bead] == 0) {
					visited[bead] = visited[x] + 1;
					q.offer(bead);
					if (++cnt > (N + 1) / 2)	// 절반 이상 방문했으면 중간 X --> false
						return false;
				}
			}
		}
		return true;
	}

	static boolean hBfs(int x) {
		int[] visited = new int[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.offer(x);
		visited[x] = 1;
		int cnt = 1;

		while (!q.isEmpty()) {
			int now = q.poll();
			for (int bead : heavy.get(now)) {
				if (visited[bead] == 0) {
					visited[bead] = visited[x] + 1;
					q.offer(bead);
					if (++cnt > (N + 1) / 2)	// 절반 이상 방문했으면 중간 X --> false
						return false;
				}
			}
		}
		return true;
	}

}
