package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_20300_서강근육맨 {

	static int N;
	static long[] t;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		t = new long[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=1;i<=N;i++) {
			t[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(t);
		long M = Long.MIN_VALUE;
		if (N%2==0) {
			for (int i=1;i<=N/2;i++) {
				M = Math.max(M, t[i]+t[N-i+1]);
			}
		} else {
			for (int i=1;i<=N/2;i++) {
				M = Math.max(M, t[i]+t[N-i]);
			}
			M = Math.max(M, t[N]);
		}
		
		System.out.println(M);
	}

}
