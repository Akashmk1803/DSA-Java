package Day18;

/*
 * ============================================================
 * Day 18 - Problem 01
 * Maximum Subarray Sum - Kadane's Algorithm
 * ============================================================
 *
 * Problem Statement:
 * Given an integer array nums, find the contiguous subarray
 * with the largest sum and return the sum of its elements.
 *
 * A subarray must be a contiguous and non-empty sequence of
 * elements.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * [2, 3, 5, -2, 7, -4]
 *
 * Output:
 * 15
 *
 * Explanation:
 *
 * The subarray:
 *
 * [2, 3, 5, -2, 7]
 *
 * has the maximum sum:
 *
 * 2 + 3 + 5 - 2 + 7 = 15
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * [-2, -3, -7, -2, -10, -4]
 *
 * Output:
 * -2
 *
 * Explanation:
 *
 * All elements are negative.
 *
 * Therefore, the largest possible sum is the least negative
 * element:
 *
 * -2
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Generate every possible subarray and calculate its sum.
 *
 * For each starting index, maintain a running sum while
 * extending the subarray.
 *
 * Algorithm:
 *
 * 1. Start from every possible index i.
 *
 * 2. Initialize sum = 0.
 *
 * 3. Traverse from i to the end of the array.
 *
 * 4. Add nums[j] to sum.
 *
 * 5. Update the maximum sum.
 *
 * Time Complexity:
 * O(n²)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * There are O(n²) possible subarrays.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 * Kadane's Algorithm
 *
 * Approach:
 * Maintain the maximum sum of a subarray ending at the
 * current position.
 *
 * At every element, we have two choices:
 *
 * 1. Start a new subarray from the current element.
 *
 * 2. Extend the previous subarray by adding the current
 *    element.
 *
 * Therefore:
 *
 * currentSum =
 * max(nums[i], currentSum + nums[i])
 *
 * After calculating currentSum, update maxSum.
 *
 * ============================================================
 *
 * KEY IDEA:
 *
 * If the previous subarray has a negative contribution,
 * continuing it may be worse than starting a new subarray.
 *
 * Therefore:
 *
 * If currentSum + nums[i] < nums[i]
 *
 * start a new subarray from nums[i].
 *
 * ============================================================
 *
 * ALGORITHM:
 *
 * 1. Initialize currentSum with the first element.
 *
 * 2. Initialize maxSum with the first element.
 *
 * 3. Traverse the remaining elements.
 *
 * 4. For every element:
 *
 *      currentSum =
 *      max(nums[i], currentSum + nums[i])
 *
 * 5. Update:
 *
 *      maxSum = max(maxSum, currentSum)
 *
 * 6. Return maxSum.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 *
 * [2, 3, 5, -2, 7, -4]
 *
 * Initially:
 *
 * currentSum = 2
 * maxSum = 2
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * nums[i] = 3
 *
 * currentSum = max(3, 2 + 3)
 *            = 5
 *
 * maxSum = max(2, 5)
 *        = 5
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * nums[i] = 5
 *
 * currentSum = max(5, 5 + 5)
 *            = 10
 *
 * maxSum = 10
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * nums[i] = -2
 *
 * currentSum = max(-2, 10 - 2)
 *            = 8
 *
 * maxSum = 10
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * nums[i] = 7
 *
 * currentSum = max(7, 8 + 7)
 *            = 15
 *
 * maxSum = 15
 *
 * ------------------------------------------------------------
 *
 * i = 5
 * nums[i] = -4
 *
 * currentSum = max(-4, 15 - 4)
 *            = 11
 *
 * maxSum = 15
 *
 * ============================================================
 *
 * FINAL ANSWER:
 *
 * 15
 *
 * ============================================================
 *
 * ALL NEGATIVE CASE:
 *
 * Input:
 *
 * [-2, -3, -7, -2, -10, -4]
 *
 * Initialize:
 *
 * currentSum = -2
 * maxSum = -2
 *
 * Every new element is compared with the option of starting
 * a new subarray.
 *
 * Therefore, the maximum remains:
 *
 * -2
 *
 * ============================================================
 *
 * IMPORTANT:
 *
 * Do NOT initialize maxSum to 0.
 *
 * Why?
 *
 * The subarray must be non-empty.
 *
 * For:
 *
 * [-5, -2, -8]
 *
 * The correct answer is:
 *
 * -2
 *
 * Initializing maxSum to 0 would incorrectly return 0.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * The array is traversed exactly once.
 *
 * ============================================================
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * Only two variables are used.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Kadane's Algorithm
 * - Maximum subarray
 * - Running sum
 * - Deciding when to start a new subarray
 * - Handling negative numbers
 * - O(n) time
 * - O(1) space
 *
 * ============================================================
 */

public class D18_01_MaximumSubarraySum {

    public static void main(String[] args) {

        int[] nums = {2, 3, 5, -2, 7, -4};

        int maxSum = findMaximumSubarraySum(nums);

        System.out.println(
                "Maximum subarray sum: " + maxSum
        );
    }

    public static int findMaximumSubarraySum(int[] nums) {

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            currentSum = Math.max(
                    nums[i],
                    currentSum + nums[i]
            );

            maxSum = Math.max(
                    maxSum,
                    currentSum
            );
        }

        return maxSum;
    }
}