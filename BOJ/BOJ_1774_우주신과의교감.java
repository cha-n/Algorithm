package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1774_우주신과의교감 {

	static int N, M;
	static int[] parents;

	static int find(int x) {
		if (parents[x] == x)
			return x;
		return parents[x] = find(parents[x]);
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Point[] points = new Point[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i] = new Point(x, y);
		}
		
//		for (Point point: points) {
//			System.out.println(point);
//		}
		
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		Edge[] edges = new Edge[N * (N - 1) / 2];
		for (int i = 0, n = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
//				System.out.println("i: "+i+" j: "+j);
				edges[n++] = new Edge(i+1, j+1, getDistance(points[i+1], points[j+1]));
			}
		}
		
//		for (Edge edge: edges) {
//			System.out.println(edge);
//		}
		
		Arrays.sort(edges);

		int v = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (union(x, y)) v++;
		}
		double result = 0;
		if (v == N - 1) {
			System.out.println(String.format("%.2f", result));
		} else {
			for (Edge edge : edges) {
				if (union(edge.from, edge.to)) {
					result += edge.weight;
					if (++v == N - 1) {
						System.out.println(String.format("%.2f", result));
						break;
					}
				}
			}
		}

	}

	static double getDistance(Point p1, Point p2) {
		int x1 = p1.x;
		int y1 = p1.y;
		int x2 = p2.x;
		int y2 = p2.y;
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double weight;

		Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
		
	}

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return x + ", " + y;
		}
	}
}
