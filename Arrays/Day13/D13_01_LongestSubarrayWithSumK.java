package Day13;

import java.util.HashMap;
import java.util.Map;

/*
 * ============================================================
 * Day 13 - Problem 01
 * Longest Subarray with Sum K
 * ============================================================
 *
 * Problem Statement:
 * Given an integer array nums and an integer k, find the
 * length of the longest subarray whose sum is equal to k.
 *
 * If no such subarray exists, return 0.
 *
 * A subarray must contain consecutive elements.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * nums = [10, 5, 2, 7, 1, 9]
 * k = 15
 *
 * Output:
 * 4
 *
 * Explanation:
 *
 * The longest subarray with sum 15 is:
 *
 * [5, 2, 7, 1]
 *
 * Sum:
 * 5 + 2 + 7 + 1 = 15
 *
 * Length:
 * 4
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * nums = [-3, 2, 1]
 * k = 6
 *
 * Output:
 * 0
 *
 * Explanation:
 * No subarray has a sum equal to 6.
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Generate every possible subarray and calculate its sum.
 *
 * For every starting index, extend the subarray to the right
 * and keep calculating the sum.
 *
 * If the sum becomes equal to k, update the maximum length.
 *
 * Algorithm:
 *
 * 1. Start from every possible index i.
 * 2. Initialize the sum as 0.
 * 3. Traverse from i to the end of the array.
 * 4. Add each element to sum.
 * 5. If sum == k, calculate the current subarray length.
 * 6. Update the maximum length.
 *
 * Time Complexity:
 * O(n²)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * We may examine O(n²) subarrays, which is inefficient for
 * large arrays.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 * Prefix Sum + HashMap
 *
 * Approach:
 * Maintain a running prefix sum while traversing the array.
 *
 * Store each prefix sum with its FIRST occurrence index in a
 * HashMap.
 *
 * At every index:
 *
 *     currentSum = currentSum + nums[i]
 *
 * To find a subarray whose sum is k:
 *
 *     previousSum = currentSum - k
 *
 * If previousSum exists in the HashMap, then the elements
 * between that previous index and the current index have a
 * sum equal to k.
 *
 * ============================================================
 *
 * WHY DOES currentSum - k WORK?
 *
 * Suppose:
 *
 * previous prefix sum = 10
 * current prefix sum  = 25
 *
 * Then the subarray between them has:
 *
 * 25 - 10 = 15
 *
 * Therefore, if:
 *
 * currentSum - previousSum = k
 *
 * then:
 *
 * previousSum = currentSum - k
 *
 * ============================================================
 *
 * Algorithm:
 *
 * 1. Create a HashMap to store prefix sums and their first
 *    occurrence indices.
 *
 * 2. Store:
 *
 *      0 -> -1
 *
 *    This handles subarrays that start from index 0.
 *
 * 3. Initialize:
 *
 *      sum = 0
 *      maxLength = 0
 *
 * 4. Traverse the array.
 *
 * 5. Add the current element to sum.
 *
 * 6. Calculate:
 *
 *      requiredSum = sum - k
 *
 * 7. If requiredSum exists in the map:
 *
 *      length = i - map.get(requiredSum)
 *
 *    Update maxLength.
 *
 * 8. If the current prefix sum does not already exist in
 *    the map, store its index.
 *
 * 9. Return maxLength.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 * nums = [10, 5, 2, 7, 1, 9]
 * k = 15
 *
 * Initially:
 *
 * sum = 0
 * maxLength = 0
 *
 * Map:
 * {0 = -1}
 *
 * ------------------------------------------------------------
 *
 * i = 0
 * nums[i] = 10
 *
 * sum = 10
 *
 * requiredSum = 10 - 15
 *             = -5
 *
 * -5 is not in the map.
 *
 * Store:
 * 10 -> 0
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * nums[i] = 5
 *
 * sum = 15
 *
 * requiredSum = 15 - 15
 *             = 0
 *
 * 0 exists at index -1.
 *
 * length = 1 - (-1)
 *        = 2
 *
 * Subarray:
 * [10, 5]
 *
 * maxLength = 2
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * nums[i] = 2
 *
 * sum = 17
 *
 * requiredSum = 17 - 15
 *             = 2
 *
 * 2 is not in the map.
 *
 * Store:
 * 17 -> 2
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * nums[i] = 7
 *
 * sum = 24
 *
 * requiredSum = 24 - 15
 *             = 9
 *
 * 9 is not in the map.
 *
 * Store:
 * 24 -> 3
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * nums[i] = 1
 *
 * sum = 25
 *
 * requiredSum = 25 - 15
 *             = 10
 *
 * 10 exists at index 0.
 *
 * length = 4 - 0
 *        = 4
 *
 * Subarray:
 * [5, 2, 7, 1]
 *
 * maxLength = 4
 *
 * ------------------------------------------------------------
 *
 * i = 5
 * nums[i] = 9
 *
 * sum = 34
 *
 * requiredSum = 34 - 15
 *             = 19
 *
 * 19 is not in the map.
 *
 * ------------------------------------------------------------
 *
 * Final Answer:
 *
 * maxLength = 4
 *
 * ============================================================
 *
 * WHY STORE ONLY THE FIRST OCCURRENCE?
 *
 * We want the longest possible subarray.
 *
 * Suppose the same prefix sum occurs at multiple indices.
 *
 * Keeping the earliest index gives the largest possible
 * subarray length.
 *
 * Therefore:
 *
 * if (!map.containsKey(sum)) {
 *     map.put(sum, i);
 * }
 *
 * We never overwrite an existing prefix sum.
 *
 * ============================================================
 *
 * IMPORTANT:
 *
 * Sliding Window is not generally suitable here because the
 * array can contain negative numbers.
 *
 * With negative numbers, the running sum does not always
 * increase when the window expands.
 *
 * Therefore, Prefix Sum + HashMap works for positive numbers,
 * zero and negative numbers.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * Each element is processed once and HashMap operations are
 * O(1) on average.
 *
 * SPACE COMPLEXITY:
 * O(n)
 *
 * The HashMap can store up to n different prefix sums.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Prefix sum
 * - HashMap
 * - Finding subarrays with a target sum
 * - Storing first occurrence
 * - Longest subarray
 * - Handling negative numbers
 * - Why sliding window does not work with arbitrary negatives
 *
 * ============================================================
 */

public class D13_01_LongestSubarrayWithSumK {

    public static void main(String[] args) {

        int[] nums = {10, 5, 2, 7, 1, 9};
        int k = 15;

        int result = longestSubarrayWithSumK(nums, k);

        System.out.println(
                "Longest subarray length: " + result
        );
    }

    public static int longestSubarrayWithSumK(int[] nums, int k) {

        Map<Long, Integer> map = new HashMap<>();

        long sum = 0;
        int maxLength = 0;

        // Handles subarrays that start from index 0
        map.put(0L, -1);

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            long requiredSum = sum - k;

            if (map.containsKey(requiredSum)) {

                int length = i - map.get(requiredSum);

                maxLength = Math.max(maxLength, length);
            }

            // Store only the first occurrence
            if (!map.containsKey(sum)) {

                map.put(sum, i);
            }
        }

        return maxLength;
    }
}