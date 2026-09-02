package Day23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * ============================================================
 * Day 23 - Problem 01
 * Leaders in an Array
 * ============================================================
 *
 * Problem Statement:
 * Given an integer array nums, find all the leaders in the
 * array.
 *
 * An element is called a leader if it is strictly greater than
 * all the elements to its right.
 *
 * The rightmost element is always a leader because there are
 * no elements to its right.
 *
 * The leaders must be returned in the same order in which
 * they appear in the original array.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * [4, 7, 1, 0]
 *
 * Output:
 * [7, 1, 0]
 *
 * Explanation:
 *
 * 0 is the rightmost element, so it is a leader.
 *
 * 1 > 0
 * Therefore, 1 is a leader.
 *
 * 7 > 1 and 0
 * Therefore, 7 is a leader.
 *
 * 4 is not a leader because 7 is greater than 4.
 *
 * ============================================================
 *
 * Example 2:
 *
 * Input:
 * [10, 22, 12, 3, 0, 6]
 *
 * Output:
 * [22, 12, 6]
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * For every element, check all elements to its right.
 *
 * If the current element is greater than every element on its
 * right, it is a leader.
 *
 * Algorithm:
 *
 * 1. Traverse every element of the array.
 *
 * 2. For each element, assume it is a leader.
 *
 * 3. Check every element to its right.
 *
 * 4. If any element is greater than or equal to the current
 *    element, it is not a leader.
 *
 * 5. Otherwise, add it to the result.
 *
 * Time Complexity:
 * O(n²)
 *
 * Space Complexity:
 * O(n)
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 *
 * Approach:
 * Traverse the array from right to left while maintaining the
 * maximum element encountered so far.
 *
 * Why traverse from right to left?
 *
 * Because an element can only be a leader if it is greater than
 * everything on its right.
 *
 * When moving from right to left, we already know the maximum
 * element on the right.
 *
 * ============================================================
 *
 * VARIABLES:
 *
 * maxRight:
 * Stores the largest element encountered so far while moving
 * from right to left.
 *
 * leaders:
 * Stores the leader elements.
 *
 * ============================================================
 *
 * ALGORITHM:
 *
 * 1. Start from the last element.
 *
 * 2. The last element is always a leader.
 *
 * 3. Maintain maxRight.
 *
 * 4. For every element from right to left:
 *
 *      If nums[i] > maxRight:
 *
 *          It is a leader.
 *
 *          Add it to leaders.
 *
 *          Update maxRight.
 *
 * 5. Since we traverse from right to left, leaders are stored
 *    in reverse order.
 *
 * 6. Reverse the leaders list.
 *
 * 7. Return the result.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 *
 * [10, 22, 12, 3, 0, 6]
 *
 * ------------------------------------------------------------
 *
 * Start from right:
 *
 * maxRight = 6
 *
 * 6 is the rightmost element.
 *
 * Therefore:
 *
 * leaders = [6]
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * nums[i] = 0
 *
 * Compare:
 *
 * 0 > 6 -> false
 *
 * 0 is not a leader.
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * nums[i] = 3
 *
 * Compare:
 *
 * 3 > 6 -> false
 *
 * 3 is not a leader.
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * nums[i] = 12
 *
 * Compare:
 *
 * 12 > 6 -> true
 *
 * 12 is a leader.
 *
 * Add:
 *
 * leaders = [6, 12]
 *
 * Update:
 *
 * maxRight = 12
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * nums[i] = 22
 *
 * Compare:
 *
 * 22 > 12 -> true
 *
 * 22 is a leader.
 *
 * leaders = [6, 12, 22]
 *
 * Update:
 *
 * maxRight = 22
 *
 * ------------------------------------------------------------
 *
 * i = 0
 * nums[i] = 10
 *
 * Compare:
 *
 * 10 > 22 -> false
 *
 * 10 is not a leader.
 *
 * ============================================================
 *
 * At this point:
 *
 * leaders = [6, 12, 22]
 *
 * But this is reverse order.
 *
 * Reverse the list:
 *
 * [22, 12, 6]
 *
 * ============================================================
 *
 * FINAL RESULT:
 *
 * [22, 12, 6]
 *
 * ============================================================
 *
 * IMPORTANT:
 *
 * The comparison must be STRICTLY greater:
 *
 *      nums[i] > maxRight
 *
 * Not:
 *
 *      nums[i] >= maxRight
 *
 * Because the problem says the element must be strictly greater
 * than all elements to its right.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * The array is traversed once and the result is reversed.
 *
 * ============================================================
 *
 * SPACE COMPLEXITY:
 * O(n)
 *
 * The leaders are stored in a separate list.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Traversing from right to left
 * - Maintaining a running maximum
 * - Leader element concept
 * - Strict comparison
 * - Reversing the result
 * - O(n) time
 *
 * ============================================================
 */

public class D23_01_LeadersInAnArray {

    public static void main(String[] args) {

        int[] nums = {10, 22, 12, 3, 0, 6};

        List<Integer> leaders = findLeaders(nums);

        System.out.println("Leaders: " + leaders);
    }

    public static List<Integer> findLeaders(int[] nums) {

        List<Integer> leaders = new ArrayList<>();

        int maxRight = Integer.MIN_VALUE;

        for (int i = nums.length - 1; i >= 0; i--) {

            if (nums[i] > maxRight) {

                leaders.add(nums[i]);

                maxRight = nums[i];
            }
        }

        Collections.reverse(leaders);

        return leaders;
    }
}