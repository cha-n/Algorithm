package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17143_낚시왕 {

	static int R, C, M;
	static int[][] map;
	static Shark[] sharks;
	static int result = 0; // 낚시로 잡은 물고기

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[R + 2][C + 2];

		sharks = new Shark[M + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks[i] = new Shark(i, r, c, s, d, z, true);

			map[r][c] = i;
		}
		int fisherman = 0;
		while (++fisherman <= C) {
			for (int i = 1; i <= R; i++) {
				if (map[i][fisherman] != 0) {
					sharks[map[i][fisherman]].live = false; // 잡는다.
					result += sharks[map[i][fisherman]].z;
					map[i][fisherman] = 0; // 칸 비운다.
					break;
				}
			}
			sharkMove();
		}

		System.out.println(result);
	}

	static void sharkMove() {

		int[][] temp = new int[R + 2][C + 2];
		for (int i = 1; i <= M; i++) {
			// 움직임
			if (!sharks[i].live)
				continue; // 이미 죽어있으면 넘어감

			for (int j = 0; j < sharks[i].s; j++) {
//				System.out.println("j: " + j + " r: " + sharks[i].r + " c: " + sharks[i].c);
				if (sharks[i].d == 1) { // 위
					if (sharks[i].r == 1) {
						sharks[i].d = 2;
						j--;
					} else {
						sharks[i].r -= 1;
					}
				} else if (sharks[i].d == 2) { // 아래
					if (sharks[i].r == R) {
						sharks[i].d = 1;
						j--;
					} else {
						sharks[i].r += 1;
					}
				} else if (sharks[i].d == 3) { // 오른
					if (sharks[i].c == C) {
						sharks[i].d = 4;
						j--;
					} else {
						sharks[i].c += 1;
					}
				} else { // 왼
					if (sharks[i].c == 1) {
						sharks[i].d = 3;
						j--;
					} else {
						sharks[i].c -= 1;
					}
				}
			}

			// 이동할 칸이 비어있는지 확인

			if (temp[sharks[i].r][sharks[i].c] == 0) { // 칸이 비어 있으면 현재 상어 저장
				temp[sharks[i].r][sharks[i].c] = sharks[i].no;
			} else {
				int idx = temp[sharks[i].r][sharks[i].c];
				if (sharks[idx].z < sharks[i].z) { // 현재 이동한 상어가 더 큼
					sharks[idx].live = false;
					temp[sharks[i].r][sharks[i].c] = sharks[i].no;
				} else { // 원래 있던 상어가 더 큼 (상어의 크기가 같은 경우는 없다.)
					sharks[i].live = false;
				}
			}

		}

		for (int i = 0; i < map.length; i++) {
			map[i] = temp[i].clone();
		}
	}

	static boolean isIn(int r, int c) {
		return 1 <= r && r <= R && 1 <= c && c <= C;
	}

	static class Shark {

		int no; // 번호
		int r; // 위치 r
		int c; // 위치 c
		int s; // 속력
		int d; // 이동 방향
		int z; // 크기
		boolean live; // 살아있는지

		public Shark() {
			super();
		}

		public Shark(int no, int r, int c, int s, int d, int z, boolean live) {
			super();
			this.no = no;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.live = live;
		}

		@Override
		public String toString() {
			return "Shark [no=" + no + ", r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + ", live=" + live
					+"]";
		}

	}
}
