// 2023, 신기한 소수
package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2023_신기한소수 {

	static boolean isPrime(int x) {	
		if (x == 1)
			return false;
		int sqrt = (int)Math.sqrt(x);
		for (int i = 2; i <= sqrt; i++) {
			if (x % i == 0) {
				return false;
			}

		}
		return true;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] primeNumberLessThanTen = { 2, 3, 5, 7 };	
		for (int i : primeNumberLessThanTen) {
			dfs(i + "", 1, N);
		}
	}

	static void dfs(String num, int len, int N) {

		if (len == N) {
			System.out.println(num);
			return;
		}

		int[] oddNums = {1, 3, 5, 7, 9};
		for (int i: oddNums) {
			if (isPrime(Integer.parseInt(num + i))) {	// 자릿수 증가시키면서 소수인지 확인
				dfs(num + i, len + 1, N);
			}
		}

	}

}
