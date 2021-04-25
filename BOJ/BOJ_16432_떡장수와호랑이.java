package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16432_떡장수와호랑이 {

	static int N;
	static Map<Integer, Integer[][]> map;	// 날: [떡 종류, 먹었는지안먹었는지]
	static int[] dduk;	// 호라이가 먹은 떡
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new HashMap<Integer, Integer[][]>();	
		dduk = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			Integer[][] temp = new Integer[2][m];
			for (int j = 0; j < m; j++) {
				temp[0][j] = Integer.parseInt(st.nextToken());	// 떡 종류
				temp[1][j] = 0; // 0: 안 먹음 -> 방문 X
			}
			map.put(i, temp);
		}

		dfs(-1, 0);	// ex: -1로 설정 --> -1인 떡 없으니까
		System.out.println(-1);
	}

	

	static void dfs(int ex, int cnt) {
		if (cnt == N) {
			for (int i=0;i<N;i++) {
				System.out.println(dduk[i]);
			}
			System.exit(0);
			return;
		}
		
		for (int i = 0; i < map.get(cnt)[0].length; i++) {
			if (map.get(cnt)[1][i] == 1) continue;	// 이미 먹었으면 넘어간다.
				
			if (ex != map.get(cnt)[0][i]) {		// 전 날 먹은 떡이랑 달라야 먹을 수 있다.
				dduk[cnt] = map.get(cnt)[0][i];
				map.get(cnt)[1][i] = 1;
				dfs(dduk[cnt], cnt + 1);
			}
		}
		
	}

}
