package LeetCode;

import java.io.*;
import java.util.*;

public class LC_017_LetterCombinationsOfaPhoneNumber {

	
	static String input;
	static Map<Character, String> map;
	static List<String> words;
	static StringBuilder sb = new StringBuilder();
	static String res="";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		map = new HashMap<Character, String>();

		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");
		
		input = br.readLine();
		words = new ArrayList<String>();
		
		
		comb(0, 0);
		System.out.println(words);
	}

	// cnt: "23"일 때 각각 2, 3을 가리키는 인덱스
	static void comb(int cnt, int idx) {
		if (cnt == input.length()) {
			words.add(res);
			//System.out.println(res);
			return;
		}
		char ch = input.charAt(cnt);
		for (int i=0;i<map.get(ch).length();i++) {
			res+=map.get(ch).charAt(i);
			//sb.append(map.get(ch).substring(idx, idx+1));
			comb(cnt+1, i+1);
			res = res.substring(0, res.length()-1);
		}
		
	}
	
}
