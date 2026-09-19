package Day40;

/*
 * Day 40: Maximum Product Subarray
 *
 * Problem Statement:
 * ------------------
 * Given an integer array nums, find the subarray
 * that has the largest product and return the product.
 *
 *
 * A subarray is a contiguous non-empty sequence
 * of elements within an array.
 *
 *
 * Example 1:
 *
 * Input:
 *
 * nums = [2, 3, -2, 4]
 *
 * Output:
 *
 * 6
 *
 *
 * Explanation:
 *
 * The subarray [2, 3] has the maximum product:
 *
 * 2 * 3 = 6
 *
 *
 * Example 2:
 *
 * Input:
 *
 * nums = [-2, 0, -1]
 *
 * Output:
 *
 * 0
 *
 *
 * Explanation:
 *
 * The answer cannot be 2 because [-2, -1]
 * is NOT a subarray.
 *
 * The elements -2 and -1 are separated by 0.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT OBSERVATION
 * ------------------------------------------------------------
 *
 * This problem is different from Maximum Subarray Sum.
 *
 * In maximum sum:
 *
 *      Negative numbers usually decrease the sum.
 *
 *
 * But in maximum product:
 *
 *      Negative * Negative = Positive
 *
 *
 * Example:
 *
 *      [-2, 3, -4]
 *
 *      -2 * 3 = -6
 *
 *      -6 * -4 = 24
 *
 *
 * Therefore, a negative number can turn a
 * small negative product into a large positive product.
 *
 *
 * Because of this, we need to track:
 *
 *      1. Maximum product ending at current position
 *      2. Minimum product ending at current position
 *
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE
 * ------------------------------------------------------------
 *
 * Generate every possible subarray and calculate
 * its product.
 *
 *
 * For every starting index:
 *
 *      start = 0
 *      start = 1
 *      start = 2
 *      ...
 *
 *
 * For every starting index, extend the subarray:
 *
 *      product = product * nums[end]
 *
 *
 * Update the maximum product.
 *
 *
 * Example:
 *
 * nums = [2, 3, -2, 4]
 *
 * Subarrays include:
 *
 * [2]              -> 2
 * [2,3]            -> 6
 * [2,3,-2]         -> -12
 * [2,3,-2,4]       -> -48
 *
 * [3]              -> 3
 * [3,-2]           -> -6
 * [3,-2,4]         -> -24
 *
 * [-2]             -> -2
 * [-2,4]           -> -8
 *
 * [4]              -> 4
 *
 *
 * Maximum = 6
 *
 *
 * Time Complexity:
 *
 *      O(n^2)
 *
 *
 * Space Complexity:
 *
 *      O(1)
 *
 *
 * ------------------------------------------------------------
 * WHY BRUTE FORCE IS TOO SLOW
 * ------------------------------------------------------------
 *
 * The constraints allow:
 *
 *      n = 2 * 10^4
 *
 *
 * Therefore O(n^2) can require a very large
 * number of operations.
 *
 *
 * We need a linear-time solution.
 *
 *
 * ------------------------------------------------------------
 * OPTIMAL APPROACH: TRACK CURRENT MAX AND MIN
 * ------------------------------------------------------------
 *
 * The main idea is to maintain two values:
 *
 *
 *      currentMax
 *
 * Maximum product of a subarray ending at
 * the current element.
 *
 *
 *      currentMin
 *
 * Minimum product of a subarray ending at
 * the current element.
 *
 *
 * Why do we need currentMin?
 *
 * Because multiplying a negative number by
 * the minimum negative product can create
 * the maximum positive product.
 *
 *
 * Example:
 *
 *      currentMin = -6
 *      nums[i] = -4
 *
 *      -6 * -4 = 24
 *
 *
 * Therefore, currentMin can suddenly become
 * the new currentMax.
 *
 *
 * ------------------------------------------------------------
 * THREE POSSIBILITIES
 * ------------------------------------------------------------
 *
 * At every element nums[i], the maximum product
 * ending at this position can come from:
 *
 *
 * 1. Start a new subarray:
 *
 *      nums[i]
 *
 *
 * 2. Extend the previous maximum product:
 *
 *      currentMax * nums[i]
 *
 *
 * 3. Extend the previous minimum product:
 *
 *      currentMin * nums[i]
 *
 *
 * Therefore:
 *
 * currentMax =
 *
 *      max(
 *          nums[i],
 *          previousMax * nums[i],
 *          previousMin * nums[i]
 *      )
 *
 *
 * Similarly:
 *
 * currentMin =
 *
 *      min(
 *          nums[i],
 *          previousMax * nums[i],
 *          previousMin * nums[i]
 *      )
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT: WHY DO WE NEED temp?
 * ------------------------------------------------------------
 *
 * When calculating currentMin, we still need
 * the OLD currentMax.
 *
 *
 * But if we update currentMax first:
 *
 *      currentMax = ...
 *
 *
 * then the old value is lost.
 *
 *
 * Therefore we store:
 *
 *      temp = currentMax
 *
 *
 * Then we calculate both values using:
 *
 *      temp
 *      currentMin
 *
 *
 * ------------------------------------------------------------
 * ALGORITHM
 * ------------------------------------------------------------
 *
 * 1. Initialize:
 *
 *      currentMax = nums[0]
 *      currentMin = nums[0]
 *      answer = nums[0]
 *
 *
 * 2. Traverse the array from index 1.
 *
 *
 * 3. Store the previous currentMax:
 *
 *      temp = currentMax
 *
 *
 * 4. Calculate the new currentMax:
 *
 *      max(
 *          nums[i],
 *          temp * nums[i],
 *          currentMin * nums[i]
 *      )
 *
 *
 * 5. Calculate the new currentMin:
 *
 *      min(
 *          nums[i],
 *          temp * nums[i],
 *          currentMin * nums[i]
 *      )
 *
 *
 * 6. Update the global answer:
 *
 *      answer = max(answer, currentMax)
 *
 *
 * 7. Return answer.
 *
 *
 * ------------------------------------------------------------
 * WHY THIS APPROACH WORKS
 * ------------------------------------------------------------
 *
 * At every position, we remember both extremes:
 *
 *      maximum product
 *      minimum product
 *
 *
 * This is enough because when multiplying by
 * a positive number:
 *
 *      maximum remains maximum
 *      minimum remains minimum
 *
 *
 * But when multiplying by a negative number:
 *
 *      maximum can become minimum
 *      minimum can become maximum
 *
 *
 * Example:
 *
 *      currentMax = 6
 *      currentMin = -6
 *      nums[i] = -4
 *
 *
 * Products:
 *
 *      6 * -4  = -24
 *      -6 * -4 = 24
 *      -4      = -4
 *
 *
 * Therefore:
 *
 *      currentMax = 24
 *      currentMin = -24
 *
 *
 * This is the key idea of the problem.
 *
 *
 * ------------------------------------------------------------
 * HANDLING ZERO
 * ------------------------------------------------------------
 *
 * Zero is also important.
 *
 * Example:
 *
 *      [2, 3, 0, 4]
 *
 *
 * Before zero:
 *
 *      2 * 3 = 6
 *
 *
 * At zero:
 *
 *      product becomes 0
 *
 *
 * The algorithm considers nums[i] itself as
 * a new possible subarray.
 *
 *
 * Therefore after zero, we can start a new
 * subarray with the next element.
 *
 *
 * ------------------------------------------------------------
 * TRACE
 * ------------------------------------------------------------
 *
 * Input:
 *
 *      nums = [2, 3, -2, 4]
 *
 *
 * Initial:
 *
 *      currentMax = 2
 *      currentMin = 2
 *      answer = 2
 *
 *
 * ------------------------------------------------------------
 * i = 1
 * nums[i] = 3
 * ------------------------------------------------------------
 *
 * temp = 2
 *
 * Possible products:
 *
 *      3
 *      2 * 3 = 6
 *      2 * 3 = 6
 *
 *
 * currentMax = 6
 * currentMin = 3
 *
 * answer = 6
 *
 *
 * ------------------------------------------------------------
 * i = 2
 * nums[i] = -2
 * ------------------------------------------------------------
 *
 * temp = 6
 *
 * Possible products:
 *
 *      -2
 *      6 * -2 = -12
 *      3 * -2 = -6
 *
 *
 * currentMax = -2
 *
 * currentMin = -12
 *
 * answer = 6
 *
 *
 * Notice:
 *
 * The negative number changed which product
 * is considered maximum and minimum.
 *
 *
 * ------------------------------------------------------------
 * i = 3
 * nums[i] = 4
 * ------------------------------------------------------------
 *
 * temp = -2
 *
 * Possible products:
 *
 *      4
 *      -2 * 4 = -8
 *      -12 * 4 = -48
 *
 *
 * currentMax = 4
 *
 * currentMin = -48
 *
 * answer remains:
 *
 *      6
 *
 *
 * Final Answer:
 *
 *      6
 *
 *
 * ------------------------------------------------------------
 * TUF EXAMPLE
 * ------------------------------------------------------------
 *
 * Input:
 *
 *      nums = [1, -2, 3, 4, -4, -3]
 *
 *
 * Consider:
 *
 *      3 * 4 * -4 * -3
 *
 *
 * = 12 * -4 * -3
 *
 * = -48 * -3
 *
 * = 144
 *
 *
 * Therefore:
 *
 *      Maximum Product = 144
 *
 *
 * ------------------------------------------------------------
 * ANOTHER EXAMPLE
 * ------------------------------------------------------------
 *
 * Input:
 *
 *      nums = [-5, 0, -2]
 *
 *
 * Possible useful subarrays:
 *
 *      [-5]       -> -5
 *      [0]        -> 0
 *      [-2]       -> -2
 *
 *
 * The maximum product is:
 *
 *      0
 *
 *
 * ------------------------------------------------------------
 * TIME COMPLEXITY
 * ------------------------------------------------------------
 *
 * We traverse the array only once.
 *
 * Therefore:
 *
 *      O(n)
 *
 *
 * ------------------------------------------------------------
 * SPACE COMPLEXITY
 * ------------------------------------------------------------
 *
 * We only use a few variables:
 *
 *      currentMax
 *      currentMin
 *      answer
 *      temp
 *
 *
 * Therefore:
 *
 *      O(1)
 *
 *
 * ------------------------------------------------------------
 * KEY LEARNING
 * ------------------------------------------------------------
 *
 * Maximum Product Subarray is solved by tracking
 * both maximum and minimum products.
 *
 *
 * Remember:
 *
 *      Maximum alone is NOT enough.
 *
 *
 * Because:
 *
 *      negative * negative = positive
 *
 *
 * So maintain:
 *
 *      currentMax
 *      currentMin
 *
 *
 * At every element:
 *
 *      newMax =
 *          max(
 *              nums[i],
 *              previousMax * nums[i],
 *              previousMin * nums[i]
 *          )
 *
 *
 *      newMin =
 *          min(
 *              nums[i],
 *              previousMax * nums[i],
 *              previousMin * nums[i]
 *          )
 *
 *
 * QUICK MEMORY TRICK:
 *
 *      PRODUCT
 *         ↓
 *   Track MAX + MIN
 *         ↓
 *   Negative can swap them
 *
 *
 * FINAL:
 *
 * Brute Force:
 *
 *      O(n^2)
 *
 *
 * Optimal:
 *
 *      O(n)
 *
 *
 * Space:
 *
 *      O(1)
 */

