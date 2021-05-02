package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2846_오르막길 {

	static int N;
	static int heights[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		heights = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}

		int idx = 0;
		int start = 0;
		int max = 0;

		while (idx < N - 1) {
			// System.out.println("idx: " + idx + " " + heights[idx]);
			if (heights[idx] < heights[idx + 1]) {
				start = heights[idx];
				while (idx < N - 1 && heights[idx] < heights[idx + 1]) {
					idx++;
				}
				// System.out.println(heights[idx]);
				max = Math.max(max, heights[idx] - start);
				idx++;
			} else {
				idx++;
			}
		}

		System.out.println(max);

	}

}
