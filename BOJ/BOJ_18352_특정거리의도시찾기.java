package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_18352_특정거리의도시찾기 {

	static int N, M, K, X;
	static List<List<Integer>> map;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		map = new ArrayList<List<Integer>>();

		for (int i = 0; i <= N; i++) {
			map.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			map.get(A).add(B);
		}
		// 입력 끝
		
		int res[] = solution();
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
	}

	static int[] solution() {
		int[] visited = new int[N + 1];

		visited[X] = 1;

		Queue<Integer> q = new LinkedList<>();
		q.offer(X);

		int t = 0;

		while (!q.isEmpty()) {
			if (t == K)
				break;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int now = q.poll();
				for (int j = 0; j < map.get(now).size(); j++) {
					int next = map.get(now).get(j);
					if (visited[next] == 0) {
						visited[next] = visited[now] + 1;
						q.offer(next);
					}
				}
			}
			t++;
		}

		if (q.isEmpty()) {
			return new int[] { -1 };
		} else {
			int[] res = new int[q.size()];
			int i = 0;
			while (!q.isEmpty()) {
				res[i++] = q.poll();
			}
			Arrays.sort(res);
			return res;
		}
	}

}
