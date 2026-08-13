package Day03;

/*
 * ============================================================
 * Day 03 - Problem 01
 * Check If Array Is Sorted and Rotated
 * ============================================================
 *
 * Problem Statement:
 * Given an array of integers, return true if the array was
 * originally sorted in non-decreasing order and then rotated
 * some number of positions, including zero.
 *
 * Duplicate elements are allowed.
 *
 * ============================================================
 *
 * Examples:
 *
 * Example 1:
 * Input  : [3, 4, 5, 1, 2]
 * Output : true
 *
 * Explanation:
 * The original sorted array is [1, 2, 3, 4, 5].
 * Rotating it by 2 positions gives [3, 4, 5, 1, 2].
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 * Input  : [2, 1, 3, 4]
 * Output : false
 *
 * Explanation:
 * There are multiple points where the order decreases,
 * so the array cannot be obtained by rotating a sorted array.
 *
 * ------------------------------------------------------------
 *
 * Example 3:
 * Input  : [1, 2, 3]
 * Output : true
 *
 * Explanation:
 * The array is already sorted. A rotation by zero positions
 * is also considered valid.
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Try every possible rotation of the array and check whether
 * the rotated array is sorted in non-decreasing order.
 *
 * If any rotation produces a sorted array, return true.
 * Otherwise, return false.
 *
 * Algorithm:
 * 1. Consider every possible rotation position.
 * 2. Check whether the resulting order is non-decreasing.
 * 3. If a sorted rotation is found, return true.
 * 4. If no valid rotation is found, return false.
 *
 * Time Complexity:
 * O(n^2)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * We may need to check up to n rotations, and each rotation
 * requires checking up to n elements.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 *
 * Key Observation:
 *
 * In a sorted array, every element is less than or equal to
 * the next element.
 *
 * After rotating a sorted array, there can be at most ONE
 * position where:
 *
 * nums[i] > nums[i + 1]
 *
 * This position represents the point where the largest values
 * wrap around to the smallest values.
 *
 * Example:
 *
 * [3, 4, 5, 1, 2]
 *
 * 3 <= 4  -> No break
 * 4 <= 5  -> No break
 * 5 > 1   -> ONE break
 * 1 <= 2  -> No break
 * 2 <= 3  -> No break (circular comparison)
 *
 * Since there is only one break, the answer is true.
 *
 * ------------------------------------------------------------
 *
 * Algorithm:
 *
 * 1. Initialize count = 0.
 *
 * 2. Traverse the array from index 0 to n - 1.
 *
 * 3. Compare the current element with the next element.
 *
 * 4. Use modulo to handle the circular comparison:
 *
 *    nums[i] > nums[(i + 1) % n]
 *
 * 5. If the current element is greater than the next element,
 *    increment count.
 *
 * 6. If count becomes greater than 1, return false immediately.
 *
 * 7. If the loop finishes with at most one break, return true.
 *
 * ============================================================
 *
 * WHY DOES (i + 1) % n WORK?
 *
 * Normally:
 *
 * i = 0 -> next index = 1
 * i = 1 -> next index = 2
 * i = 2 -> next index = 3
 * ...
 *
 * For the last element:
 *
 * i = n - 1
 *
 * (i + 1) % n
 * = n % n
 * = 0
 *
 * Therefore, the last element is compared with the first
 * element.
 *
 * This allows us to treat the array as circular.
 *
 * ============================================================
 *
 * TRACE EXAMPLE:
 *
 * Input:
 * [3, 4, 5, 1, 2]
 *
 * n = 5
 * count = 0
 *
 * i = 0:
 * 3 > 4 -> false
 * count = 0
 *
 * i = 1:
 * 4 > 5 -> false
 * count = 0
 *
 * i = 2:
 * 5 > 1 -> true
 * count = 1
 *
 * i = 3:
 * 1 > 2 -> false
 * count = 1
 *
 * i = 4:
 * 2 > 3 -> false
 * count = 1
 *
 * count = 1
 * Therefore, return true.
 *
 * ============================================================
 *
 * EARLY TERMINATION:
 *
 * If count becomes greater than 1:
 *
 * count > 1
 *
 * We immediately return false.
 *
 * There is no need to continue traversing because a sorted
 * and rotated array cannot contain more than one break.
 *
 * ============================================================
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Array traversal
 * - Circular array traversal
 * - Modulo operator (%)
 * - Detecting order breaks
 * - Handling duplicate elements
 * - Early termination
 * - Understanding independent if statements
 * - Tracing loop execution
 * - Time and space complexity
 *
 * ============================================================
 */

public class D3_01_CheckIfArrayIsSortedAndRotated {

    public static void main(String[] args) {

        int[] nums = {3, 4, 5, 1, 2};

        boolean result = check(nums);

        System.out.println(
                "Is the array sorted and rotated? " + result
        );
    }

    public static boolean check(int[] nums) {

        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }

            if (count > 1) {
                return false;
            }
        }

        return true;
    }
}