package Day10;

/*
 * ============================================================
 * Day 10 - Problem 01
 * Find the Missing Number
 * ============================================================
 *
 * Problem Statement:
 * Given an array containing n distinct numbers in the range
 * [0, n], find the only number missing from the array.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * nums = [0, 2, 3, 1, 4]
 *
 * Output:
 * 5
 *
 * Explanation:
 * The complete range is [0, 5].
 * The array contains 0, 1, 2, 3 and 4.
 * Therefore, 5 is missing.
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * nums = [0, 1, 2, 4, 5, 6]
 *
 * Output:
 * 3
 *
 * Explanation:
 * The complete range is [0, 6].
 * The array contains 0, 1, 2, 4, 5 and 6.
 * Therefore, 3 is missing.
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Check every number from 0 to n and search for that number
 * in the array.
 *
 * Algorithm:
 *
 * 1. For every number from 0 to n:
 * 2. Search for that number in the array.
 * 3. If the number is not found, return it.
 *
 * Time Complexity:
 * O(n^2)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * Every number may require a complete traversal of the array.
 *
 * ============================================================
 *
 * BETTER APPROACH - SUM FORMULA
 *
 * Approach:
 * Calculate the expected sum of numbers from 0 to n.
 *
 * Formula:
 *
 * Sum = n * (n + 1) / 2
 *
 * Then calculate the actual sum of elements in the array.
 *
 * Missing Number:
 *
 * Missing = Expected Sum - Actual Sum
 *
 * Example:
 *
 * nums = [0, 1, 2, 4, 5, 6]
 * n = 6
 *
 * Expected Sum:
 *
 * 6 * 7 / 2 = 21
 *
 * Actual Sum:
 *
 * 0 + 1 + 2 + 4 + 5 + 6 = 18
 *
 * Missing:
 *
 * 21 - 18 = 3
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 *
 * ============================================================
 *
 * OPTIMAL APPROACH - XOR
 *
 * Approach:
 * Use the XOR operation to find the missing number.
 *
 * Important XOR properties:
 *
 * 1. x ^ x = 0
 *
 * 2. x ^ 0 = x
 *
 * Since every number except the missing number appears both
 * in the expected range and in the array, equal numbers
 * cancel each other.
 *
 * The missing number remains at the end.
 *
 * ============================================================
 *
 * Algorithm:
 *
 * 1. Initialize missing with n.
 *
 * 2. Traverse the array using index i.
 *
 * 3. XOR missing with i.
 *
 * 4. XOR missing with nums[i].
 *
 * 5. After the loop, missing contains the answer.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 * nums = [0, 1, 2, 4, 5, 6]
 *
 * n = 6
 *
 * We start with:
 *
 * missing = 6
 *
 * During the loop, every number that appears both in the
 * range and in the array cancels itself:
 *
 * 0 ^ 0 = 0
 * 1 ^ 1 = 0
 * 2 ^ 2 = 0
 * 4 ^ 4 = 0
 * 5 ^ 5 = 0
 * 6 ^ 6 = 0
 *
 * The only number without a matching pair is:
 *
 * 3
 *
 * Therefore:
 *
 * missing = 3
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * The array is traversed only once.
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
 * - Finding missing elements
 * - XOR operation
 * - XOR cancellation property
 * - Sum formula
 * - Array traversal
 * - Comparing brute force and optimal approaches
 * - O(n) time and O(1) space
 *
 * ============================================================
 */

public class D10_01_FindMissingNumber {

    public static void main(String[] args) {

        int[] nums = {0, 1, 2, 4, 5, 6};

        int missing = findMissingNumber(nums);

        System.out.println("The missing number is: " + missing);
    }

    public static int findMissingNumber(int[] nums) {

        int missing = nums.length;

        for (int i = 0; i < nums.length; i++) {

            missing ^= i;
            missing ^= nums[i];
        }

        return missing;
    }
}