package swea;

import java.io.*;
import java.util.*;

public class Solution_1251_하나로 {

	static int T, N;
	static int[] parents;

	static class Edge implements Comparable<Edge> { // 간선
		int from;
		int to;
		long distance;

		public Edge(int from, int to, long distance) {
			super();
			this.from = from;
			this.to = to;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", distance=" + distance + "]";
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.distance, o.distance);
		}

	}

	static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if (a == b)
			return false;
		parents[b] = a;
		return true;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine()); // 테스트케이스 개수

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine()); // 섬 개수

			int[] xs = new int[N];
			int[] ys = new int[N];

			double E; // 환경 부담 세율
			// x 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				xs[i] = Integer.parseInt(st.nextToken());
			}
			// y 입력
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				ys[i] = Integer.parseInt(st.nextToken());
			}

			long[][] dis = new long[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) {
						dis[i][j] = 0;
					} else {
						dis[i][j] = (long) (xs[j] - xs[i]) * (xs[j] - xs[i]) + (long) (ys[j] - ys[i]) * (ys[j] - ys[i]);
					}
				}
			}

			E = Double.parseDouble(br.readLine());

			Edge[] edgeList = new Edge[N * (N - 1) / 2];
			int count = 0;
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < i; j++) {
					edgeList[count++] = new Edge(j, i, dis[i][j]);
				}
			}

			Arrays.sort(edgeList);
//			System.out.println(Arrays.toString(edgeList));

			parents = new int[N];
			for (int i = 0; i < N; i++) {
				parents[i] = i;
			}

			double result = 0;
			count = 0;

			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					result += E * edge.distance;
					count++;
					if (count == N - 1)
						break;
				}
			}

			sb.append("#" + (t + 1) + " " + (long)Math.round(result)+ "\n");
		}

		System.out.println(sb);

	}

}
