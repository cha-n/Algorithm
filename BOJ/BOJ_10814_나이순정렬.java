package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_10814_나이순정렬 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[][] members = new String[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			members[i][0] = st.nextToken();
			members[i][1] = st.nextToken();
		}
		
		Arrays.sort(members, (o1, o2)->{
			return Integer.compare(Integer.parseInt(o1[0]), Integer.parseInt(o2[0]));
		});
		
		
//		Arrays.sort(members, new Comparator<String[]>(){
//			public int compare(String[] o1, String[] o2) {
//				return Integer.compare(Integer.parseInt(o1[0]), Integer.parseInt(o2[0]));
//				//return Integer.parseInt(o1[0])-Integer.parseInt(o2[0]);
//			}
//
//		});
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<members.length;i++) {
			sb.append(members[i][0]+" "+members[i][1]+"\n");
		}
		System.out.println(sb);

	}

}
