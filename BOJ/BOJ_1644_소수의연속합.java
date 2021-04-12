// 1644, 소수의 연속합
package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1644 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		boolean[] isPrime = new boolean[4000001];
		Arrays.fill(isPrime, true);
		isPrime[1] = false;

		for (int i = 2; i < 4000001; i++) {
			for (int j = i * 2; j < 4000001; j += i) {
				isPrime[j] = false;
			}
		}

		List<Integer> primeNumber = new ArrayList<Integer>();
		for (int i = 2; i <= N; i++) {
			if (isPrime[i]) {
				primeNumber.add(i);
			}
		}

		int sum = 0;
		int start = 0, end = 0;

		int result = 0;
		while (true) {
			if (sum >= N) {
				if (sum == N)
					result++;
				sum -= primeNumber.get(start++);
			} else {
				if (end == primeNumber.size())
					break;
				sum += primeNumber.get(end++);
			}
		}

		System.out.println(result);
	}

}
