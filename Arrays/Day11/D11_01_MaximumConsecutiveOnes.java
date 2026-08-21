package Day11;

/*
 * ============================================================
 * Day 11 - Problem 01
 * Maximum Consecutive Ones
 * ============================================================
 *
 * Problem Statement:
 * Given a binary array containing only 0 and 1, find the
 * maximum number of consecutive 1's in the array.
 *
 * ============================================================
 *
 * Examples:
 *
 * Example 1:
 *
 * Input:
 * [1, 1, 0, 1, 1, 1]
 *
 * Output:
 * 3
 *
 * Explanation:
 * There are two consecutive 1's at the beginning and three
 * consecutive 1's at the end.
 *
 * The maximum consecutive streak is 3.
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * [1, 0, 1, 1, 0, 1]
 *
 * Output:
 * 2
 *
 * Explanation:
 * The maximum consecutive streak of 1's is 2.
 *
 * ============================================================
 *
 * APPROACH - LINEAR TRAVERSAL
 *
 * Approach:
 * Traverse the array once while maintaining the count of
 * consecutive 1's.
 *
 * Two variables are used:
 *
 * count -> Stores the current streak of consecutive 1's.
 *
 * max -> Stores the maximum streak found so far.
 *
 * Whenever a 1 is found:
 *     Increment count.
 *
 * Whenever a 0 is found:
 *     Reset count to 0 because the current streak is broken.
 *
 * After every element, update max if the current count is
 * greater than the previous maximum.
 *
 * ============================================================
 *
 * Algorithm:
 *
 * 1. Initialize count = 0.
 *
 * 2. Initialize max = 0.
 *
 * 3. Traverse the array from left to right.
 *
 * 4. If nums[i] == 1:
 *       count++
 *
 * 5. Otherwise:
 *       count = 0
 *
 * 6. Update:
 *       max = Math.max(max, count)
 *
 * 7. Return max.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 * [1, 1, 0, 1, 1, 1]
 *
 * Initially:
 *
 * count = 0
 * max = 0
 *
 * ------------------------------------------------------------
 *
 * i = 0
 * nums[0] = 1
 *
 * count = 1
 * max = 1
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * nums[1] = 1
 *
 * count = 2
 * max = 2
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * nums[2] = 0
 *
 * Streak is broken.
 *
 * count = 0
 * max = 2
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * nums[3] = 1
 *
 * count = 1
 * max = 2
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * nums[4] = 1
 *
 * count = 2
 * max = 2
 *
 * ------------------------------------------------------------
 *
 * i = 5
 * nums[5] = 1
 *
 * count = 3
 * max = 3
 *
 * ------------------------------------------------------------
 *
 * Final Answer:
 *
 * 3
 *
 * ============================================================
 *
 * WHY DO WE RESET count BUT NOT max?
 *
 * count represents the current consecutive streak.
 *
 * When a 0 appears, the current streak ends, so count must
 * become 0.
 *
 * max represents the best streak found anywhere in the array.
 * Therefore, max should never be reset when a 0 appears.
 *
 * Example:
 *
 * [1, 1, 1, 0, 1]
 *
 * Before 0:
 * max = 3
 *
 * After 0:
 * count = 0
 * max = 3
 *
 * The previous maximum is still the answer.
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
 * - Linear traversal
 * - Maintaining a running count
 * - Resetting a counter when a condition breaks
 * - Maintaining a maximum value
 * - O(n) time and O(1) space
 *
 * ============================================================
 */

public class D11_01_MaximumConsecutiveOnes {

    public static void main(String[] args) {

        int[] nums = {1, 1, 0, 1, 1, 1};

        int maximumOnes = findMaxConsecutiveOnes(nums);

        System.out.println(
                "Maximum consecutive 1's: " + maximumOnes
        );
    }

    public static int findMaxConsecutiveOnes(int[] nums) {

        int count = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) {
                count++;
            } else {
                count = 0;
            }

            max = Math.max(max, count);
        }

        return max;
    }
}