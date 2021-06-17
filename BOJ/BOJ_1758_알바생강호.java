package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1758_알바생강호 {

	static int N, arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		long res = 0;

		Arrays.sort(arr);

		for (int i = N; i >= 1; i--) {
			long tip = arr[i] - (N - i);
			if (tip >= 0) {
				res += tip;
			}
		}

		System.out.println(res);

	}

}
