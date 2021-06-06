package BOJ;

import java.io.*;

public class BOJ_4097_수익 {

	static int N, P, tot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			
			N = Integer.parseInt(br.readLine());
			if (N==0) break;
			
			int max = Integer.MIN_VALUE;
			tot=0;
			for (int i = 0; i < N; i++) {
				P = Integer.parseInt(br.readLine());
				tot += P;
				max = Math.max(tot, max);
				if (tot<0) tot=0;
			}
			sb.append(max+"\n");
		}
		System.out.println(sb);
	}

}
