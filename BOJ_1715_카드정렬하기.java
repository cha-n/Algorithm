package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1715_카드정렬하기 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> cards = new PriorityQueue<>();	// 작은 카드 묶음부터 poll 되도록
		for (int i = 0; i < N; i++) {
			cards.offer(Integer.parseInt(br.readLine()));
		}

		int comparisonCount = 0;
		while (cards.size() >= 2) {
			int cards_temp1 = cards.poll();
			int cards_temp2 = cards.poll();	// 작은 카드 묶음 두 개를 뽑아 합친다.
			
			comparisonCount += (cards_temp1 + cards_temp2);
			cards.offer(cards_temp1 + cards_temp2);	
		}
		System.out.println(comparisonCount);
	}

}
