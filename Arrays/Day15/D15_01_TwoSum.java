package Day15;

import java.util.HashMap;
import java.util.Map;

/*
 * ============================================================
 * Day 15 - Problem 01
 * Two Sum
 * ============================================================
 *
 * Problem Statement:
 * Given an array of integers nums and an integer target,
 * find the indices of two elements whose sum is equal to
 * the target.
 *
 * Each input has exactly one valid solution.
 *
 * The same element cannot be used twice.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * nums = [1, 6, 2, 10, 3]
 * target = 7
 *
 * Output:
 * [0, 1]
 *
 * Explanation:
 *
 * nums[0] + nums[1]
 * = 1 + 6
 * = 7
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * nums = [1, 3, 5, -7, 6, -3]
 * target = 0
 *
 * Output:
 * [1, 5]
 *
 * Explanation:
 *
 * nums[1] + nums[5]
 * = 3 + (-3)
 * = 0
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Check every possible pair of elements.
 *
 * For each element nums[i], check all elements after it.
 *
 * If:
 *
 *      nums[i] + nums[j] == target
 *
 * return their indices.
 *
 * Algorithm:
 *
 * 1. Start with the first element.
 *
 * 2. Compare it with every element after it.
 *
 * 3. Move to the next element and repeat.
 *
 * 4. If a pair sums to target, return their indices.
 *
 * 5. If no pair exists, return {-1, -1}.
 *
 * Time Complexity:
 * O(n²)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * Every possible pair may need to be checked.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH - HASHMAP
 *
 * Approach:
 * Use a HashMap to store previously visited elements.
 *
 * The map stores:
 *
 *      value -> index
 *
 * For every current element:
 *
 *      required = target - current element
 *
 * If required already exists in the map, we have found
 * the required pair.
 *
 * ============================================================
 *
 * WHY target - current?
 *
 * Suppose:
 *
 * target = 7
 * current = 6
 *
 * We need:
 *
 * required = 7 - 6
 *          = 1
 *
 * Therefore, if 1 appeared earlier in the array:
 *
 *      1 + 6 = 7
 *
 * We found the answer.
 *
 * ============================================================
 *
 * ALGORITHM:
 *
 * 1. Create a HashMap.
 *
 *      value -> index
 *
 * 2. Traverse the array.
 *
 * 3. For each element:
 *
 *      required = target - nums[i]
 *
 * 4. Check whether required exists in the map.
 *
 * 5. If it exists:
 *
 *      return {map.get(required), i}
 *
 * 6. Otherwise, store:
 *
 *      nums[i] -> i
 *
 * 7. If no pair is found, return {-1, -1}.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 *
 * nums = [1, 6, 2, 10, 3]
 * target = 7
 *
 * Initially:
 *
 * map = {}
 *
 * ------------------------------------------------------------
 *
 * i = 0
 * nums[i] = 1
 *
 * required = 7 - 1
 *          = 6
 *
 * Is 6 present?
 *
 * No.
 *
 * Store:
 *
 * 1 -> 0
 *
 * Map:
 *
 * {1=0}
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * nums[i] = 6
 *
 * required = 7 - 6
 *          = 1
 *
 * Is 1 present?
 *
 * YES.
 *
 * Map:
 *
 * 1 -> 0
 *
 * Current index:
 *
 * 1
 *
 * Therefore:
 *
 * Answer = [0, 1]
 *
 * ============================================================
 *
 * WHY CHECK BEFORE INSERTING?
 *
 * The same element cannot be used twice.
 *
 * Therefore, we first check whether the required value exists
 * among previously visited elements.
 *
 * Only after checking do we insert the current element.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * The array is traversed once.
 *
 * HashMap lookup and insertion are O(1) on average.
 *
 * ============================================================
 *
 * SPACE COMPLEXITY:
 * O(n)
 *
 * In the worst case, the HashMap stores almost every element.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - HashMap
 * - Value to index mapping
 * - Complement technique
 * - target - current
 * - One-pass array traversal
 * - O(n) time and O(n) space
 *
 * ============================================================
 */

public class D15_01_TwoSum {

    public static void main(String[] args) {

        int[] nums = {1, 6, 2, 10, 3};
        int target = 7;

        int[] result = twoSum(nums, target);

        System.out.println(
                "Indices: [" + result[0] + ", " + result[1] + "]"
        );
    }

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int required = target - nums[i];

            if (map.containsKey(required)) {

                return new int[]{
                        map.get(required),
                        i
                };
            }

            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }
}