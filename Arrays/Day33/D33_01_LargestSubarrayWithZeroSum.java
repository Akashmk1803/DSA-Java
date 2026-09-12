package Day33;

import java.util.HashMap;
import java.util.Map;

/*
 * Day 33: Largest Subarray with Sum 0
 *
 * Problem Statement:
 * ------------------
 * Given an integer array containing both positive and negative
 * integers, find the length of the longest contiguous subarray
 * whose sum is equal to 0.
 *
 * If no such subarray exists, return 0.
 *
 *
 * Example:
 *
 * Input:
 * arr = [15, -2, 2, -8, 1, 7, 10, 23]
 *
 * Output:
 * 5
 *
 * Explanation:
 *
 * The subarray:
 *
 * [-2, 2, -8, 1, 7]
 *
 * has sum:
 *
 * -2 + 2 - 8 + 1 + 7 = 0
 *
 * Length = 5
 *
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE
 * ------------------------------------------------------------
 *
 * Generate every possible subarray.
 *
 * For every starting index i:
 *
 *     Start sum = 0
 *
 *     For every ending index j:
 *
 *         Add arr[j] to sum.
 *
 *         If sum == 0:
 *             update maximum length.
 *
 *
 * Example:
 *
 * arr = [9, -3, 3, -1, 6, -5]
 *
 * Starting from index 0:
 *
 * 9
 * 9 + (-3)
 * 9 + (-3) + 3
 * ...
 *
 * We repeat this for every starting index.
 *
 *
 * Time Complexity:
 * O(n^2)
 *
 * Space Complexity:
 * O(1)
 *
 *
 * ------------------------------------------------------------
 * APPROACH 2: OPTIMAL APPROACH
 * ------------------------------------------------------------
 *
 * Use:
 *
 *     Prefix Sum + HashMap
 *
 *
 * KEY IDEA:
 * ----------
 *
 * Suppose the prefix sum at index i is:
 *
 * prefixSum[i]
 *
 * If the same prefix sum appeared earlier at index j,
 * then:
 *
 * prefixSum[i] == prefixSum[j]
 *
 * Therefore:
 *
 * prefixSum[i] - prefixSum[j] = 0
 *
 * Which means:
 *
 * arr[j + 1 ... i]
 *
 * has sum 0.
 *
 *
 * Example:
 *
 * arr = [9, -3, 3, -1, 6, -5]
 *
 * Prefix sums:
 *
 * Index:       0    1   2   3   4   5
 * Array:       9   -3   3  -1   6  -5
 * Prefix:      9    6   9   8  14   9
 *
 *
 * Prefix sum 9 appears at:
 *
 * index 0
 * index 2
 * index 5
 *
 *
 * Between index 0 and index 2:
 *
 * [-3, 3]
 *
 * Sum = 0
 *
 * Length = 2
 *
 *
 * Between index 0 and index 5:
 *
 * [-3, 3, -1, 6, -5]
 *
 * Sum = 0
 *
 * Length = 5
 *
 *
 * Therefore the longest zero-sum subarray has length 5.
 *
 *
 * ------------------------------------------------------------
 * WHY DO WE STORE ONLY THE FIRST OCCURRENCE?
 * ------------------------------------------------------------
 *
 * This is very important.
 *
 * Suppose a prefix sum occurs at:
 *
 * index 2
 * index 7
 *
 * The subarray between them has sum 0.
 *
 * If we store index 2, then:
 *
 * length = 7 - 2 = 5
 *
 * If the same prefix sum occurs again at index 10:
 *
 * length = 10 - 2 = 8
 *
 * This is longer.
 *
 * Therefore, we should always keep the FIRST occurrence
 * of a prefix sum.
 *
 * This gives us the maximum possible subarray length.
 *
 *
 * ------------------------------------------------------------
 * WHY DO WE STORE PREFIX SUM 0 AT INDEX -1?
 * ------------------------------------------------------------
 *
 * We initialize:
 *
 * map.put(0, -1);
 *
 * This handles subarrays that start from index 0.
 *
 * Example:
 *
 * arr = [1, -1, 2]
 *
 * At index 1:
 *
 * prefixSum = 0
 *
 * We find 0 already stored at index -1.
 *
 * Length:
 *
 * 1 - (-1) = 2
 *
 * Therefore:
 *
 * [1, -1]
 *
 * is correctly counted.
 *
 *
 * ------------------------------------------------------------
 * OPTIMAL ALGORITHM
 * ------------------------------------------------------------
 *
 * 1. Create a HashMap.
 *
 * 2. Store:
 *
 *      prefixSum = 0
 *      index = -1
 *
 * 3. Traverse the array.
 *
 * 4. Add the current element to prefixSum.
 *
 * 5. If prefixSum already exists in the map:
 *
 *      currentLength = i - firstIndex
 *
 *      update maximum length.
 *
 * 6. Otherwise, store the prefixSum with its current index.
 *
 * 7. Return maximum length.
 *
 *
 * ------------------------------------------------------------
 * TIME COMPLEXITY
 * ------------------------------------------------------------
 *
 * We traverse the array once.
 *
 * HashMap operations take O(1) average time.
 *
 * Therefore:
 *
 * Time Complexity = O(n) average
 *
 *
 * ------------------------------------------------------------
 * SPACE COMPLEXITY
 * ------------------------------------------------------------
 *
 * In the worst case, every prefix sum is different.
 *
 * The HashMap can contain n entries.
 *
 * Therefore:
 *
 * Space Complexity = O(n)
 *
 *
 * ------------------------------------------------------------
 * KEY LEARNING
 * ------------------------------------------------------------
 *
 * The most important idea is:
 *
 * SAME PREFIX SUM
 *        ↓
 * DIFFERENCE BETWEEN THEM = 0
 *        ↓
 * SUBARRAY BETWEEN THEM HAS SUM 0
 *
 *
 * Pattern:
 *
 * Prefix Sum + HashMap
 *
 * is useful for many subarray-sum problems.
 *
 * ------------------------------------------------------------
 */
