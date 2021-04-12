package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17612_쇼핑몰 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Queue<Customer> customers = new LinkedList<Customer>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			customers.offer(new Customer(id, w));
		}
		// 입력 끝
		
		int minutes = 0;
		int pollCnt = 0;
		long result = 0;
		int r = 1; // 빠져나가는 순서

		PriorityQueue<Integer> leftcheckouts = new PriorityQueue<>();
		PriorityQueue<Customer> pq = new PriorityQueue<>(); // 계산대
		
		while (!customers.isEmpty()) {
			
			while (pollCnt < k && !customers.isEmpty()) {
				Customer temp = customers.poll();
				temp.checkout = pollCnt++;
				temp.finishtime = minutes + temp.items;
				pq.offer(temp);
			}

			if (pollCnt >= k) {
				Customer out = pq.poll();
				minutes = out.finishtime;
				result += 1l * out.id * r++;
				//System.out.println("*****out: " + out + " finishTime" + out.finishtime);
				leftcheckouts.offer(out.checkout);

				while (!customers.isEmpty() && !pq.isEmpty() && pq.peek().finishtime == out.finishtime) { // 계산이 동시에 끝나는 곳이 있다면 모두 뺀다.
					out = pq.poll();
					//System.out.println("*****-->out: " + out);
					result += 1l * out.id * (r++);
					leftcheckouts.offer(out.checkout);	// 계산대 번호 offer
				}

				while (!leftcheckouts.isEmpty() && !customers.isEmpty()) {	// 비운 계산대 채운다.
					Customer temp = customers.poll();
					temp.finishtime = minutes + temp.items;
					temp.checkout = leftcheckouts.poll();
					pq.offer(temp);
				}

			}
		}

		//System.out.println("==============================================================================");
		while (!pq.isEmpty()) {		// 남아있는 계산대 다 뺀다.
			Customer out = pq.poll();
			//System.out.println("*****out: " + out);
			result += 1l * out.id * r++;
		}

		System.out.println(result);

	}

	static class Customer implements Comparable<Customer> {
		int id;
		int items;
		int finishtime;
		int checkout;

		Customer(int id, int items) {
			this.id = id;
			this.items = items;
		}

		@Override
		public int compareTo(Customer o) {	// 끝나는 시간 오름차순, 끝나는 시간 같다면 계산대 번호 내림차순 
			if (this.finishtime == o.finishtime) {
				return Integer.compare(o.checkout, this.checkout);
			}
			return Integer.compare(this.finishtime, o.finishtime);
		}

		@Override
		public String toString() {
			return "Customer [id=" + id + ", items=" + items + ", finishtime=" + finishtime + ", checkout=" + checkout
					+ "]";
		}

	}
}
