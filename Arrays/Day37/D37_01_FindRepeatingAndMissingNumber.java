package Day37;

import java.util.Arrays;

/*
 * Day 37: Find the Repeating and Missing Number
 *
 * Problem Statement:
 * ------------------
 * Given an integer array nums of size n containing values
 * from [1, n].
 *
 * Every number should appear exactly once.
 *
 * However:
 *
 *      One number appears TWICE.
 *      One number is MISSING.
 *
 * We need to return:
 *
 *      [repeating, missing]
 *
 *
 * Example:
 *
 * Input:
 *
 * nums = [3, 5, 4, 1, 1]
 *
 * Output:
 *
 * [1, 2]
 *
 *
 * Explanation:
 *
 * Numbers that should be present:
 *
 * [1, 2, 3, 4, 5]
 *
 * Actual:
 *
 * [1, 1, 3, 4, 5]
 *
 * 1 appears twice.
 *
 * 2 is missing.
 *
 *
 * IMPORTANT:
 * ----------
 * We are NOT allowed to modify the original array.
 *
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE
 * ------------------------------------------------------------
 *
 * For every number from 1 to n:
 *
 * Count how many times it appears in the array.
 *
 * If count == 2:
 *
 *      repeating = number
 *
 * If count == 0:
 *
 *      missing = number
 *
 *
 * Example:
 *
 * nums = [3,5,4,1,1]
 *
 * Check 1:
 * count = 2
 * -> repeating = 1
 *
 * Check 2:
 * count = 0
 * -> missing = 2
 *
 *
 * Time Complexity:
 *
 * O(n^2)
 *
 * Space Complexity:
 *
 * O(1)
 *
 *
 * ------------------------------------------------------------
 * APPROACH 2: BETTER APPROACH
 * ------------------------------------------------------------
 *
 * Use a frequency array.
 *
 * Create:
 *
 *      frequency[n + 1]
 *
 * Traverse nums and increase:
 *
 *      frequency[num]++
 *
 * Then traverse from 1 to n.
 *
 * If:
 *
 *      frequency[i] == 2
 *
 * then i is repeating.
 *
 * If:
 *
 *      frequency[i] == 0
 *
 * then i is missing.
 *
 *
 * Time Complexity:
 *
 * O(n)
 *
 * Space Complexity:
 *
 * O(n)
 *
 *
 * ------------------------------------------------------------
 * OPTIMAL APPROACH 1: MATHEMATICAL APPROACH
 * ------------------------------------------------------------
 *
 * We know the numbers should be:
 *
 *      1, 2, 3, ..., n
 *
 *
 * Expected sum:
 *
 *      S = n * (n + 1) / 2
 *
 *
 * Expected sum of squares:
 *
 *      S2 = n * (n + 1) * (2n + 1) / 6
 *
 *
 * Let:
 *
 *      repeating = A
 *      missing = B
 *
 *
 * Actual sum is:
 *
 *      S + A - B
 *
 *
 * Therefore:
 *
 * actualSum - expectedSum = A - B
 *
 *
 * Let:
 *
 *      X = A - B
 *
 *
 * Now consider the sum of squares.
 *
 * Actual square sum:
 *
 *      S2 + A^2 - B^2
 *
 *
 * Therefore:
 *
 * actualSquareSum - expectedSquareSum
 *
 *      = A^2 - B^2
 *
 *
 * Using:
 *
 *      A^2 - B^2 = (A-B)(A+B)
 *
 *
 * We already know:
 *
 *      A-B = X
 *
 * Therefore we can calculate:
 *
 *      A+B
 *
 * Finally:
 *
 *      A = ((A-B) + (A+B)) / 2
 *
 *      B = (A+B - (A-B)) / 2
 *
 *
 * IMPORTANT:
 * ----------
 * Use long instead of int because n can be 100000
 * and square sums can become very large.
 *
 *
 * Time Complexity:
 *
 * O(n)
 *
 * Space Complexity:
 *
 * O(1)
 *
 *
 * ------------------------------------------------------------
 * OPTIMAL APPROACH 2: XOR APPROACH
 * ------------------------------------------------------------
 *
 * This is usually the safest optimal approach because
 * it avoids large sum and square calculations.
 *
 *
 * Important XOR properties:
 *
 *      x ^ x = 0
 *
 *      x ^ 0 = x
 *
 *
 * Therefore duplicate values cancel each other.
 *
 *
 * XOR all numbers from:
 *
 *      1 to n
 *
 * and XOR all numbers in nums.
 *
 * The result will be:
 *
 *      repeating ^ missing
 *
 *
 * Let:
 *
 *      XOR = repeating ^ missing
 *
 *
 * But we still need to separate the two numbers.
 *
 *
 * ------------------------------------------------------------
 * HOW DO WE SEPARATE THEM?
 * ------------------------------------------------------------
 *
 * Since:
 *
 *      XOR = repeating ^ missing
 *
 * XOR has at least one bit set to 1 because the two numbers
 * are different.
 *
 * Find any set bit.
 *
 * A common method is:
 *
 *      rightmostSetBit = XOR & (-XOR)
 *
 *
 * This bit is different between the repeating and missing
 * numbers.
 *
 *
 * Divide all numbers into two groups:
 *
 * Group 1:
 *
 * Numbers where that bit is 0.
 *
 * Group 2:
 *
 * Numbers where that bit is 1.
 *
 *
 * XOR within each group.
 *
 * Duplicate values cancel.
 *
 * Eventually the two groups produce:
 *
 *      repeating
 *      missing
 *
 *
 * ------------------------------------------------------------
 * WHY XOR IS GOOD HERE
 * ------------------------------------------------------------
 *
 * No modification of the original array.
 *
 * No extra array or HashMap.
 *
 * No overflow from square sums.
 *
 * Time:
 *
 *      O(n)
 *
 * Space:
 *
 *      O(1)
 *
 *
 * ------------------------------------------------------------
 * FINAL RECOMMENDED APPROACH
 * ------------------------------------------------------------
 *
 * Use the XOR approach.
 *
 * It gives:
 *
 *      O(n) time
 *      O(1) extra space
 *
 * while respecting the requirement that nums must not be modified.
 */
