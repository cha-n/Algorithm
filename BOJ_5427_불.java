// 5427, 불
package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_5427_불 {

	static int T, W, H;
	static char[][] map;
	static int[][][] visitedAndFired;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Integer[]> fire;
	static Queue<Integer[]> sg;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new char[H][W]; // 건물 배열
			// [H][W][0]: 상근, [H][W][1]: 불
			visitedAndFired = new int[H][W][2];	
			
			fire = new LinkedList<Integer[]>(); // 불 위치
			sg = new LinkedList<Integer[]>(); // 상근 위치
			for (int i = 0; i < H; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '*')
						fire.offer(new Integer[] { i, j });
					if (map[i][j] == '@')
						sg.offer(new Integer[] { i, j });
				}
			}

			// 입력 끝
			fireBFS();
            
			int res = sgBFS();
			if (res==-1) {
				sb.append("IMPOSSIBLE\n");
			}else {
				sb.append(res+"\n");
			}
		}
		System.out.println(sb);
	}

	static void fireBFS() {
		while (!fire.isEmpty()) {
			Integer[] now = fire.poll();
			int x = now[0];
			int y = now[1];

			if (visitedAndFired[x][y][1] == 0) visitedAndFired[x][y][1] = 1;


			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (0 > nx || nx >= H || 0 > ny || ny >= W)
					continue;
				if (map[nx][ny] != '#' && visitedAndFired[nx][ny][1] == 0) {
					visitedAndFired[nx][ny][1] = visitedAndFired[x][y][1] + 1;
					fire.offer(new Integer[] { nx, ny });
				}
				
			}
			
		}
	}
	
	static int sgBFS() {
		while (!sg.isEmpty()) {
			Integer[] now = sg.poll();
			int x = now[0];
			int y = now[1];
			if (visitedAndFired[x][y][0] == 0) visitedAndFired[x][y][0] = 1;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (0 > nx || nx >= H || 0 > ny || ny >= W) {
					return visitedAndFired[x][y][0];	
				}
					
				if (map[nx][ny] == '.'&& visitedAndFired[nx][ny][0] == 0 && (visitedAndFired[x][y][0] + 1 < visitedAndFired[nx][ny][1] || visitedAndFired[nx][ny][1] == 0)) {
					visitedAndFired[nx][ny][0] = visitedAndFired[x][y][0] + 1;
					sg.offer(new Integer[] { nx, ny });

				}
			}
			
			
		}
		
		return -1;
	}
}
