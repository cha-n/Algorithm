package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_15650_N과M2 {

	static int N, M;
	static int[] combs;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		combs = new int[M];
		combination(0, 1);
	}

	static void combination(int cnt, int start) {
		if (cnt==M) {
			for (int x: combs) {
				System.out.print(x+" ");
			}
			System.out.println();
			return;
		}
		
		for (int i=start;i<=N;i++) {
			combs[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
}
