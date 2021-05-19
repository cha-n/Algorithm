package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1662_압축 {
	static int N;
	static String s;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();

		System.out.println(dfs() + 1);
	}

	static int idx = 0;

	static int dfs() {

		int temp = 0;

		while (idx < s.length()) {

			if (idx != s.length() - 1) {
				if (Character.isDigit(s.charAt(idx + 1))) {
					temp += 1;
				} else if (s.charAt(idx + 1) == '(') {
					int mul1 = s.charAt(idx) - '0';
					idx++;	// (의 idx 무시. 한 칸 뒤로 가서 dfs 시작
					int mul2 = dfs();
					temp--;	// temp--: '('의 바로 앞에 숫자는 압축횟수로, 더해지면 안된다. 
					temp += mul1 * mul2;
				} else if (s.charAt(idx + 1) == ')' || idx + 1 == s.length()) {
					return temp;
				}
			}
			idx++;
		}
		return temp;
	}

}

//3(3(3(2(2)2(2))))
//108
//6(22)122
//15
