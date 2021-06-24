package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_15649_N과M1 {

	static int N, M;
	static int[] perms;
	static boolean[] isSelected;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		perms = new int[M];
		isSelected = new boolean[N+1];
		sb = new StringBuilder();
		
		permutation(0);
		System.out.println(sb);
	}
	
	static void permutation(int cnt) {
		if (cnt==M) {
			for (int x: perms) {
				sb.append(x+" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=1;i<=N;i++) {
			if (isSelected[i]) continue;
			
			perms[cnt] = i;
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
		
	}
}