public class D37_01_FindRepeatingAndMissingNumber {

    public static void main(String[] args) {

        int[] nums = {3, 5, 4, 1, 1};

        int[] result = findMissingAndRepeating(nums);

        System.out.println(
                "Repeating number: " + result[0]
        );

        System.out.println(
                "Missing number: " + result[1]
        );

        System.out.println(
                "Result: " + Arrays.toString(result)
        );
    }


    /*
     * Optimal XOR Approach
     *
     * Time Complexity:
     * O(n)
     *
     * Space Complexity:
     * O(1)
     *
     * Result:
     *
     * [repeating, missing]
     */
    public static int[] findMissingAndRepeating(int[] nums) {

        int n = nums.length;

        /*
         * ----------------------------------------------------
         * STEP 1: XOR all array elements and numbers 1 to n
         * ----------------------------------------------------
         *
         * Suppose:
         *
         * repeating = A
         * missing = B
         *
         * Every normal number appears once in both groups,
         * so they cancel.
         *
         * The final XOR becomes:
         *
         * A ^ B
         */
        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }


        /*
         * Now:
         *
         * xor = repeating ^ missing
         */


        /*
         * ----------------------------------------------------
         * STEP 2: Find the rightmost set bit
         * ----------------------------------------------------
         *
         * Example:
         *
         * xor = 6
         *
         * Binary:
         *
         * 6 = 110
         *
         * Rightmost set bit:
         *
         * 010
         *
         * Formula:
         *
         * xor & (-xor)
         */
        int rightmostSetBit = xor & (-xor);


        /*
         * ----------------------------------------------------
         * STEP 3: Divide values into two groups
         * ----------------------------------------------------
         *
         * Group 1:
         * Rightmost bit is 0.
         *
         * Group 2:
         * Rightmost bit is 1.
         *
         * Because the repeating and missing numbers have
         * different values at this bit, they will end up
         * in different groups.
         */
        int group1 = 0;
        int group2 = 0;


        /*
         * XOR all elements of nums.
         */
        for (int num : nums) {

            if ((num & rightmostSetBit) != 0) {

                group2 ^= num;

            } else {

                group1 ^= num;
            }
        }


        /*
         * XOR all numbers from 1 to n.
         */
        for (int i = 1; i <= n; i++) {

            if ((i & rightmostSetBit) != 0) {

                group2 ^= i;

            } else {

                group1 ^= i;
            }
        }


        /*
         * Now group1 and group2 contain:
         *
         *      repeating
         *      missing
         *
         * But we don't yet know which is which.
         */


