package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_19948_음유시인영재 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String poem = br.readLine();
		int space = Integer.parseInt(br.readLine());
		int key[] = new int[26];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 26; i++) {
			key[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(poem);
		StringBuilder sb = new StringBuilder();

		while (st.hasMoreTokens()) {
			if (space < 0) {
				System.out.println(-1);
				return;
			}
			String temp = st.nextToken();
//			System.out.println(temp);
			sb.append(Character.toUpperCase(temp.charAt(0)));
			for (int i = 0; i < temp.length(); i++) {
				if (i > 0 && temp.charAt(i - 1) == temp.charAt(i)) {
					continue;
				}

				int idx = (int) (Character.toLowerCase(temp.charAt(i)) - 'a');
				key[idx]--;
				if (key[idx] < 0) {
					System.out.println(-1);
					return;
				}
			}
			space--;
		}
		
		System.out.println(Arrays.toString(key));

		for (int i = 0; i < sb.length(); i++) {
			if (i > 0 && sb.charAt(i - 1) == sb.charAt(i)) {
				continue;
			}
			int idx = (int) (Character.toLowerCase(sb.charAt(i)) - 'a');
			key[idx]--;
			if (key[idx] < 0) {
				System.out.println(-1);
				return;
			}
		}

		System.out.println(sb);
	}

}
