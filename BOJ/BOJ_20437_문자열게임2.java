package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_20437_문자열게임2 {

	static int T, K;
	static String W;
	static List<List<Integer>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			W = br.readLine();
			K = Integer.parseInt(br.readLine());

			list = new ArrayList<List<Integer>>();
			for (int i = 0; i < 26; i++) {
				list.add(new ArrayList<Integer>());
			}

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;

			
			
			
			for (int i = 0; i < W.length(); i++) {
				int idx = W.charAt(i) - 'a';

//			System.out.println(i+" "+idx+" "+W.charAt(i));

				list.get(idx).add(i);

				if (list.get(idx).size() == K) {
//				System.out.println("**] "+idx + " "+W.charAt(i));
//				System.out.println(list.get(idx).get(K-1)+" "+list.get(idx).get(0));
					int length = list.get(idx).get(K - 1) - list.get(idx).get(0) + 1;
					min = Math.min(min, length);
					max = Math.max(max, length);
					list.get(idx).remove(0);
				}
//			print();
			}

//		System.out.println(min+" "+max);
			if (min == Integer.MAX_VALUE) {
				sb.append(-1 + "\n");
			} else {
				sb.append(min + " " + max + "\n");
			}
		}
		System.out.println(sb);
	}

	static void print() {
		for (int i = 0; i < 26; i++) {
			System.out.print(list.get(i).size() + " ");
		}
		System.out.println();
	}
}
