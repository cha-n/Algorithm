package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_18513_샘터_배열 {

	static int loc = 150000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		boolean[] arr = new boolean[loc + loc + 1];
		Queue<Integer> q = new LinkedList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			q.offer(temp);
			arr[temp + loc] = true;
		}

//		System.out.println(Arrays.toString(arr));
		long result = 0;
		int distance = 0;
		while (!q.isEmpty()) {
			int len = q.size();
			distance++;
			for (int i = 0; i < len; i++) {
				int temp = q.poll();
//				System.out.println("temp: "+temp + " : "+ arr[temp+loc]);
				if (!arr[temp + loc - 1]) {
					arr[temp + loc - 1] = true;
					q.offer(temp - 1);
					result += distance;
					if (--K == 0) {
						System.out.println(result);
						return;
					}
				}
				if (!arr[temp + loc + 1]) {
					arr[temp + loc + 1] = true;
					q.offer(temp + 1);
					result += distance;
					if (--K == 0) {
						System.out.println(result);
						return;
					}
				}
			}
		}

	}

	static boolean isIn(int x) {
		return -100000000 <= x && x <= 100000000;
	}
}