public class D40_01_MaximumProductSubarray {

    public static void main(String[] args) {

        int[] nums = {2, 3, -2, 4};

        int result = maxProduct(nums);

        System.out.println("Maximum product: " + result);
    }


    /*
     * Optimal Approach:
     *
     * Track maximum and minimum product
     * ending at the current position.
     *
     * Time Complexity:
     *
     *      O(n)
     *
     * Space Complexity:
     *
     *      O(1)
     */
    public static int maxProduct(int[] nums) {

        /*
         * Initialize all values using the first element.
         *
         * A single element itself is a valid subarray.
         */
        int currentMax = nums[0];
        int currentMin = nums[0];
        int answer = nums[0];


        /*
         * Traverse the array from the second element.
         */
        for (int i = 1; i < nums.length; i++) {

            /*
             * Save the previous maximum.
             *
             * We need the old maximum while calculating
             * the new minimum.
             */
            int temp = currentMax;


            /*
             * Three possibilities:
             *
             * 1. Start a new subarray with nums[i]
             * 2. Extend the previous maximum product
             * 3. Extend the previous minimum product
             *
             * The third case is important when nums[i]
             * is negative.
             */
            currentMax = Math.max(
                    nums[i],
                    Math.max(
                            temp * nums[i],
                            currentMin * nums[i]
                    )
            );


            /*
             * Calculate the minimum product.
             *
             * We use temp because it contains the
             * previous currentMax.
             */
            currentMin = Math.min(
                    nums[i],
                    Math.min(
                            temp * nums[i],
                            currentMin * nums[i]
                    )
            );


            /*
             * Update the overall maximum answer.
             */
            answer = Math.max(answer, currentMax);
        }


        return answer;
    }
}


