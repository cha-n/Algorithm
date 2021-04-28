package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_12904_A와B {

	static String S, T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();

		List<Character> charListT = new ArrayList<Character>();
		for (int i = 0; i < T.length(); i++) {
			charListT.add(T.charAt(i));
		}

		// T의 길이가 S의 길이보다 길 때까지
		int index = charListT.size() - 1;
		while (charListT.size() > S.length()) {
//			System.out.println(charListT);
			if (charListT.get(index) == 'A') {
				charListT.remove(index);
			} else {
				charListT.remove(index);
				charListT = reverse(charListT);
			}
			index--;
		}
//		System.out.println(charListT);
		// 같은지 아닌지 확인
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) != charListT.get(i).charValue()) {
				System.out.println(0);
				System.exit(0);
			}
		}
		System.out.println(1);
		// System.out.println(charListT);
	}

	static List<Character> reverse(List<Character> list) {
		List<Character> result = new ArrayList<Character>();
		int index = list.size() - 1;
		while (index >= 0) {
			result.add(list.get(index--));
		}
		return result;
	}
}
