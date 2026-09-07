package Day28;

import java.util.HashMap;
import java.util.Map;

public class D28_01_SubarraySumEqualsK {

    /*
     * ============================================================
     * PROBLEM: COUNT SUBARRAY SUM EQUALS K
     * ============================================================
     *
     * Given an array of integers and an integer k, return the total
     * number of non-empty subarrays whose sum equals k.
     *
     * A subarray is a contiguous sequence of elements.
     *
     *
     * Example 1:
     *
     * Input:
     *
     * nums = [1, 1, 1]
     * k = 2
     *
     * Valid subarrays:
     *
     * [1, 1]
     * [1, 1]
     *
     * Output:
     *
     * 2
     *
     *
     * Example 2:
     *
     * Input:
     *
     * nums = [1, 2, 3]
     * k = 3
     *
     * Valid subarrays:
     *
     * [1, 2]
     * [3]
     *
     * Output:
     *
     * 2
     *
     *
     * Example 3:
     *
     * Input:
     *
     * nums = [3, 1, 2, 4]
     * k = 6
     *
     * Valid subarrays:
     *
     * [3, 1, 2]
     * [2, 4]
     *
     * Output:
     *
     * 2
     *
     *
     * ============================================================
     * BRUTE FORCE APPROACH
     * ============================================================
     *
     * Generate every possible subarray.
     *
     * For every starting index i, extend the ending index j and
     * keep calculating the running sum.
     *
     *
     * Example:
     *
     * nums = [1, 2, 3]
     *
     * Start at index 0:
     *
     * [1]       → sum = 1
     * [1, 2]    → sum = 3
     * [1, 2, 3] → sum = 6
     *
     * Start at index 1:
     *
     * [2]       → sum = 2
     * [2, 3]    → sum = 5
     *
     * Start at index 2:
     *
     * [3]       → sum = 3
     *
     *
     * Time Complexity:
     * O(n²)
     *
     * Space Complexity:
     * O(1)
     *
     *
     * ============================================================
     * BETTER APPROACH
     * ============================================================
     *
     * If all numbers are positive, a sliding window can sometimes
     * be used.
     *
     * However, this problem allows negative numbers.
     *
     * Example:
     *
     * [1, -1, 1]
     *
     * Because negative numbers can increase or decrease the
     * running sum unpredictably, a normal sliding window cannot
     * reliably solve the general problem.
     *
     * Therefore we use prefix sums.
     *
     *
     * ============================================================
     * OPTIMAL APPROACH:
     * PREFIX SUM + HASHMAP
     * ============================================================
     *
     * The key equation is:
     *
     * prefixSum - previousPrefixSum = k
     *
     * Therefore:
     *
     * previousPrefixSum = prefixSum - k
     *
     *
     * So while traversing the array:
     *
     * 1. Add the current number to prefixSum.
     *
     * 2. Calculate:
     *
     *    needed = prefixSum - k
     *
     * 3. Check how many times 'needed' has appeared before.
     *
     * 4. Add that frequency to the answer.
     *
     * 5. Store the current prefixSum in the HashMap.
     *
     *
     * ============================================================
     * WHY DO WE STORE FREQUENCY?
     * ============================================================
     *
     * This is VERY important.
     *
     * We don't just store whether a prefix sum exists.
     *
     * We store HOW MANY TIMES it exists.
     *
     * Example:
     *
     * nums = [1, -1, 0]
     * k = 0
     *
     * Prefix sums:
     *
     * 0
     * 1
     * 0
     * 0
     *
     * Prefix sum 0 occurs multiple times.
     *
     * Each previous occurrence can form a different subarray.
     *
     * Therefore we need:
     *
     * HashMap<prefixSum, frequency>
     *
     *
     * ============================================================
     * INITIALIZATION
     * ============================================================
     *
     * We start with:
     *
     * map.put(0, 1);
     *
     * Why?
     *
     * This represents an empty prefix before the array starts.
     *
     *
     * Example:
     *
     * nums = [3]
     * k = 3
     *
     * Current prefixSum = 3
     *
     * needed = 3 - 3
     *        = 0
     *
     * If 0 was already stored once, we know that:
     *
     * [3]
     *
     * itself has sum 3.
     *
     * Therefore:
     *
     * map.put(0, 1)
     *
     * is essential.
     *
     *
     * ============================================================
     * TRACE
     * ============================================================
     *
     * nums = [3, 1, 2, 4]
     * k = 6
     *
     *
     * Initial:
     *
     * prefixSum = 0
     * count = 0
     *
     * Map:
     *
     * {0=1}
     *
     *
     * ------------------------------------------------------------
     * i = 0
     * nums[0] = 3
     * ------------------------------------------------------------
     *
     * prefixSum = 3
     *
     * needed = 3 - 6
     *        = -3
     *
     * -3 not found.
     *
     * Store:
     *
     * {0=1, 3=1}
     *
     *
     * ------------------------------------------------------------
     * i = 1
     * nums[1] = 1
     * ------------------------------------------------------------
     *
     * prefixSum = 4
     *
     * needed = 4 - 6
     *        = -2
     *
     * -2 not found.
     *
     * Store:
     *
     * {0=1, 3=1, 4=1}
     *
     *
     * ------------------------------------------------------------
     * i = 2
     * nums[2] = 2
     * ------------------------------------------------------------
     *
     * prefixSum = 6
     *
     * needed = 6 - 6
     *        = 0
     *
     * 0 exists once.
     *
     * count = 1
     *
     * This represents:
     *
     * [3, 1, 2]
     *
     * Store:
     *
     * {0=1, 3=1, 4=1, 6=1}
     *
     *
     * ------------------------------------------------------------
     * i = 3
     * nums[3] = 4
     * ------------------------------------------------------------
     *
     * prefixSum = 10
     *
     * needed = 10 - 6
     *        = 4
     *
     * 4 exists once.
     *
     * count = 2
     *
     * This represents:
     *
     * [2, 4]
     *
     *
     * FINAL ANSWER:
     *
     * 2
     *
     *
     * ============================================================
     * TIME COMPLEXITY
     * ============================================================
     *
     * We traverse the array once.
     *
     * HashMap insertion and lookup take O(1) average time.
     *
     * Therefore:
     *
     * O(n) average time
     *
     *
     * ============================================================
     * SPACE COMPLEXITY
     * ============================================================
     *
     * The HashMap can contain up to n different prefix sums.
     *
     * Therefore:
     *
     * O(n)
     *
     *
     * ============================================================
     * WHY IS THIS OPTIMAL?
     * ============================================================
     *
     * The brute-force solution examines O(n²) subarrays.
     *
     * We can avoid checking every subarray individually by using
     * the prefix-sum relationship:
     *
     * currentPrefix - previousPrefix = k
     *
     * A HashMap lets us find the required previous prefix sum
     * in O(1) average time.
     *
     * Therefore the complete solution becomes:
     *
     * O(n)
     *
     *
     * ============================================================
     * KEY LEARNING
     * ============================================================
     *
     * Remember this formula:
     *
     * currentPrefix - previousPrefix = k
     *
     * Therefore:
     *
     * previousPrefix = currentPrefix - k
     *
     *
     * Whenever you see:
     *
     * "Count the number of subarrays with sum K"
     *
     * Think:
     *
     * PREFIX SUM + HASHMAP FREQUENCY
     *
     *
     * Also remember:
     *
     * map.put(0, 1);
     *
     * This handles subarrays that start from index 0.
     * ============================================================
     */

