package swea;

import java.io.*;
import java.util.*;

public class Solution_1868_파핑파핑지뢰찾기 {

	static int T, N;
	static char[][] board;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static Queue<int[]> q = new LinkedList<int[]>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			// 입력
			N = Integer.parseInt(br.readLine());

			board = new char[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					board[i][j] = s.charAt(j);
				}
			}

			// 입력 확인
			/*
			 * for (char[] row : board) { System.out.println(row); }
			 */
			// 변화 확인
			countMine();

			int clickCnt = 0;
			// 최소한의 클릭 수 --> 연쇄 반응이 일어날 수 있는 칸을 먼저 처리해야 한다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '0' && !visited[i][j]) {
						bfs(new int[] { i, j });

						clickCnt++;

					}
				}
			}

			// System.out.println("0인 부분 다 처리 --> 남아있는 . 처리");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] != '*' && !visited[i][j]) {
						clickCnt++;
					}
				}
			}
			sb.append("#").append(t + 1).append(" ").append(clickCnt).append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(int[] coor) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(coor);
		visited[coor[0]][coor[1]] = true;
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}
					if (board[nx][ny] == '0' && !visited[nx][ny]) {
						q.add(new int[] { nx, ny });
					}
					visited[nx][ny] = true;
				}
			}

		}

	}

	// 지뢰 계산
	static void countMine() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == '.') {
					int mine = 0;
					for (int k = 0; k < 8; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
							continue;
						}
						// 지뢰 아니면 --> =='.'으로 하면 이미 숫자로 바뀐 칸이 카운트 안 됨
						if (board[nx][ny] == '*') {
							mine++;
						}
					}
					board[i][j] = (char) (mine + '0');
				}
			}
		}
	}

}