/*
 * ------------------------------------------------------------
 * QUICK QUIZ
 * ------------------------------------------------------------
 *
 * Input:
 *
 *      nums = [1, -2, 3, 4, -4, -3]
 *
 *
 * What is the maximum product?
 *
 *
 * Options:
 *
 *      12
 *      96
 *      144
 *      288
 *
 *
 * Answer:
 *
 *      144
 *
 *
 * Because:
 *
 *      3 * 4 * (-4) * (-3)
 *
 *      = 144
 *
 *
 * ------------------------------------------------------------
 * FINAL SUMMARY
 * ------------------------------------------------------------
 *
 * Problem:
 *
 *      Maximum Product Subarray
 *
 *
 * Brute Force:
 *
 *      O(n^2)
 *
 *
 * Optimal:
 *
 *      O(n)
 *
 *
 * Space:
 *
 *      O(1)
 *
 *
 * Main Concept:
 *
 *      Track both currentMax and currentMin.
 *
 *
 * Why?
 *
 *      A negative number can turn the minimum
 *      product into the maximum product.
 *
 *
 * Most Important Formula:
 *
 *      currentMax =
 *          max(
 *              nums[i],
 *              previousMax * nums[i],
 *              previousMin * nums[i]
 *          )
 *
 *
 *      currentMin =
 *          min(
 *              nums[i],
 *              previousMax * nums[i],
 *              previousMin * nums[i]
 *          )
 */