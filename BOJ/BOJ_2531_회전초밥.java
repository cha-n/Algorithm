package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2531_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] sushi = new int[N + k - 1];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		for (int i = N; i < N + k - 1; i++) {
			sushi[i] = sushi[i - N];
		}

		Map<Integer, Integer> eat = new HashMap<Integer, Integer>();

		for (int i = 0; i < k; i++) { // 처음 k개 먹음
			if (eat.containsKey(sushi[i])) {	// 같은 종류의 초밥을 이미 먹었으면 1 더한다.
				eat.put(sushi[i], eat.get(sushi[i]) + 1);
			} else {
				eat.put(sushi[i], 1);	// 아직 안 먹었으면 1
			}
		}

		int kindOfSushi = Integer.MIN_VALUE; // 먹을 수 있는 초밥 가짓수
		if (eat.containsKey(c)) { // 쿠폰
			kindOfSushi = Math.max(kindOfSushi, eat.size());
		} else {
			kindOfSushi = Math.max(kindOfSushi, eat.size() + 1);
		}

		for (int i = k; i < N + k - 1; i++) {
			if (eat.get(sushi[i - k]) == 1) {	// 먹은 초밥 개수가 1이면 map에서 삭제
				eat.remove(sushi[i - k]);
			} else {	// 1보다 크면 1 뺀다.
				eat.put(sushi[i - k], eat.get(sushi[i - k]) - 1);
			}

			if (eat.containsKey(sushi[i])) {	// 같은 종류의 초밥을 이미 먹었으면 1 더한다.
				eat.put(sushi[i], eat.get(sushi[i]) + 1);
			} else {
				eat.put(sushi[i], 1);
			}

			// 쿠폰
			if (eat.containsKey(c)) {
				kindOfSushi = Math.max(kindOfSushi, eat.size());
			} else { // 안 먹었으면 1 더함
				kindOfSushi = Math.max(kindOfSushi, eat.size() + 1);
			}
			System.out.println(eat);
		}

		System.out.println(kindOfSushi);
	}

}
