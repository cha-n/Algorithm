package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1790_수이어쓰기2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int temp = 0;
		
		for (int i = 1; i <= N; i++) {
			temp = (int) (Math.log10(i)+1);
			if (k - temp > 0) {
				k -= temp;
			} else {
//				System.out.println(i + " " + temp);
//				System.out.println(k+" "+(k - temp));
				System.out.println((i+"").charAt(k-1));
				return;
			}
		}
		System.out.println(-1);
	}

}
