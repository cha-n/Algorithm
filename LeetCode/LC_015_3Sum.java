package LeetCode;

import java.util.*;

public class LC_015_3Sum {

	static int[] nums = { -1, 0, 1, 2, -1, -4 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> res = threeSum(nums);
		for (int i=0;i<res.size();i++) {
			System.out.println(res.get(i));
		}
	}

	
	static public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> list = new LinkedList<List<Integer>>();

		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 2; i++) {
			// 전 인덱스의 값과 같은 값이면 중복. 할 필요 없음.
			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			int left = i + 1;
			int right = nums.length - 1;
			
			while (left < right) {
				int sum = nums[i]+nums[left]+nums[right];
				
				if (sum == 0) {
					list.add(Arrays.asList(nums[i], nums[left], nums[right]));
					left++;
					right--;
					while (nums[left] == nums[left-1] && left<right) left++;
					while (nums[right] == nums[right+1] && left<right) right++;
				} else if (sum>0) {
					right--;
				}else {
					left++;
				}
			}
		}
		return list;
	}

}