        /*
         * ----------------------------------------------------
         * STEP 4: Determine which number is repeating
         * ----------------------------------------------------
         *
         * Search the original array.
         *
         * If group1 appears in nums, it is the repeating number.
         *
         * Otherwise group2 is the repeating number.
         */
        int repeating;
        int missing;

        if (contains(nums, group1)) {

            repeating = group1;
            missing = group2;

        } else {

            repeating = group2;
            missing = group1;
        }


        /*
         * Return:
         *
         * [repeating, missing]
         */
        return new int[]{repeating, missing};
    }


    /*
     * Helper method to check whether a number exists
     * in the original array.
     *
     * Time Complexity:
     * O(n)
     *
     * Space Complexity:
     * O(1)
     */
    private static boolean contains(int[] nums, int target) {

        for (int num : nums) {

            if (num == target) {
                return true;
            }
        }

        return false;
    }
}


/*
 * ------------------------------------------------------------
 * TRACE OF XOR APPROACH
 * ------------------------------------------------------------
 *
 * Input:
 *
 * nums = [3, 5, 4, 1, 1]
 *
 * n = 5
 *
 * Numbers that should exist:
 *
 * [1, 2, 3, 4, 5]
 *
 *
 * Actual array:
 *
 * [1, 1, 3, 4, 5]
 *
 *
 * Repeating = 1
 * Missing = 2
 *
 *
 * ------------------------------------------------------------
 * STEP 1
 * ------------------------------------------------------------
 *
 * XOR all array values:
 *
 * 3 ^ 5 ^ 4 ^ 1 ^ 1
 *
 * The two 1s cancel:
 *
 * 1 ^ 1 = 0
 *
 * So this becomes:
 *
 * 3 ^ 5 ^ 4
 *
 *
 * Then XOR numbers 1 to 5:
 *
 * 1 ^ 2 ^ 3 ^ 4 ^ 5
 *
 * Common values cancel.
 *
 * What remains:
 *
 * 1 ^ 2
 *
 *
 * Therefore:
 *
 * xor = 1 ^ 2
 *
 *
 * ------------------------------------------------------------
 * STEP 2
 * ------------------------------------------------------------
 *
 * xor contains information about:
 *
 * repeating ^ missing
 *
 *      = 1 ^ 2
 *
 *
 * Since:
 *
 * 1 = 001
 * 2 = 010
 *
 * xor:
 *
 * 001
 * ^
 * 010
 * ---
 * 011
 *
 * xor = 3
 *
 *
 * Rightmost set bit:
 *
 * 001
 *
 *
 * ------------------------------------------------------------
 * STEP 3
 * ------------------------------------------------------------
 *
 * Divide numbers based on this bit.
 *
 * After XORing the two groups:
 *
 * group1 = 1
 * group2 = 2
 *
 *
 * We now know the two numbers are:
 *
 * 1 and 2
 *
 *
 * ------------------------------------------------------------
 * STEP 4
 * ------------------------------------------------------------
 *
 * Check which one actually appears in the array.
 *
 * 1 exists twice.
 *
 * Therefore:
 *
 * repeating = 1
 * missing = 2
 *
 *
 * FINAL:
 *
 * [1, 2]
 *
 *
 * ------------------------------------------------------------
 * ANOTHER EXAMPLE
 * ------------------------------------------------------------
 *
 * nums = [6, 5, 7, 1, 8, 6, 4, 3, 2]
 *
 * n = 9
 *
 * Expected:
 *
 * [1,2,3,4,5,6,7,8,9]
 *
 * Actual:
 *
 * [1,2,3,4,5,6,6,7,8]
 *
 *
 * 6 appears twice.
 *
 * 9 is missing.
 *
 * Therefore:
 *
 * [6, 9]
 *
 *
 * ------------------------------------------------------------
 * QUICK MEMORY TRICK
 * ------------------------------------------------------------
 *
 * Problem:
 *
 * One number repeats.
 * One number is missing.
 *
 *
 * XOR all:
 *
 *      ARRAY
 *
 * and:
 *
 *      1 TO N
 *
 * gives:
 *
 *      REPEATING ^ MISSING
 *
 *
 * Then:
 *
 *      Find a distinguishing bit
 *              ↓
 *      Divide into two groups
 *              ↓
 *      XOR each group
 *              ↓
 *      Get the two numbers
 *              ↓
 *      Check which one appears in array
 *              ↓
 *      Repeating + Missing
 *
 *
 * FINAL COMPLEXITY:
 *
 * Time  = O(n)
 * Space = O(1)
 */