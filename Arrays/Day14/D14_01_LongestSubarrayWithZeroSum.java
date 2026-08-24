package Day14;

import java.util.HashMap;
import java.util.Map;

/*
 * ============================================================
 * Day 14 - Problem 01
 * Length of the Longest Subarray with Sum Zero
 * ============================================================
 *
 * Problem Statement:
 * Given an array containing positive and negative integers,
 * find the length of the longest subarray whose sum is equal
 * to zero.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * [9, -3, 3, -1, 6, -5]
 *
 * Output:
 * 5
 *
 * Explanation:
 *
 * Zero-sum subarrays include:
 *
 * [-3, 3]
 * [-1, 6, -5]
 * [-3, 3, -1, 6, -5]
 *
 * The longest subarray has length 5.
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * [6, -2, 2, -8, 1, 7, 4, -10]
 *
 * Output:
 * 8
 *
 * Explanation:
 *
 * The complete array has sum zero.
 *
 * Therefore, the longest zero-sum subarray has length 8.
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Generate every possible subarray and calculate its sum.
 *
 * Whenever the sum becomes zero, calculate the length of
 * that subarray and update the maximum length.
 *
 * Algorithm:
 *
 * 1. Select every possible starting index.
 *
 * 2. Initialize sum = 0.
 *
 * 3. Extend the subarray towards the right.
 *
 * 4. Add every element to sum.
 *
 * 5. If sum == 0, calculate the current length.
 *
 * 6. Update the maximum length.
 *
 * Time Complexity:
 * O(n^2)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * There can be O(n^2) possible subarrays.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 * Prefix Sum + HashMap
 *
 * Approach:
 * Maintain a running prefix sum while traversing the array.
 *
 * Store each prefix sum and its FIRST occurrence index in a
 * HashMap.
 *
 * If the same prefix sum appears again, the elements between
 * the two indices have a sum of zero.
 *
 * ============================================================
 *
 * WHY DOES THE SAME PREFIX SUM MEAN ZERO SUM?
 *
 * Suppose:
 *
 * Prefix sum at index i = 10
 * Prefix sum at index j = 10
 *
 * Then:
 *
 * prefix[j] - prefix[i] = 0
 *
 * Therefore, the elements between i and j have sum zero.
 *
 * Example:
 *
 * [9, -3, 3]
 *
 * Prefix sums:
 *
 * 9
 * 6
 * 9
 *
 * The prefix sum 9 appears at index 0 and index 2.
 *
 * Therefore:
 *
 * 9 - 9 = 0
 *
 * The subarray between them:
 *
 * [-3, 3]
 *
 * has sum zero.
 *
 * ============================================================
 *
 * ALGORITHM:
 *
 * 1. Create a HashMap to store:
 *
 *      prefixSum -> first occurrence index
 *
 * 2. Store:
 *
 *      0 -> -1
 *
 *    This handles zero-sum subarrays starting from index 0.
 *
 * 3. Initialize:
 *
 *      sum = 0
 *      maxLength = 0
 *
 * 4. Traverse the array.
 *
 * 5. Add nums[i] to sum.
 *
 * 6. Check whether sum already exists in the map.
 *
 * 7. If it exists:
 *
 *      length = i - firstOccurrence
 *
 *      Update maxLength.
 *
 * 8. If it does not exist, store the current index.
 *
 * 9. Return maxLength.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 *
 * [9, -3, 3, -1, 6, -5]
 *
 * Initially:
 *
 * sum = 0
 * maxLength = 0
 *
 * Map:
 *
 * {0 = -1}
 *
 * ------------------------------------------------------------
 *
 * i = 0
 * nums[i] = 9
 *
 * sum = 9
 *
 * 9 is not present.
 *
 * Store:
 *
 * 9 -> 0
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * nums[i] = -3
 *
 * sum = 6
 *
 * 6 is not present.
 *
 * Store:
 *
 * 6 -> 1
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * nums[i] = 3
 *
 * sum = 9
 *
 * 9 already exists at index 0.
 *
 * length = 2 - 0
 *        = 2
 *
 * Subarray:
 *
 * [-3, 3]
 *
 * maxLength = 2
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * nums[i] = -1
 *
 * sum = 8
 *
 * 8 is not present.
 *
 * Store:
 *
 * 8 -> 3
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * nums[i] = 6
 *
 * sum = 14
 *
 * 14 is not present.
 *
 * Store:
 *
 * 14 -> 4
 *
 * ------------------------------------------------------------
 *
 * i = 5
 * nums[i] = -5
 *
 * sum = 9
 *
 * 9 already exists at index 0.
 *
 * length = 5 - 0
 *        = 5
 *
 * Subarray:
 *
 * [-3, 3, -1, 6, -5]
 *
 * maxLength = 5
 *
 * ============================================================
 *
 * WHY STORE ONLY THE FIRST OCCURRENCE?
 *
 * We need the LONGEST subarray.
 *
 * Suppose the same prefix sum occurs at multiple indices.
 *
 * Keeping the earliest index gives the largest possible
 * distance to the current index.
 *
 * Therefore, we should never overwrite an existing prefix sum.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * The array is traversed once.
 * HashMap operations are O(1) on average.
 *
 * ============================================================
 *
 * SPACE COMPLEXITY:
 * O(n)
 *
 * The HashMap can contain up to n different prefix sums.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Prefix sum
 * - HashMap
 * - Finding zero-sum subarrays
 * - Repeated prefix sums
 * - Storing first occurrence
 * - Finding the longest subarray
 * - O(n) time and O(n) space
 *
 * ============================================================
 */

public class D14_01_LongestSubarrayWithZeroSum {

    public static void main(String[] args) {

        int[] nums = {9, -3, 3, -1, 6, -5};

        int result = longestZeroSumSubarray(nums);

        System.out.println(
                "Longest zero-sum subarray length: " + result
        );
    }

    public static int longestZeroSumSubarray(int[] nums) {

        Map<Long, Integer> map = new HashMap<>();

        long sum = 0;
        int maxLength = 0;

        // Handles subarrays starting from index 0
        map.put(0L, -1);

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            if (map.containsKey(sum)) {

                int length = i - map.get(sum);

                maxLength = Math.max(maxLength, length);

            } else {

                // Store only the first occurrence
                map.put(sum, i);
            }
        }

        return maxLength;
    }
}