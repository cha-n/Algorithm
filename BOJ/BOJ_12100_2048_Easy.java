package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_12100_2048_Easy {

	static int N;
	static int[][] map;
	static Deque<Integer> dq;
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// dfs로 이동 시킬거..
		// cnt가 5가 되면 제일 큰 블록 찾는다.
		// 그 전까지는 for문 돌리면서 4방으로 모두 이동 시킴
		dq = new ArrayDeque<Integer>();
		dfs(0, map);
		System.out.println(max);
	}
	
	static void dfs(int cnt, int[][] arr) {
//		System.out.println("cnt: "+cnt);
		if (cnt==5) {
			int temp = 0;
			for (int i=0;i<N;i++) {
				for (int j=0;j<N;j++) {
					temp = Math.max(temp, arr[i][j]);
				}
			}
			max = Math.max(temp, max);
			return;
		}
		
		for (int i=0;i<4;i++) {
			dfs(cnt+1, move(i, arr));
		}
	}
	
	
	static int[][] move(int x, int[][] arr) {
		if (x==0) return moveUp(arr);
		else if (x==1) return moveDown(arr);
		else if (x==2) return moveLeft(arr);
		else return moveRight(arr);
	}

	static int[][] moveUp(int[][] arr) {
		int[][] temp = new int[N][N];
		for (int j=0;j<N;j++) {
			dq.clear();
			for (int i=0;i<N;i++) {
				if (arr[i][j]==0) continue;
				if (!dq.isEmpty() && dq.getLast().intValue()==arr[i][j]) {
					dq.pollLast();
					dq.offer(arr[i][j]*(-2));	// 연속해서 합쳐지면 안 됨
				}else {
					dq.offer(arr[i][j]);
				}
			}
			int idx = 0;
			
			while (!dq.isEmpty()) {
				temp[idx++][j] = Math.abs(dq.pollFirst());
			}
			for (int i=idx;i<N;i++) {
				temp[i][j] = 0;
			}
			
		}
		
//		print();
		return temp;
	}
	
	static int[][] moveDown(int[][] arr) {
		int[][] temp = new int[N][N];
		for (int j=0;j<N;j++) {
			dq.clear();
			for (int i=N-1;i>=0;i--) {
				if (arr[i][j]==0) continue;
				if (!dq.isEmpty() && dq.getLast().intValue()==arr[i][j]) {
					dq.pollLast();
					dq.offer(arr[i][j]*(-2));	// 연속해서 합쳐지면 안 됨
				}else {
					dq.offer(arr[i][j]);
				}
			}
			
			int idx = N-1;
			while (!dq.isEmpty()) {
				temp[idx--][j] = Math.abs(dq.pollFirst());
			}
			for (int i=idx;i>=0;i--) {
				temp[i][j] = 0;
			}
			
		}
//		print();
		return temp;
	}
	
	
	static int[][] moveLeft(int[][] arr) {
		int[][] temp = new int[N][N];
		for (int i=0;i<N;i++) {
			dq.clear();
			for (int j=0;j<N;j++) {
				if (arr[i][j]==0) continue;
				if (!dq.isEmpty() && dq.getLast().intValue()==arr[i][j]) {
					dq.pollLast();
					dq.offer(arr[i][j]*(-2));	// 연속해서 합쳐지면 안 됨
				}else {
					dq.offer(arr[i][j]);
				}
			}
			int idx = 0;
			while (!dq.isEmpty()) {
				temp[i][idx++] = Math.abs(dq.pollFirst());
			}
			for (int j=idx;j<N;j++) {
				temp[i][j] = 0;
			}
			
		}
//		print();
		return temp;
	}
	
	static int[][] moveRight(int[][] arr) {
		int[][] temp = new int[N][N];
		for (int i=0;i<N;i++) {
			dq.clear();
			for (int j=N-1;j>=0;j--) {
				if (arr[i][j]==0) continue;
				if (!dq.isEmpty() && dq.getLast().intValue()==arr[i][j]) {
					dq.pollLast();
					dq.offer(arr[i][j]*(-2));	// 연속해서 합쳐지면 안 됨
				}else {
					dq.offer(arr[i][j]);
				}
			}
			int idx = N-1;
			while (!dq.isEmpty()) {
				temp[i][idx--] = Math.abs(dq.pollFirst());
			}
			for (int j=idx;j>=0;j--) {
				temp[i][j] = 0;
			}
			
		}
//		print();
		return temp;
	}
	
//	static void print() {
//		for (int[] row : map) {
//			System.out.println(Arrays.toString(row));
//		}
//	}

}
