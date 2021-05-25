package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14575_뒤풀이 {

	static int N, T, S;

	public static void main(String[] args) throws IOException {
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println((int)Math.pow(10, 6)*1000);
//		System.out.println((int)Math.pow(10,  9));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		S = Integer.MAX_VALUE;

		int[][] arr = new int[N][2];
		int lSum = 0, rSum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			lSum += arr[i][0];
			rSum += arr[i][1];
		}

		// 입력 끝
//		for (int[] row : arr) {
//			System.out.println(Arrays.toString(row));
//		}

//		long tot = 0;
//		for (int[] row: arr) {
//			tot += row[0];
//			if (tot > T) {
//				System.out.println(-1);
//				System.exit(0);
//			}
//		}

		if (lSum > T || rSum < T) {
			System.out.println(-1);
			System.exit(0);
		}

		int left = 0;
		int right = (int) Math.pow(10, 6);

		while (left <= right) {

			int mid = (left + right) / 2;
//			System.out.println(left + "~" + right + " : " + mid);
			int tot = 0;
			int flag = -1; // 0: mid늘려야함. 1: mid줄여야함

			for (int i = 0; i < N; i++) {

				if (arr[i][0] > mid) {
					flag = 0;
					break;
				} else {
					tot += Math.min(mid, arr[i][1]);
				}
			}

			if (flag == 0) {
				left = mid + 1;
			} else {
				if (tot >= T) {
					right = mid - 1;
					S = Math.min(S, mid);
//					System.out.println("mid: "+mid+ " right: "+right);
				} else {
					left = mid + 1;
//					System.out.println("left: "+left);
				}
			}

		}

		System.out.println(S);
	}

}
