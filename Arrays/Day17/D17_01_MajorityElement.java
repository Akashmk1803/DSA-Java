package Day17;

import java.util.HashMap;
import java.util.Map;

/*
 * ============================================================
 * Day 17 - Problem 01
 * Majority Element
 * ============================================================
 *
 * Problem Statement:
 * Given an integer array nums of size n, find the majority
 * element.
 *
 * The majority element is the element that appears more than
 * n / 2 times in the array.
 *
 * The array is guaranteed to contain a majority element.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * [7, 0, 0, 1, 7, 7, 2, 7, 7]
 *
 * Output:
 * 7
 *
 * Explanation:
 * 7 appears 5 times in an array of size 9.
 *
 * 5 > 9 / 2
 *
 * Therefore, 7 is the majority element.
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * [1, 1, 1, 2, 1, 2]
 *
 * Output:
 * 1
 *
 * Explanation:
 * 1 appears 4 times in an array of size 6.
 *
 * 4 > 6 / 2
 *
 * Therefore, 1 is the majority element.
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * For every element, count how many times it appears in the
 * array.
 *
 * If its frequency is greater than n / 2, return that element.
 *
 * Algorithm:
 *
 * 1. Select every element one by one.
 *
 * 2. Count its occurrences in the array.
 *
 * 3. If count > n / 2, return that element.
 *
 * Time Complexity:
 * O(n²)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * Each element may require another complete traversal.
 *
 * ============================================================
 *
 * BETTER APPROACH - HASHMAP
 *
 * Approach:
 * Use a HashMap to store the frequency of every element.
 *
 * Map:
 *
 * element -> frequency
 *
 * Example:
 *
 * [7, 0, 0, 1, 7, 7, 2, 7, 7]
 *
 * Frequency:
 *
 * 7 -> 5
 * 0 -> 2
 * 1 -> 1
 * 2 -> 1
 *
 * Since 7 occurs more than n / 2 times, return 7.
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
 * OPTIMAL APPROACH
 * Moore's Voting Algorithm
 *
 * Approach:
 * Maintain a candidate and a count.
 *
 * The majority element appears more than n / 2 times.
 *
 * Therefore, even if every occurrence of the majority element
 * is paired with a different element, some occurrences of the
 * majority element will remain.
 *
 * Different elements cancel one occurrence of the candidate.
 *
 * ============================================================
 *
 * VARIABLES:
 *
 * candidate -> Current possible majority element.
 *
 * count -> Voting strength of the current candidate.
 *
 * ============================================================
 *
 * ALGORITHM:
 *
 * 1. Initialize:
 *
 *      candidate = 0
 *      count = 0
 *
 * 2. Traverse the array.
 *
 * 3. If count == 0:
 *
 *      candidate = nums[i]
 *
 * 4. If nums[i] == candidate:
 *
 *      count++
 *
 *    Otherwise:
 *
 *      count--
 *
 * 5. After the traversal, candidate contains the majority
 *    element.
 *
 * 6. Since the problem guarantees that a majority element
 *    exists, no second verification pass is required.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 *
 * [2, 2, 1, 1, 1, 2, 2]
 *
 * Initially:
 *
 * candidate = 0
 * count = 0
 *
 * ------------------------------------------------------------
 *
 * i = 0
 * nums[i] = 2
 *
 * count == 0
 *
 * candidate = 2
 *
 * nums[i] == candidate
 *
 * count = 1
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * nums[i] = 2
 *
 * nums[i] == candidate
 *
 * count = 2
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * nums[i] = 1
 *
 * 1 != 2
 *
 * count = 1
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * nums[i] = 1
 *
 * 1 != 2
 *
 * count = 0
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * nums[i] = 1
 *
 * count == 0
 *
 * candidate = 1
 *
 * count = 1
 *
 * ------------------------------------------------------------
 *
 * i = 5
 * nums[i] = 2
 *
 * 2 != 1
 *
 * count = 0
 *
 * ------------------------------------------------------------
 *
 * i = 6
 * nums[i] = 2
 *
 * count == 0
 *
 * candidate = 2
 *
 * count = 1
 *
 * ------------------------------------------------------------
 *
 * Final:
 *
 * candidate = 2
 *
 * Therefore:
 *
 * Majority Element = 2
 *
 * ============================================================
 *
 * WHY DOES MOORE'S VOTING ALGORITHM WORK?
 *
 * Every time we encounter an element different from the
 * candidate, we decrease the candidate's count.
 *
 * This effectively cancels one candidate occurrence with one
 * different element.
 *
 * Since the majority element occurs more than all other
 * elements combined, it cannot be completely cancelled.
 *
 * Therefore, the remaining candidate is the majority element.
 *
 * ============================================================
 *
 * IMPORTANT:
 *
 * This problem guarantees that a majority element exists.
 *
 * If a majority element was NOT guaranteed, we would need
 * another pass to verify:
 *
 *      frequency > n / 2
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * The array is traversed once.
 *
 * ============================================================
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * Only candidate and count variables are used.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Majority element
 * - Moore's Voting Algorithm
 * - Candidate and count
 * - Cancellation technique
 * - HashMap frequency counting
 * - O(n) time and O(1) space
 *
 * ============================================================
 */

public class D17_01_MajorityElement {

    public static void main(String[] args) {

        int[] nums = {7, 0, 0, 1, 7, 7, 2, 7, 7};

        int majorityElement = findMajorityElement(nums);

        System.out.println(
                "The majority element is: " + majorityElement
        );
    }

    public static int findMajorityElement(int[] nums) {

        int candidate = 0;
        int count = 0;

        for (int num : nums) {

            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}