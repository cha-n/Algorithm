package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11403_경로찾기 {

	static int N;
	static int[][] map;
	static int[][] result;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		result = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝

		for (int i = 0; i < N; i++) {
			visited = new int[N];
			dfs(i);
//			System.out.println(Arrays.toString(visited));
			result[i] = visited.clone();
		}

		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void dfs(int x) {
		for (int i = 0; i < N; i++) {
			if (map[x][i] == 1 && visited[i] == 0) {
				visited[i] = 1;
				dfs(i);
			}
		}
	}

}
