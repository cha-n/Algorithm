package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_15810_풍선공장 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		int x = 1000000*1000000;
//		System.out.println(x);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
			return Long.compare(a[0], b[0]);
		});

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			Long temp = Long.parseLong(st.nextToken());
			pq.add(new long[] {temp, temp});
		}
		
		long result = 0;
		while (M-->0) {
			long[] temp = pq.poll();
//			System.out.println(Arrays.toString(temp));
			result = temp[0];
			pq.add(new long[] {temp[0]+temp[1], temp[1]});
		}
		System.out.println(result);
	}

}
