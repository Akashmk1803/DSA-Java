package Day24;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class D24_01_LongestConsecutiveSequence {

    /*
     * ============================================================
     * PROBLEM: LONGEST CONSECUTIVE SEQUENCE
     * ============================================================
     *
     * Given an unsorted array of integers, return the length of
     * the longest sequence of consecutive integers.
     *
     * The numbers do NOT have to be next to each other in the array.
     *
     * Example:
     *
     * Input:  [100, 4, 200, 1, 3, 2]
     *
     * Longest consecutive sequence:
     * 1, 2, 3, 4
     *
     * Output: 4
     *
     *
     * IMPORTANT:
     * The sequence can appear in any order in the original array.
     *
     * [100, 4, 200, 1, 3, 2]
     *          ↓
     *        1,2,3,4
     *
     * ============================================================
     *
     * EXAMPLE 2:
     *
     * Input:  [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
     *
     * Longest sequence:
     * 0,1,2,3,4,5,6,7,8
     *
     * Output: 9
     *
     *
     * EXAMPLE 3:
     *
     * Input:  [1, 0, 1, 2]
     *
     * Longest sequence:
     * 0,1,2
     *
     * Output: 3
     *
     *
     * ============================================================
     * APPROACH 1: BRUTE FORCE
     * ============================================================
     *
     * Idea:
     *
     * For every number, try to find the next consecutive number.
     *
     * Example:
     *
     * nums = [100, 4, 200, 1, 3, 2]
     *
     * Start with 1:
     *
     * Is 2 present? YES
     * Is 3 present? YES
     * Is 4 present? YES
     * Is 5 present? NO
     *
     * Sequence length = 4
     *
     *
     * But to check whether a number exists, we may have to scan
     * the entire array.
     *
     * Therefore the time complexity can become O(n²).
     *
     * Time:  O(n²)
     * Space: O(1) auxiliary space
     *
     *
     * ============================================================
     * APPROACH 2: BETTER APPROACH - SORTING
     * ============================================================
     *
     * Idea:
     *
     * First sort the array.
     *
     * Example:
     *
     * [100, 4, 200, 1, 3, 2]
     *
     * After sorting:
     *
     * [1, 2, 3, 4, 100, 200]
     *
     * Now consecutive numbers are easy to identify.
     *
     * 1 → 2 → 3 → 4
     *
     * Length = 4
     *
     *
     * We can skip duplicate values because duplicates should not
     * increase the length of a consecutive sequence.
     *
     * Time:  O(n log n)
     * Space: O(1) auxiliary space
     *        (depending on the sorting implementation)
     *
     *
     * ============================================================
     * APPROACH 3: OPTIMAL APPROACH - HASHSET
     * ============================================================
     *
     * The problem requires O(n) time.
     *
     * So sorting is not enough because sorting takes O(n log n).
     *
     * We use a HashSet.
     *
     * HashSet provides average O(1) lookup.
     *
     *
     * STEP 1:
     * Put every number into a HashSet.
     *
     * Example:
     *
     * nums = [100, 4, 200, 1, 3, 2]
     *
     * HashSet:
     *
     * {100, 4, 200, 1, 3, 2}
     *
     *
     * STEP 2:
     * For every number, check whether it is the START of a
     * consecutive sequence.
     *
     * How do we know whether a number is the start?
     *
     * Check whether:
     *
     * num - 1
     *
     * exists in the HashSet.
     *
     *
     * If num - 1 does NOT exist:
     *
     * num is the beginning of a sequence.
     *
     *
     * Example:
     *
     * num = 1
     *
     * Is 0 present?
     * NO
     *
     * Therefore 1 is the START.
     *
     *
     * But:
     *
     * num = 2
     *
     * Is 1 present?
     * YES
     *
     * Therefore 2 is NOT the start.
     *
     *
     * ============================================================
     *
     * STEP 3:
     * Once we find the start, keep checking:
     *
     * num + 1
     * num + 2
     * num + 3
     * ...
     *
     * until the next number is missing.
     *
     *
     * Example:
     *
     * Start = 1
     *
     * 2 exists → length = 2
     * 3 exists → length = 3
     * 4 exists → length = 4
     * 5 does not exist → stop
     *
     * Current sequence length = 4
     *
     *
     * ============================================================
     * WHY DO WE ONLY START FROM THE BEGINNING?
     * ============================================================
     *
     * This is the MOST IMPORTANT idea.
     *
     * Suppose:
     *
     * [1, 2, 3, 4]
     *
     * If we start from 1:
     *
     * 1 → 2 → 3 → 4
     *
     * We find the complete sequence.
     *
     * When we reach 2:
     *
     * 1 already exists.
     *
     * Therefore 2 is not a starting point.
     *
     * Same for 3 and 4.
     *
     * This prevents unnecessary repeated work.
     *
     *
     * ============================================================
     * TRACE
     * ============================================================
     *
     * nums = [100, 4, 200, 1, 3, 2]
     *
     * HashSet:
     * {100, 4, 200, 1, 3, 2}
     *
     *
     * num = 100
     *
     * Is 99 present?
     * NO
     *
     * So 100 is a start.
     *
     * Is 101 present?
     * NO
     *
     * length = 1
     *
     * maxLength = 1
     *
     *
     * num = 4
     *
     * Is 3 present?
     * YES
     *
     * 4 is NOT a start.
     *
     *
     * num = 200
     *
     * Is 199 present?
     * NO
     *
     * Start sequence from 200.
     *
     * 201 does not exist.
     *
     * length = 1
     *
     *
     * num = 1
     *
     * Is 0 present?
     * NO
     *
     * 1 is a START.
     *
     * 2 exists
     * 3 exists
     * 4 exists
     * 5 does not exist
     *
     * length = 4
     *
     * maxLength = 4
     *
     *
     * FINAL ANSWER:
     *
     * 4
     *
     *
     * ============================================================
     * WHY IS HASHSET OPTIMAL?
     * ============================================================
     *
     * Sorting takes:
     *
     * O(n log n)
     *
     * But the problem requires:
     *
     * O(n)
     *
     * HashSet allows us to check whether a number exists in
     * approximately O(1) average time.
     *
     * We therefore avoid sorting and directly search for
     * consecutive numbers.
     *
     * ============================================================
     *
     * TIME COMPLEXITY:
     *
     * Building HashSet:
     * O(n)
     *
     * Checking all numbers:
     * O(n) average
     *
     * Although there is a while loop, every number belonging to
     * a sequence is effectively processed as part of a sequence
     * that starts only at its beginning.
     *
     * Overall:
     *
     * O(n) average
     *
     *
     * SPACE COMPLEXITY:
     *
     * HashSet stores all n elements.
     *
     * O(n)
     *
     * ============================================================
     *
     * KEY LEARNING:
     *
     * 1. HashSet gives fast existence checking.
     *
     * 2. The important trick is to identify the START of a
     *    consecutive sequence.
     *
     * 3. A number is a starting point only when num - 1
     *    does not exist.
     *
     * 4. Once we find a starting point, keep checking num + 1.
     *
     * 5. This avoids repeatedly building the same sequence.
     *
     * 6. When a problem asks for O(n) and involves checking
     *    whether values exist, HashSet should immediately come
     *    to mind.
     * ============================================================
     */

    public static void main(String[] args) {

        int[] nums = {100, 4, 200, 1, 3, 2};

        int answer = longestConsecutive(nums);

        System.out.println("Longest Consecutive Sequence Length: " + answer);
    }

    public static int longestConsecutive(int[] nums) {

        // Create a HashSet for O(1) average lookup.
        Set<Integer> set = new HashSet<>();

        // Add all numbers to the HashSet.
        for (int num : nums) {
            set.add(num);
        }

        int maxLength = 0;

        // Check every number.
        for (int num : set) {

            /*
             * If num - 1 exists, then num is not the beginning
             * of a consecutive sequence.
             *
             * Example:
             *
             * 1, 2, 3, 4
             *
             * For 2:
             * 1 exists → 2 is not the start.
             *
             * For 3:
             * 2 exists → 3 is not the start.
             */
            if (!set.contains(num - 1)) {

                int currentNumber = num;
                int currentLength = 1;

                /*
                 * Keep moving forward while the next consecutive
                 * number exists.
                 */
                while (set.contains(currentNumber + 1)) {

                    currentNumber++;
                    currentLength++;
                }

                // Update the longest sequence found so far.
                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }
}