package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16987_계란으로계란치기 {

	static int N;
	static int[] S, W;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		W = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			S[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 끝

		dfs(0);
		System.out.println(max);

	}

	static void dfs(int cnt) {
		//System.out.println("dfs 호출 ==> cnt: " + cnt + " " + Arrays.toString(S));
		if (cnt == N) {
			int temp = 0;
			for (int i = 0; i < N; i++) {
				if (S[i] <= 0) {
					temp++;
				}
			}
			max = Math.max(max, temp);
			//System.out.println("*: temp: " + temp + " " + Arrays.toString(S));
			return;
		}

		if (S[cnt] <= 0) {
			//System.out.println("지금 쥔 달걀 깨져있음");
			dfs(cnt + 1);
			return;
		}

		boolean flag = false;	// 치지 않고 넘어가는 경우
		for (int i = 0; i < N; i++) {
			if (i == cnt) continue;
			if (S[i] <= 0) continue;
			flag = true;
			S[cnt] -= W[i];
			S[i] -= W[cnt];
			//System.out.println("깨고 " + cnt + " " + i + " " + Arrays.toString(S));
			dfs(cnt + 1);
			S[i] += W[cnt];	// 재귀 호출 전으로 되돌리기
			S[cnt] += W[i];
		}
		//System.out.println(cnt+ " 못 깨고" + Arrays.toString(S));
		// 아무것도 못 깼다? 다음 계란으로 넘어가야됨. 
		// 깼으면 for문 안에서 다음 계란으로 넘어갔으니까 이 코드까지 올 필요 없음
		// boolean 형의 flag변수 하나 만들어서 체크한다.
		if (!flag) dfs(cnt + 1);
	}

}
