package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_6137_문자열생성 {

	static int N;
	static char[] cArr;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//		System.out.println('A' < 'B');

		N = Integer.parseInt(br.readLine());
		cArr = new char[N];

		for (int i = 0; i < N; i++) {
			cArr[i] = br.readLine().charAt(0);
		}


		int left = 0;
		int right = N - 1;

		while (left <= right) {
			for (int i = 0; i < 80; i++) {
				if (left > right)
					break;
				if (cArr[left] < cArr[right]) {
					System.out.print(cArr[left]);
					left++;
				} else if (cArr[left] > cArr[right]) {
					System.out.print(cArr[right]);
					right--;
				} else {
					int temp_left = left;
					int temp_right = right;
					while (temp_left <= temp_right && cArr[temp_left] == cArr[temp_right]) {
						temp_left++;
						temp_right--;
					}
					if (cArr[temp_left] <= cArr[temp_right]) {
						System.out.print(cArr[left]);
						left++;
					} else {
						System.out.print(cArr[right]);
						right--;
					}

				}
			}
			System.out.println();
		}

	}

}
