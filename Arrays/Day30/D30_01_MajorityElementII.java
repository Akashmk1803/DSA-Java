package Day30;

import java.util.ArrayList;
import java.util.List;

public class D30_01_MajorityElementII {

    /*
     * ============================================================
     * PROBLEM: MAJORITY ELEMENT II
     * ============================================================
     *
     * Given an integer array nums of size n, return all elements
     * that appear more than n/3 times.
     *
     *
     * Example 1:
     *
     * Input:
     * [1, 2, 1, 1, 3, 2]
     *
     * n = 6
     * n/3 = 2
     *
     * Frequencies:
     *
     * 1 → 3 times
     * 2 → 2 times
     * 3 → 1 time
     *
     * Only 1 appears MORE than 2 times.
     *
     * Output:
     * [1]
     *
     *
     * Example 2:
     *
     * Input:
     * [1, 2, 1, 1, 3, 2, 2]
     *
     * n = 7
     * floor(n/3) = 2
     *
     * Frequencies:
     *
     * 1 → 3 times
     * 2 → 3 times
     * 3 → 1 time
     *
     * Output:
     * [1, 2]
     *
     *
     * ============================================================
     * IMPORTANT OBSERVATION
     * ============================================================
     *
     * There can be AT MOST TWO elements that appear more than
     * n/3 times.
     *
     *
     * Suppose there were three such elements:
     *
     * A > n/3
     * B > n/3
     * C > n/3
     *
     * Then:
     *
     * A + B + C > n
     *
     * But the array only contains n elements.
     *
     * Therefore this is impossible.
     *
     *
     * So we only need:
     *
     * candidate1
     * candidate2
     *
     * count1
     * count2
     *
     *
     * ============================================================
     * BRUTE FORCE APPROACH
     * ============================================================
     *
     * For every element, count its frequency by scanning the
     * complete array.
     *
     * If frequency > n/3, add it to the answer.
     *
     * Duplicate elements must be avoided.
     *
     *
     * Time Complexity:
     * O(n²)
     *
     * Space Complexity:
     * O(1)
     *
     *
     * ============================================================
     * BETTER APPROACH - HASHMAP
     * ============================================================
     *
     * Store the frequency of every number:
     *
     * number → frequency
     *
     * Then traverse the map and select elements whose frequency
     * is greater than n/3.
     *
     *
     * Time Complexity:
     * O(n) average
     *
     * Space Complexity:
     * O(n)
     *
     *
     * ============================================================
     * OPTIMAL APPROACH - BOYER-MOORE VOTING
     * ============================================================
     *
     * Since there can be at most two valid elements, maintain
     * two candidates and their counts.
     *
     *
     * candidate1 → first possible majority element
     * count1     → its vote count
     *
     * candidate2 → second possible majority element
     * count2     → its vote count
     *
     *
     * ============================================================
     * PHASE 1: FIND TWO POSSIBLE CANDIDATES
     * ============================================================
     *
     * For every number:
     *
     * CASE 1:
     *
     * If num == candidate1:
     *
     *     count1++
     *
     *
     * CASE 2:
     *
     * Else if num == candidate2:
     *
     *     count2++
     *
     *
     * CASE 3:
     *
     * Else if count1 == 0:
     *
     *     candidate1 = num
     *     count1 = 1
     *
     *
     * CASE 4:
     *
     * Else if count2 == 0:
     *
     *     candidate2 = num
     *     count2 = 1
     *
     *
     * CASE 5:
     *
     * Otherwise:
     *
     *     count1--
     *     count2--
     *
     *
     * ============================================================
     * WHY DECREASE BOTH COUNTS?
     * ============================================================
     *
     * Suppose we have:
     *
     * candidate1 = A
     * candidate2 = B
     *
     * and we encounter another number C.
     *
     * If C is different from both A and B:
     *
     * A vote can cancel with C.
     *
     * B vote can also cancel with C.
     *
     * Therefore:
     *
     * count1--
     * count2--
     *
     * This is the cancellation principle behind Boyer-Moore.
     *
     *
     * ============================================================
     * IMPORTANT: CANDIDATES ARE NOT GUARANTEED ANSWERS
     * ============================================================
     *
     * After Phase 1, candidate1 and candidate2 are only POTENTIAL
     * answers.
     *
     * They may not actually occur more than n/3 times.
     *
     * Therefore we MUST perform a second pass.
     *
     *
     * ============================================================
     * PHASE 2: VERIFY
     * ============================================================
     *
     * Count the actual occurrences of candidate1 and candidate2.
     *
     * If:
     *
     * count1 > n/3
     *
     * add candidate1.
     *
     * If:
     *
     * count2 > n/3
     *
     * add candidate2.
     *
     *
     * ============================================================
     * TRACE
     * ============================================================
     *
     * nums = [1, 2, 1, 1, 3, 2, 2]
     *
     * n = 7
     * n/3 = 2
     *
     *
     * Start:
     *
     * candidate1 = none
     * candidate2 = none
     * count1 = 0
     * count2 = 0
     *
     *
     * num = 1
     *
     * count1 == 0
     *
     * candidate1 = 1
     * count1 = 1
     *
     *
     * num = 2
     *
     * count2 == 0
     *
     * candidate2 = 2
     * count2 = 1
     *
     *
     * num = 1
     *
     * Matches candidate1.
     *
     * count1 = 2
     *
     *
     * num = 1
     *
     * Matches candidate1.
     *
     * count1 = 3
     *
     *
     * num = 3
     *
     * Different from both candidates.
     *
     * count1--
     * count2--
     *
     * count1 = 2
     * count2 = 0
     *
     *
     * num = 2
     *
     * Matches candidate2.
     *
     * count2 = 1
     *
     *
     * num = 2
     *
     * Matches candidate2.
     *
     * count2 = 2
     *
     *
     * Potential candidates:
     *
     * 1
     * 2
     *
     *
     * ============================================================
     * VERIFICATION
     * ============================================================
     *
     * Count actual frequencies:
     *
     * 1 → 3
     * 2 → 3
     *
     * n/3 = 2
     *
     * 3 > 2 → add 1
     * 3 > 2 → add 2
     *
     * Answer:
     *
     * [1, 2]
     *
     *
     * ============================================================
     * TIME COMPLEXITY
     * ============================================================
     *
     * First pass:
     * O(n)
     *
     * Second pass:
     * O(n)
     *
     * Final:
     *
     * O(n)
     *
     *
     * ============================================================
     * SPACE COMPLEXITY
     * ============================================================
     *
     * Only a few variables are used.
     *
     * Auxiliary Space:
     *
     * O(1)
     *
     * The returned result can contain at most two elements.
     *
     *
     * ============================================================
     * WHY IS THIS OPTIMAL?
     * ============================================================
     *
     * HashMap uses O(n) additional space.
     *
     * Boyer-Moore uses only constant extra space.
     *
     * It also processes the array in linear time.
     *
     * Therefore:
     *
     * Time  = O(n)
     * Space = O(1)
     *
     *
     * ============================================================
     * KEY LEARNING
     * ============================================================
     *
     * For:
     *
     * More than n/2:
     *
     *     → 1 candidate
     *
     *
     * For:
     *
     * More than n/3:
     *
     *     → 2 candidates
     *
     *
     * General pattern:
     *
     * More than n/k:
     *
     *     → at most k - 1 candidates
     *
     *
     * Also remember:
     *
     * Boyer-Moore candidate selection
     *           ↓
     * Candidate verification
     *
     * The candidates from Phase 1 MUST be verified.
     * ============================================================
     */

