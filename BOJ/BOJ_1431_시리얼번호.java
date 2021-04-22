package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1431_시리얼번호 {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] strs = new String[N];
		for (int i=0;i<N;i++) {
			strs[i] = br.readLine();
		}
		
		Arrays.sort(strs, new Comparator<String>() {


			@Override
			public int compare(String s1, String s2) {
				// TODO Auto-generated method stub
				if (s1.length()!= s2.length()) {
					return Integer.compare(s1.length(), s2.length());
				}
				
				int s1Sum = 0;
				int s2Sum = 0;
				for (int i=0;i<s1.length();i++) {
					if (Character.isDigit(s1.charAt(i))) {
						s1Sum += Integer.parseInt(s1.substring(i, i+1));
					}
					if (Character.isDigit(s2.charAt(i))) {
						s2Sum += Integer.parseInt(s2.substring(i, i+1));
					}
				}
				if (s1Sum != s2Sum) {
					return Integer.compare(s1Sum, s2Sum);
				}
				
				
				return s1.compareTo(s2);
			}
				
			
			
		});
		StringBuilder sb = new StringBuilder();
		for (String str: strs) {
			sb.append(str+"\n");
		}
		System.out.println(sb);
	}
}