public class D33_01_LargestSubarrayWithZeroSum {

    public static void main(String[] args) {

        int[] arr = {15, -2, 2, -8, 1, 7, 10, 23};

        int result = maxLen(arr);

        System.out.println("Longest zero-sum subarray length: " + result);
    }


    /*
     * Optimal Approach:
     *
     * Prefix Sum + HashMap
     *
     * Time Complexity:
     * O(n) average
     *
     * Space Complexity:
     * O(n)
     */
    public static int maxLen(int[] arr) {

        /*
         * HashMap stores:
         *
         * Prefix Sum -> First Index Where It Appeared
         */
        Map<Integer, Integer> prefixSumMap = new HashMap<>();

        /*
         * Prefix sum 0 is considered to occur before
         * the array starts, at index -1.
         *
         * This allows us to correctly calculate the length
         * of a zero-sum subarray starting from index 0.
         */
        prefixSumMap.put(0, -1);

        int prefixSum = 0;
        int maxLength = 0;

        /*
         * Traverse the array.
         */
        for (int i = 0; i < arr.length; i++) {

            /*
             * Add current element to prefix sum.
             */
            prefixSum += arr[i];

            /*
             * If this prefix sum has appeared before,
             * then the elements between the previous index
             * and the current index have sum 0.
             */
            if (prefixSumMap.containsKey(prefixSum)) {

                /*
                 * Get the FIRST occurrence of this prefix sum.
                 */
                int firstIndex = prefixSumMap.get(prefixSum);

                /*
                 * Calculate the length of the zero-sum subarray.
                 */
                int currentLength = i - firstIndex;

                /*
                 * Update maximum length.
                 */
                maxLength = Math.max(maxLength, currentLength);

            } else {

                /*
                 * Store the prefix sum only the FIRST time
                 * we encounter it.
                 *
                 * Why?
                 *
                 * Earlier index = longer possible subarray.
                 */
                prefixSumMap.put(prefixSum, i);
            }
        }

        return maxLength;
    }
}


/*
 * ------------------------------------------------------------
 * TRACE
 * ------------------------------------------------------------
 *
 * Input:
 *
 * [15, -2, 2, -8, 1, 7, 10, 23]
 *
 *
 * Initially:
 *
 * prefixSum = 0
 * maxLength = 0
 *
 * HashMap:
 *
 * {0 = -1}
 *
 *
 * ------------------------------------------------------------
 *
 * i = 0
 * arr[0] = 15
 *
 * prefixSum = 15
 *
 * 15 is not in the map.
 *
 * Store:
 *
 * {0=-1, 15=0}
 *
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * arr[1] = -2
 *
 * prefixSum = 13
 *
 * Store:
 *
 * 13=1
 *
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * arr[2] = 2
 *
 * prefixSum = 15
 *
 * 15 already exists at index 0.
 *
 * Therefore:
 *
 * subarray from index 1 to 2:
 *
 * [-2, 2]
 *
 * Sum = 0
 *
 * Length:
 *
 * 2 - 0 = 2
 *
 * maxLength = 2
 *
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * arr[3] = -8
 *
 * prefixSum = 7
 *
 * Store:
 *
 * 7=3
 *
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * arr[4] = 1
 *
 * prefixSum = 8
 *
 * Store:
 *
 * 8=4
 *
 *
 * ------------------------------------------------------------
 *
 * i = 5
 * arr[5] = 7
 *
 * prefixSum = 15
 *
 * 15 already exists at index 0.
 *
 * Therefore:
 *
 * subarray from index 1 to 5:
 *
 * [-2, 2, -8, 1, 7]
 *
 * Sum = 0
 *
 * Length:
 *
 * 5 - 0 = 5
 *
 * maxLength = 5
 *
 *
 * ------------------------------------------------------------
 *
 * i = 6
 * arr[6] = 10
 *
 * prefixSum = 25
 *
 * Store:
 *
 * 25=6
 *
 *
 * ------------------------------------------------------------
 *
 * i = 7
 * arr[7] = 23
 *
 * prefixSum = 48
 *
 * Store:
 *
 * 48=7
 *
 *
 * ------------------------------------------------------------
 *
 * FINAL ANSWER:
 *
 * 5
 *
 *
 * Longest zero-sum subarray:
 *
 * [-2, 2, -8, 1, 7]
 *
 *
 * ------------------------------------------------------------
 * QUICK MEMORY TRICK
 * ------------------------------------------------------------
 *
 * If:
 *
 * prefixSum repeats
 *
 * then:
 *
 * subarray between those two positions = 0
 *
 *
 * For longest length:
 *
 * KEEP THE FIRST INDEX.
 *
 *
 * Initialization:
 *
 * map.put(0, -1);
 *
 * This one line is extremely important.
 */