    public static void main(String[] args) {

        int[] nums = {
                1, 2, 1, 1, 3, 2, 2, 3
        };

        List<Integer> result = majorityElement(nums);

        // Sort is used only because the question asks for
        // ascending order for this particular example.
        result.sort(Integer::compareTo);

        System.out.println("Majority Elements (> N/3): " + result);
    }

    public static List<Integer> majorityElement(int[] nums) {

        List<Integer> result = new ArrayList<>();

        int n = nums.length;

        // Two possible candidates.
        int candidate1 = 0;
        int candidate2 = 0;

        // Their corresponding counts.
        int count1 = 0;
        int count2 = 0;

        // ========================================================
        // PHASE 1: FIND POSSIBLE CANDIDATES
        // ========================================================

        for (int num : nums) {

            // Case 1: num matches candidate1.
            if (num == candidate1) {

                count1++;
            }

            // Case 2: num matches candidate2.
            else if (num == candidate2) {

                count2++;
            }

            // Case 3: candidate1 has no votes.
            else if (count1 == 0) {

                candidate1 = num;
                count1 = 1;
            }

            // Case 4: candidate2 has no votes.
            else if (count2 == 0) {

                candidate2 = num;
                count2 = 1;
            }

            // Case 5: num is different from both candidates.
            else {

                count1--;
                count2--;
            }
        }

        // ========================================================
        // PHASE 2: VERIFY THE CANDIDATES
        // ========================================================

        count1 = 0;
        count2 = 0;

        for (int num : nums) {

            if (num == candidate1) {
                count1++;
            }

            else if (num == candidate2) {
                count2++;
            }
        }

        // ========================================================
        // CHECK WHETHER CANDIDATES ACTUALLY OCCUR MORE THAN N/3
        // ========================================================

        if (count1 > n / 3) {

            result.add(candidate1);
        }

        if (count2 > n / 3) {

            result.add(candidate2);
        }

        return result;
    }
}