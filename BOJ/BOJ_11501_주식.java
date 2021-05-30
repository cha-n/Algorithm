package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11501_주식 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			long max = 0;

			int start = N - 1;
			while (start > 0) {

				int idx = start - 1;

				while (idx >= 0 && arr[idx] < arr[start]) {
					idx--;
				}

				idx++;
				for (int i = idx; i <= start; i++) {
					max += (arr[start] - arr[i]);
				}
				start = --idx;
			}
			System.out.println(max);
		}
	}
}
