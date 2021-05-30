package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_3584_가장가까운공통조상 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int arr[] = new int[N + 1];
			for (int i = 0; i < N - 1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				arr[B] = A;
			}
//			System.out.println(Arrays.toString(arr));
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
//			System.out.println(n1 + " " + n2);

			List<Integer> list1 = new ArrayList<Integer>();
			while (n1 != 0) {
				list1.add(n1);
				n1 = arr[n1];
			}
			List<Integer> list2 = new ArrayList<Integer>();
			while (n2 != 0) {
				list2.add(n2);
				n2 = arr[n2];
			}
//			System.out.println(list1);
//			System.out.println(list2);
//			System.out.println(getNear(list1, list2));
			sb.append(getNear(list1, list2)+"\n");
		}
		System.out.println(sb);
	}

	static int getNear(List<Integer> l1, List<Integer> l2) {

		for (int i = 0; i < l1.size(); i++) {
			int num = l1.get(i);
			for (int j = 0; j < l2.size(); j++) {
				if (num == l2.get(j)) {
					return num;
				}
			}
		}
		return -1;
	}

}
