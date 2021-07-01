package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14405_피카츄 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		
		if (solve(s)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
//		s = s.replaceAll("pi", "");
//		s = s.replaceAll("ka", "");
//		s = s.replaceAll("chu", "");
//
//		if (s.equals("")) {
//			System.out.println("YES");
//		} else {
//			System.out.println("NO");
//		}
	}

	static boolean solve(String s) {

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i)=='p') {
				if (i+1==s.length() || s.charAt(i+1) != 'i') {
					return false;
				}
				i+=1;
			} else if (s.charAt(i)=='k') {
				if (i+1==s.length() || s.charAt(i+1) != 'a') {
					return false;
				}
				i+=1;
			} else if (s.charAt(i)=='c') {
				if (i+1==s.length() || s.charAt(i+1) != 'h') {
					return false;
				}
				if (i+2==s.length() || s.charAt(i+2) != 'u') {
					return false;
				}
				i+=2;
			} else {
				return false;
			}
		}
		return true;
	}
}
