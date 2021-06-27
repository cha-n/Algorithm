package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16398_행성연결 {

	static int N;
	static int[][] C;
	static int[] parents;

	static int find(int x) { 
		if (x==parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x==y) return false;
		
		if (x<y) {
			parents[y] = x;
		} else {
			parents[x] = y;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		C = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				C[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 1+2 2*(1+2)/2
		// 4+3+2+1: N(N+1)/2 ==> 4*5/2 = 10
		Edge[] edges = new Edge[N * (N - 1) / 2];
		int idx = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i < j) {
					edges[idx++] = new Edge(i, j, C[i][j]);
				}
			}
		}
		
		parents = new int[N+1];
		for (int i=1;i<=N;i++) {
			parents[i] = i;
		}
		
		Arrays.sort(edges);
		
		long res = 0;
		
		for (Edge edge: edges) {
			if (union(edge.start, edge.end)) {
				res += (long)edge.weight;
			}
		}
		
		System.out.println(res);
	}

	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int weight;

		Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

}
