package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_10775_공항 {

	static int P, G, g[], parents[];

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
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		g = new int[P];
		for (int i = 0; i < P; i++) {
			g[i] = Integer.parseInt(br.readLine());
		}
		// 입력 끝

		int cnt = 0;

		parents = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < P; i++) {
//			System.out.println(g[i] + " "+parents[g[i]]);
			int fx = find(g[i]);
			if (fx != 0) {
				union(fx, fx-1);
				cnt++;
			} else {
				break;
			}
//			System.out.println(Arrays.toString(parents));
		}

		System.out.println(cnt);
	}

}