    public static void main(String[] args) {

        int[] nums = {3, 1, 2, 4};
        int k = 6;

        int answer = subarraySum(nums, k);

        System.out.println("Number of subarrays with sum " + k + ": " + answer);
    }

    public static int subarraySum(int[] nums, int k) {

        /*
         * Stores:
         *
         * prefix sum → number of times it has appeared
         */
        Map<Integer, Integer> prefixSumFrequency = new HashMap<>();

        /*
         * Empty prefix before the array starts.
         *
         * This is important for subarrays beginning at index 0.
         */
        prefixSumFrequency.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        // Traverse the array once.
        for (int num : nums) {

            // Add current element to prefix sum.
            prefixSum += num;

            /*
             * We need a previous prefix sum such that:
             *
             * prefixSum - previousPrefixSum = k
             *
             * Therefore:
             *
             * previousPrefixSum = prefixSum - k
             */
            int needed = prefixSum - k;

            /*
             * If 'needed' has appeared before, every occurrence
             * represents a subarray whose sum is k.
             */
            if (prefixSumFrequency.containsKey(needed)) {

                count += prefixSumFrequency.get(needed);
            }

            /*
             * Store the current prefix sum.
             *
             * If it already exists, increase its frequency.
             */
            prefixSumFrequency.put(
                    prefixSum,
                    prefixSumFrequency.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }
}