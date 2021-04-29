package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1922_네트워크연결 {

	static int[] parents;

	static int findSet(int x) {
		if (parents[x] == x)
			return x;
		return parents[x] = x;
	}

	static boolean union(int x, int y) {
		x = findSet(parents[x]);
        y = findSet(parents[y]);
		if (x == y)
			return false;
		parents[y] = x;
		return true;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		Edge[] edgeList = new Edge[M];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(a, b, c);
		}

		Arrays.sort(edgeList);
		
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		int totalCost = 0;
		int n = 1;
		for (Edge temp: edgeList) {
			if (union(temp.from, temp.to)) {
				totalCost += temp.cost;
				if ((++n) == N) break;
			}
		}
		System.out.println(totalCost);

	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;

		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
		
		public String toString() {
			return from+"~"+to+": "+cost;
		}
	}

}
