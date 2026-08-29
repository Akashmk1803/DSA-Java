package Day19;

/*
 * ============================================================
 * Day 19 - Problem 01
 * Maximum Subarray Sum - Extended Kadane's Algorithm
 * ============================================================
 *
 * Problem Statement:
 * Given an integer array nums, find the contiguous subarray
 * with the largest sum.
 *
 * Return:
 * 1. The maximum sum.
 * 2. The starting index of the subarray.
 * 3. The ending index of the subarray.
 *
 * ============================================================
 *
 * Example:
 *
 * Input:
 * nums = [-1, 2, 3, -1, 2, -6, 5]
 *
 * Output:
 * Maximum Sum = 6
 * Start Index = 1
 * End Index = 4
 *
 * Explanation:
 *
 * The maximum subarray is:
 *
 * [2, 3, -1, 2]
 *
 * Sum:
 *
 * 2 + 3 - 1 + 2 = 6
 *
 * ============================================================
 *
 * APPROACH:
 * Extended Kadane's Algorithm
 *
 * Day 18 only calculated the maximum sum.
 *
 * In this problem, we also need to identify the actual
 * subarray.
 *
 * Therefore, we keep track of:
 *
 * currentStart -> possible starting index of current subarray
 * start        -> starting index of maximum subarray
 * end          -> ending index of maximum subarray
 *
 * ============================================================
 *
 * KEY IDEA:
 *
 * At every element we decide:
 *
 * 1. Start a new subarray from the current element.
 *
 * OR
 *
 * 2. Continue the previous subarray.
 *
 * If nums[i] is better than:
 *
 * currentSum + nums[i]
 *
 * we start a new subarray.
 *
 * ============================================================
 *
 * ALGORITHM:
 *
 * 1. Initialize:
 *
 *      currentSum = nums[0]
 *      maxSum = nums[0]
 *
 *      currentStart = 0
 *      start = 0
 *      end = 0
 *
 * 2. Traverse the array from index 1.
 *
 * 3. If currentSum + nums[i] < nums[i]:
 *
 *      Start a new subarray.
 *
 *      currentSum = nums[i]
 *      currentStart = i
 *
 *    Otherwise:
 *
 *      Continue the current subarray.
 *
 *      currentSum += nums[i]
 *
 * 4. If currentSum > maxSum:
 *
 *      maxSum = currentSum
 *      start = currentStart
 *      end = i
 *
 * 5. Return the maximum sum and indices.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 *
 * [-1, 2, 3, -1, 2, -6, 5]
 *
 * Initially:
 *
 * currentSum = -1
 * maxSum = -1
 * currentStart = 0
 * start = 0
 * end = 0
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * nums[i] = 2
 *
 * Compare:
 *
 * currentSum + nums[i]
 * = -1 + 2
 * = 1
 *
 * nums[i] = 2
 *
 * 2 > 1
 *
 * Start a new subarray.
 *
 * currentSum = 2
 * currentStart = 1
 *
 * Since:
 *
 * 2 > -1
 *
 * maxSum = 2
 * start = 1
 * end = 1
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * nums[i] = 3
 *
 * Continue:
 *
 * currentSum = 2 + 3
 *            = 5
 *
 * maxSum = 5
 * start = 1
 * end = 2
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * nums[i] = -1
 *
 * Continue:
 *
 * currentSum = 5 - 1
 *            = 4
 *
 * maxSum remains 5.
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * nums[i] = 2
 *
 * Continue:
 *
 * currentSum = 4 + 2
 *            = 6
 *
 * 6 > 5
 *
 * maxSum = 6
 * start = 1
 * end = 4
 *
 * ------------------------------------------------------------
 *
 * i = 5
 * nums[i] = -6
 *
 * Continue:
 *
 * currentSum = 6 - 6
 *            = 0
 *
 * maxSum remains 6.
 *
 * ------------------------------------------------------------
 *
 * i = 6
 * nums[i] = 5
 *
 * Compare:
 *
 * currentSum + nums[i]
 * = 0 + 5
 * = 5
 *
 * nums[i] = 5
 *
 * Continuing gives the same value.
 *
 * currentSum = 5
 *
 * maxSum remains 6.
 *
 * ============================================================
 *
 * FINAL RESULT:
 *
 * Maximum Sum = 6
 *
 * Start Index = 1
 *
 * End Index = 4
 *
 * Subarray:
 *
 * [2, 3, -1, 2]
 *
 * ============================================================
 *
 * ALL NEGATIVE NUMBERS:
 *
 * Example:
 *
 * [-5, -2, -8]
 *
 * The correct answer is:
 *
 * Maximum Sum = -2
 *
 * Start Index = 1
 *
 * End Index = 1
 *
 * Therefore, we must NOT initialize maxSum to 0.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * The array is traversed only once.
 *
 * ============================================================
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * Only a few variables are used.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Kadane's Algorithm
 * - Finding the actual maximum subarray
 * - Tracking start and end indices
 * - currentStart
 * - Handling negative numbers
 * - O(n) time
 * - O(1) space
 *
 * ============================================================
 */

public class D19_01_MaximumSubarrayWithIndices {

    public static void main(String[] args) {

        int[] nums = {-1, 2, 3, -1, 2, -6, 5};

        findMaximumSubarray(nums);
    }

    public static void findMaximumSubarray(int[] nums) {

        int currentSum = nums[0];
        int maxSum = nums[0];

        int currentStart = 0;

        int start = 0;
        int end = 0;

        for (int i = 1; i < nums.length; i++) {

            if (currentSum + nums[i] < nums[i]) {

                currentSum = nums[i];
                currentStart = i;

            } else {

                currentSum += nums[i];
            }

            if (currentSum > maxSum) {

                maxSum = currentSum;
                start = currentStart;
                end = i;
            }
        }

        System.out.println("Maximum Sum: " + maxSum);
        System.out.println("Start Index: " + start);
        System.out.println("End Index: " + end);

        System.out.print("Maximum Subarray: [");

        for (int i = start; i <= end; i++) {

            System.out.print(nums[i]);

            if (i < end) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}