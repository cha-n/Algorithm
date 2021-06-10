package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_20056_마법사상어와파이어볼 {

	static int N, M, K;
	static List<List<List<Shark>>> list;
	static int[] dx = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // N x N
		M = Integer.parseInt(st.nextToken()); // M개의 파이어볼
		K = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
			for (int j = 0; j < N; j++) {
				list.get(i).add(new ArrayList<Shark>());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r, c, m, s, d, 0);
			list.get(r).get(c).add(shark);
		}

		for (int k = 0; k < K; k++) {
			move(k);
		}
		
		System.out.println(getM());
	}

	
	static int getM() {
		int tot_m = 0;
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				for (int k=0;k<list.get(i).get(j).size();k++) {
					tot_m += list.get(i).get(j).get(k).m;
				}
			}
		}
		return tot_m;
	}
	
	static void move(int k) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int s = 0; s < list.get(i).get(j).size(); s++) {
					if (list.get(i).get(j).get(s).k == (k)) {
						Shark temp = list.get(i).get(j).get(s);
						list.get(i).get(j).remove(s);
						s--;
						int r = temp.r;
						int c = temp.c;
						int nr = r + (temp.s * dx[temp.d])%N;
						int nc = c + (temp.s * dy[temp.d])%N;
						if (nr >= N) nr -= N;
						if (nr < 0) nr += N;

						if (nc >= N) nc -= N;
						if (nc < 0) nc += N;
						temp.r = nr;
						temp.c = nc;
						temp.k += 1;
						list.get(nr).get(nc).add(temp);
					} else {
						break;
					}
				}
			}
		}

		// 이동 후 2개 이상의 파이어볼이 있는 칸
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int size = list.get(i).get(j).size();
				if (size > 1) {
					int tot_m = 0; // 질량의 합
					int tot_s = 0; // 속력의 합
					boolean allEven = true; // 모두 짝수
					boolean allOdd = true; // 모두 홀수
					for (int s = 0; s < size; s++) {
						Shark temp = list.get(i).get(j).get(s);
						if (temp.d % 2 == 0) {
							allOdd = false;
						} else {
							allEven = false;
						}
						tot_m += temp.m;
						tot_s += temp.s;
					}
					list.get(i).get(j).clear();
					int m = (int) (tot_m / 5);
					int s = (int) (tot_s / size);
					if (m > 0) { // 질량이 0인 파이어볼은 소멸되어 없어진다.
						if (allEven || allOdd) {
							for (int d = 0; d < 8; d += 2) {
								list.get(i).get(j).add(new Shark(i, j, m, s, d, k+1));
							}
						} else {
							for (int d = 1; d < 8; d += 2) {
								list.get(i).get(j).add(new Shark(i, j, m, s, d, k+1));
							}
						}
					}

				}
			}
		}
	}

	static class Shark {
		int r;
		int c;
		int m; // 질량
		int s; // 속력
		int d; // 방향
		int k; // 움직인 횟수

		public Shark() {
			super();
		}

		public Shark(int r, int c, int m, int s, int d, int k) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + ", k=" + k + "]";
		}

		
	}
}
