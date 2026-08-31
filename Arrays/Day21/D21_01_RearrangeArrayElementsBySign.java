package Day21;

/*
 * ============================================================
 * Day 21 - Problem 01
 * Rearrange Array Elements by Sign
 * ============================================================
 *
 * Problem Statement:
 * Given an integer array of even length containing an equal
 * number of positive and negative integers, rearrange the
 * elements so that:
 *
 * 1. Every consecutive pair has opposite signs.
 * 2. The relative order of positive elements is preserved.
 * 3. The relative order of negative elements is preserved.
 * 4. The resulting array starts with a positive integer.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * [3, 1, -2, -5, 2, -4]
 *
 * Output:
 * [3, -2, 1, -5, 2, -4]
 *
 * Positive elements:
 * [3, 1, 2]
 *
 * Negative elements:
 * [-2, -5, -4]
 *
 * Their relative order is preserved.
 *
 * ============================================================
 *
 * Example 2:
 *
 * Input:
 * [-1, 1]
 *
 * Output:
 * [1, -1]
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Store all positive elements in one array/list and all
 * negative elements in another.
 *
 * Then place them alternately:
 *
 * positive, negative, positive, negative...
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 * Two Position Pointers
 *
 * Since the number of positive and negative elements is equal,
 * we know exactly where each type of element should go.
 *
 * Positive elements go to:
 *
 * 0, 2, 4, 6, ...
 *
 * Negative elements go to:
 *
 * 1, 3, 5, 7, ...
 *
 * Therefore, maintain:
 *
 * positiveIndex = 0
 * negativeIndex = 1
 *
 * ============================================================
 *
 * ALGORITHM:
 *
 * 1. Create a result array of the same size.
 *
 * 2. Initialize:
 *
 *      positiveIndex = 0
 *      negativeIndex = 1
 *
 * 3. Traverse the original array.
 *
 * 4. If nums[i] is positive:
 *
 *      result[positiveIndex] = nums[i]
 *      positiveIndex += 2
 *
 * 5. If nums[i] is negative:
 *
 *      result[negativeIndex] = nums[i]
 *      negativeIndex += 2
 *
 * 6. Return the result array.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 *
 * [2, 4, 5, -1, -3, -4]
 *
 * Initially:
 *
 * positiveIndex = 0
 * negativeIndex = 1
 *
 * ------------------------------------------------------------
 *
 * i = 0
 * nums[i] = 2
 *
 * Positive:
 *
 * result[0] = 2
 *
 * positiveIndex = 2
 *
 * Result:
 *
 * [2, _, _, _, _, _]
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * nums[i] = 4
 *
 * Positive:
 *
 * result[2] = 4
 *
 * positiveIndex = 4
 *
 * Result:
 *
 * [2, _, 4, _, _, _]
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * nums[i] = 5
 *
 * Positive:
 *
 * result[4] = 5
 *
 * positiveIndex = 6
 *
 * Result:
 *
 * [2, _, 4, _, 5, _]
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * nums[i] = -1
 *
 * Negative:
 *
 * result[1] = -1
 *
 * negativeIndex = 3
 *
 * Result:
 *
 * [2, -1, 4, _, 5, _]
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * nums[i] = -3
 *
 * Negative:
 *
 * result[3] = -3
 *
 * negativeIndex = 5
 *
 * Result:
 *
 * [2, -1, 4, -3, 5, _]
 *
 * ------------------------------------------------------------
 *
 * i = 5
 * nums[i] = -4
 *
 * Negative:
 *
 * result[5] = -4
 *
 * Result:
 *
 * [2, -1, 4, -3, 5, -4]
 *
 * ============================================================
 *
 * WHY DO WE INCREMENT BY 2?
 *
 * Positive elements must occupy:
 *
 * 0, 2, 4, 6...
 *
 * Negative elements must occupy:
 *
 * 1, 3, 5, 7...
 *
 * Therefore, after placing one element, we skip the next
 * position and move to the next position of the same sign.
 *
 * ============================================================
 *
 * IMPORTANT:
 *
 * The problem guarantees:
 *
 * - Equal number of positive and negative elements.
 * - Array length is even.
 *
 * Therefore, the two-pointer position approach always works.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * We traverse the array once.
 *
 * ============================================================
 *
 * SPACE COMPLEXITY:
 * O(n)
 *
 * We create a result array of size n.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Two-position pointer technique
 * - Maintaining relative order
 * - Separating positive and negative positions
 * - Inserting at alternate indices
 * - O(n) time
 * - O(n) space
 *
 * ============================================================
 */

public class D21_01_RearrangeArrayElementsBySign {

    public static void main(String[] args) {

        int[] nums = {2, 4, 5, -1, -3, -4};

        int[] result = rearrangeBySign(nums);

        System.out.print("Rearranged array: ");

        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public static int[] rearrangeBySign(int[] nums) {

        int n = nums.length;

        int[] result = new int[n];

        int positiveIndex = 0;
        int negativeIndex = 1;

        for (int num : nums) {

            if (num > 0) {

                result[positiveIndex] = num;
                positiveIndex += 2;

            } else {

                result[negativeIndex] = num;
                negativeIndex += 2;
            }
        }

        return result;
    }
}