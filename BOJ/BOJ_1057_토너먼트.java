package BOJ;

import java.util.*;

public class BOJ_1057_토너먼트 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int jimin = sc.nextInt();
		int hansu = sc.nextInt();
		int round = 0;

		// 대결하지 않는 경우는 없다.
		while (jimin != hansu) {
			jimin -= jimin / 2;
			hansu -= hansu / 2;
			round++;
		}
		System.out.println(round);
	}
}
