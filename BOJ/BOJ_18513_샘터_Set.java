package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_18513_샘터_Set {

	static int loc = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<>();

		Set<Integer> set = new HashSet<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			q.offer(temp);
			set.add(temp);
		}

		long result = 0;
		int distance = 0;
		while (!q.isEmpty()) {
			int len = q.size();
			distance++;
			for (int i = 0; i < len; i++) {
				int temp = q.poll();
//				System.out.println("temp: "+temp + " set: "+set);

				if (!set.contains(temp - 1)) {
					set.add(temp - 1);
					q.offer(temp - 1);
//					System.out.println("1: "+set + " K :"+K);
					result += distance;
					if (--K == 0) {
						System.out.println(result);
						return;
					}
				}
				if (!set.contains(temp + 1)) {
					set.add(temp + 1);
					q.offer(temp + 1);
//					System.out.println("2: "+set+ " K: "+K);
					result += distance;
					if (--K == 0) {
						System.out.println(result);
						return;
					}
				}
			}
		}

	}

}
