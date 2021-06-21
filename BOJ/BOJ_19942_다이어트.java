package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_19942_다이어트 {

	static int N;
	static int[] mins;
	static int[][] info;

	static boolean[] visited;
	static List<Integer> list;
	static int min = Integer.MAX_VALUE;
	static int[] res;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		mins = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			mins[i] = Integer.parseInt(st.nextToken());
		}
		info = new int[N][5];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝

		visited = new boolean[N];
		list = new ArrayList<Integer>();
		dfs(0, 0, 0, 0, 0, 0);	
		
		if (min==Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
			for (int i=0;i<res.length;i++) {
				System.out.print((res[i]+1)+" ");
			}
		}
		
	}

	static void dfs(int x, int p, int f, int s, int v, int c) {
//		System.out.println(list+" "+x+" "+p+" "+f+" "+s+" "+v+" "+c);
		
		if (p >= mins[0] && f >= mins[1] && s >= mins[2] && v >= mins[3]) {
			if (c<min) {
				res = new int[list.size()];
				for (int i=0;i<list.size();i++) {
					res[i] = list.get(i);
				}
				min = c;
			}
			return;
		}

		System.out.println(list);
		for (int i = x; i < N; i++) {
			if (!visited[i]) {
				list.add(i);
				visited[i] = true;
				dfs(i + 1, p + info[i][0], f + info[i][1], s + info[i][2], v + info[i][3], c + info[i][4]);
				list.remove(list.size() - 1);
				visited[i] = false;
			}
		}
	}

}
