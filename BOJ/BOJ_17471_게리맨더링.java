package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17471_게리맨더링 {

	static int N;
	static int[] populations;
	static int[] combs;
	static List<List<Integer>> list;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		populations = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
		}

		list = new ArrayList<List<Integer>>();	// 인접리스트 생성
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Integer>());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				list.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		// 입력 끝

		for (int i = 1; i <= N / 2; i++) {	// 부분집합
			combs = new int[i];
			comb(0, 1, i);
		}

		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
		
	}

	static void comb(int cnt, int start, int num) {
		if (cnt == num) {
			// combs: 선거구1, combs2: 선거구2
			boolean[] check = new boolean[N + 1];
			for (int n : combs) {
				check[n] = true;
			}
			int[] combs2 = new int[N - num];
			for (int i = 1, j = 0; i < N + 1; i++) {
				if (!check[i])
					combs2[j++] = i;
			}
			
			boolean[] combsGo = go(combs[0], combs);
			
			boolean check1 = connected(check, combsGo);
			if (check1) {
				combsGo = go(combs2[0], combs2);
				for (int i = 1; i <= N; i++) {
					check[i] = !check[i];
				}
				
				boolean check2 = connected(check, combsGo);
				if (check2) {
					// 부분 집합 두 개 다 제대로 연결되어 있으면 인구수 비교
					int population1 = 0;
					int population2 = 0;
					for (int i = 0; i < combs.length; i++) {
						population1 += populations[combs[i]];
					}
					for (int i = 0; i < combs2.length; i++) {
						population2 += populations[combs2[i]];
					}
					min = Math.min(Math.abs(population1 - population2), min);
				}
			}
			return;
		}

		for (int i = start; i <= N; i++) {
			combs[cnt] = i;
			comb(cnt + 1, i + 1, num);
		}
	}

	static boolean connected(boolean[] go, boolean[] comb) {	// 부분집합에 있는 노드끼리 제대로 연결되어있는지 확인
		for (int i = 1; i <= N; i++) {
			if (go[i] != comb[i]) {
				return false;
			}
		}
		return true;
	}

	// BFS로 노드가 연결되어 있는지 확인
	// 부분집합에 포함되어있는 노드만 방문해야 한다.
	static boolean[] go(int x, int[] comb) { // x: 출발 노드
		Set<Integer> set = new HashSet<>();		// 부분집합에 포함되는지 확인하기 위해 set만듦
		for (int n : comb) {
			set.add(n);
		}

		boolean[] visited = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		visited[x] = true;
		q.offer(x);

		while (!q.isEmpty()) {
			int now = q.poll();
			for (int node : list.get(now)) {
				if (!visited[node] && set.contains(node)) {		// 방문 안 했고, set에 포함되어 있으면 방문 가능
					q.offer(node);
					visited[node] = true;
				}
			}
		}
		return visited; // 연결 됐으면 true, 안 됐으면 false 담겨있음
	}
}
