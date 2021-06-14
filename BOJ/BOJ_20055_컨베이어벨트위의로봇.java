package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_20055_컨베이어벨트위의로봇 {

	static int N, K, A[];
	static boolean[] robot;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[2 * N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 2 * N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		robot = new boolean[N];

		int t = 0;
		while (true) {
			t++;
			rotate();
			move();
			set();
			if (count() >= K) {
				break;
			}

		}
		System.out.println(t);
	}

	static void rotate() {
		int temp = A[2 * N - 1];
		for (int i = 2 * N - 1; i > 0; i--) {
			A[i] = A[i - 1];
		}
		A[0] = temp;

		robot[N - 1] = false; // 내리는 곳에 있는 로봇은 내린다.
		for (int i = N - 2; i > 0; i--) {
			robot[i] = robot[i - 1];
		}
		robot[0] = false;
	}

	static void move() {
		// 로봇이 내리는 위치가 아니고,
		// 이동하려는 칸에 로봇이 없으며,
		// 그 칸의 내구도가 1 이상 남아 있어야 한다.
		for (int i = N - 1; i >= 0; i--) {
			if (robot[i]) {
				if ((i < N - 1) && !robot[i + 1] && A[i + 1] >= 1) {
					robot[i] = false;
					robot[i + 1] = true;
					A[i + 1]--; // 이동하면 그 칸의 내구도는 즉시 1만큼 감소
				}
			}
		}
	}

	static void set() { // 올리는 위치에 올리면 내구도는 즉시 1만큼 감소
		if (A[0] != 0) {
			A[0]--;
			robot[0] = true;
		}
	}

	static int count() {
		int cnt = 0;
		for (int i = 0; i < 2 * N; i++) {
			if (A[i] == 0) {
				cnt++;
			}
		}
		return cnt;
	}
}
