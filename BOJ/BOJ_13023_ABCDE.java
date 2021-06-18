package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_13023_ABCDE {

	static int N, M;
	static List<List<Integer>> list;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<Integer>());
		}
		visited = new boolean[N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				dfs(i, 1);
			}
		}
		System.out.println(0);
	}

	static void dfs(int x, int cnt) {
//		System.out.println(x+" "+cnt);
		if (cnt == 5) {
			System.out.println(1);
			System.exit(0);
		}

		visited[x] = true;
		for (int i = 0; i < list.get(x).size(); i++) {
			if (!visited[list.get(x).get(i)]) {
				dfs(list.get(x).get(i), cnt + 1);
			}
		}
		visited[x] = false;	
	}

}
