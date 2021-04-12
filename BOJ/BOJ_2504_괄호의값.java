package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2504_괄호의값 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		Stack<String> stack = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			String temp = input.substring(i, i + 1);
			if (temp.equals("(") || temp.equals("[")) {
				stack.push(temp);
			} else { 	// ), ]
				if (temp.equals(")")) {	// )
					int tempNum = 0;
					while (!stack.isEmpty()) {
						if (stack.peek().equals("(")) {
							stack.pop();
							if (tempNum == 0) {
								stack.push("2");
							} else {
								stack.push(2 * tempNum + "");
							}
							break;
						} else if (stack.peek().equals("[")) {
							System.out.println(0);
							System.exit(0);
						} else {
							tempNum += Integer.parseInt(stack.pop());
						}
					}
				} else { // ]
					int tempNum = 0;
					while (!stack.isEmpty()) {
						if (stack.peek().equals("[")) {
							stack.pop();
							if (tempNum == 0) {
								stack.push("3");
							} else {
								stack.push(3 * tempNum + "");
							}
							break;
						} else if (stack.peek().equals("(")) {
							System.out.println(0);
							System.exit(0);
						} else {
							tempNum += Integer.parseInt(stack.pop());
						}
					}
				}
			}
		}

		int result = 0;
		while (!stack.isEmpty()) {
			if (stack.peek().equals("(") || stack.peek().equals("[")) {
				System.out.println(0);
				System.exit(0);
			} else {
				result += Integer.parseInt(stack.pop());
			}
		}
		System.out.println(result);

	}

}
