package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_13458_시험감독 {

	static int N, A[], B, C;

	public static void main(String[] args) throws IOException {
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(1000000*1000000);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		long res = 0;
		for (int i = 0; i < N; i++) {

			if (A[i] > B) {
				A[i] -= B;
				if (A[i] % C == 0) {
					res += (int) (A[i] / C);
				} else {
					res += (int) (A[i] / C) + 1;
				}
			}
			res++;
		}
		System.out.println(res);
	}
}
