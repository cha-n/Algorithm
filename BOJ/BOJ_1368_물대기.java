package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1368_물대기 {

	static int N;
	static int[] W;
	static int[][] cost;
	static int[] parents;

	static int findSet(int x) {
		if (parents[x] == x)
			return x;
		return parents[x] = findSet(parents[x]);
	}

	static boolean union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x == y)
			return false;
		if (x < y) {
			parents[y] = x;
		} else {
			parents[x] = y;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		W = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			W[i] = Integer.parseInt(br.readLine());
		}

		Edge[] edges = new Edge[N * N - (N * (N - 1) / 2)];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j <= i; j++) {
				int weight = Integer.parseInt(st.nextToken());
				if (i == j) {
					edges[idx++] = new Edge(0, i + 1, W[i + 1]);
				} else {
					edges[idx++] = new Edge(i + 1, j + 1, weight);
				}
			}
		}
//		for (Edge edge : edges) {
//			System.out.println(edge);
//		}

		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}

		// cost 정렬
		Arrays.sort(edges);

		int v = 0;
		int result = 0;
		for (Edge edge : edges) {
			if (union(edge.from, edge.to)) {
				v++;
				result += edge.weight;
			}
		}

		System.out.println(result);

	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		public String toString() {
			return from + "~" + to + ": " + weight;
		}
	}

}
