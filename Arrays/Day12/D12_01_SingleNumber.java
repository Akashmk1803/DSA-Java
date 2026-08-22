package Day12;

/*
 * ============================================================
 * Day 12 - Problem 01
 * Single Number
 * ============================================================
 *
 * Problem Statement:
 * Given a non-empty array of integers, every element appears
 * twice except for one element which appears only once.
 *
 * Find and return the element that appears only once.
 *
 * The solution must have:
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 *
 * ============================================================
 *
 * Examples:
 *
 * Example 1:
 *
 * Input:
 * [2, 2, 1]
 *
 * Output:
 * 1
 *
 * Explanation:
 * 2 appears twice and 1 appears once.
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * [4, 1, 2, 1, 2]
 *
 * Output:
 * 4
 *
 * Explanation:
 * 1 appears twice.
 * 2 appears twice.
 * 4 appears once.
 *
 * Therefore, the answer is 4.
 *
 * ------------------------------------------------------------
 *
 * Example 3:
 *
 * Input:
 * [1]
 *
 * Output:
 * 1
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * For every element, count how many times it appears in the
 * array.
 *
 * If an element appears only once, return that element.
 *
 * Algorithm:
 *
 * 1. Select each element one by one.
 * 2. Count its occurrences in the array.
 * 3. If the count is 1, return that element.
 *
 * Time Complexity:
 * O(n^2)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * Every element may require another complete traversal of the
 * array.
 *
 * ============================================================
 *
 * BETTER APPROACH - HASHMAP
 *
 * Approach:
 * Use a HashMap to store the frequency of each element.
 *
 * Algorithm:
 *
 * 1. Create a HashMap.
 * 2. Traverse the array.
 * 3. Store the frequency of every element.
 * 4. Traverse the map.
 * 5. Return the element whose frequency is 1.
 *
 * Example:
 *
 * Input:
 * [4, 1, 2, 1, 2]
 *
 * Frequency:
 *
 * 4 -> 1
 * 1 -> 2
 * 2 -> 2
 *
 * Therefore, return 4.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
 *
 * Drawback:
 * Additional memory is required for the HashMap.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH - XOR
 *
 * Approach:
 * Use the XOR operator to find the element that appears once.
 *
 * Important XOR properties:
 *
 * 1. x ^ x = 0
 *
 * 2. x ^ 0 = x
 *
 * 3. XOR is commutative:
 *    x ^ y = y ^ x
 *
 * 4. XOR is associative:
 *    (x ^ y) ^ z = x ^ (y ^ z)
 *
 * Since every element except one appears twice, all duplicate
 * elements cancel each other when XOR is applied.
 *
 * The element appearing once remains.
 *
 * ============================================================
 *
 * ALGORITHM:
 *
 * 1. Initialize:
 *
 *      single = 0
 *
 * 2. Traverse the array.
 *
 * 3. XOR every element with single:
 *
 *      single = single ^ nums[i]
 *
 * 4. After the complete traversal, single contains the
 *    element that appears only once.
 *
 * 5. Return single.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 * [4, 1, 2, 1, 2]
 *
 * Initially:
 *
 * single = 0
 *
 * ------------------------------------------------------------
 *
 * Process 4:
 *
 * single = 0 ^ 4
 *
 * single = 4
 *
 * ------------------------------------------------------------
 *
 * Process 1:
 *
 * single = 4 ^ 1
 *
 * ------------------------------------------------------------
 *
 * Process 2:
 *
 * single = previous ^ 2
 *
 * ------------------------------------------------------------
 *
 * Process 1:
 *
 * The two occurrences of 1 cancel:
 *
 * 1 ^ 1 = 0
 *
 * ------------------------------------------------------------
 *
 * Process 2:
 *
 * The two occurrences of 2 cancel:
 *
 * 2 ^ 2 = 0
 *
 * ------------------------------------------------------------
 *
 * Final:
 *
 * single = 4
 *
 * Therefore:
 *
 * Answer = 4
 *
 * ============================================================
 *
 * WHY DOES XOR WORK?
 *
 * Consider:
 *
 * [4, 1, 2, 1, 2]
 *
 * XOR all elements:
 *
 * 4 ^ 1 ^ 2 ^ 1 ^ 2
 *
 * Rearrange:
 *
 * 4 ^ (1 ^ 1) ^ (2 ^ 2)
 *
 * Cancel pairs:
 *
 * 4 ^ 0 ^ 0
 *
 * Therefore:
 *
 * 4
 *
 * The duplicate elements disappear and the single element
 * remains.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * The array is traversed exactly once.
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * Only one variable is used.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - XOR operator
 * - XOR cancellation
 * - HashMap vs XOR
 * - Linear traversal
 * - O(n) time and O(1) space
 *
 * ============================================================
 */

public class D12_01_SingleNumber {

    public static void main(String[] args) {

        int[] nums = {4, 1, 2, 1, 2};

        int single = findSingleNumber(nums);

        System.out.println("The single number is: " + single);
    }

    public static int findSingleNumber(int[] nums) {

        int single = 0;

        for (int num : nums) {

            single = single ^ num;
        }

        return single;
    }
}