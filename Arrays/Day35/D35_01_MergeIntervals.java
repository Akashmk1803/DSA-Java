package Day35;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Day 35: Merge Intervals
 *
 * Problem Statement:
 * ------------------
 * Given an array of intervals where:
 *
 *      intervals[i] = [start, end]
 *
 * merge all overlapping intervals and return the resulting
 * non-overlapping intervals.
 *
 *
 * Example:
 *
 * Input:
 *
 * [[1,3], [2,6], [8,10], [15,18]]
 *
 * Output:
 *
 * [[1,6], [8,10], [15,18]]
 *
 *
 * Why?
 *
 * [1,3] and [2,6] overlap.
 *
 * They can be merged into:
 *
 * [1,6]
 *
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE
 * ------------------------------------------------------------
 *
 * We can repeatedly compare intervals and merge overlapping
 * intervals.
 *
 * After merging one pair, we may need to compare the newly
 * created interval with other intervals again.
 *
 * This can lead to many repeated comparisons.
 *
 * Time Complexity:
 *
 * O(n^2) in the worst case.
 *
 * Space Complexity:
 *
 * O(n) for the result.
 *
 *
 * ------------------------------------------------------------
 * APPROACH 2: OPTIMAL APPROACH
 * ------------------------------------------------------------
 *
 * Sort the intervals according to their starting point.
 *
 * Example:
 *
 * Input:
 *
 * [[1,5], [3,6], [8,10], [15,18]]
 *
 * Already sorted.
 *
 *
 * Suppose:
 *
 * current = [1,5]
 * next    = [3,6]
 *
 * Since:
 *
 * currentEnd >= nextStart
 *
 *      5 >= 3
 *
 * they overlap.
 *
 * Merge:
 *
 * start = 1
 * end   = max(5,6) = 6
 *
 * New current interval:
 *
 * [1,6]
 *
 *
 * ------------------------------------------------------------
 * OVERLAP CONDITION
 * ------------------------------------------------------------
 *
 * Two intervals:
 *
 * [start1, end1]
 * [start2, end2]
 *
 * overlap when:
 *
 *      end1 >= start2
 *
 * because the intervals are sorted by start.
 *
 *
 * Example:
 *
 * [1,4] and [4,5]
 *
 *      4 >= 4
 *
 * Therefore they overlap.
 *
 * Result:
 *
 * [1,5]
 *
 *
 * ------------------------------------------------------------
 * ALGORITHM
 * ------------------------------------------------------------
 *
 * 1. Sort intervals by their starting value.
 *
 * 2. Create an empty result list.
 *
 * 3. Start with the first interval.
 *
 * 4. For every next interval:
 *
 *      If current interval overlaps with it:
 *
 *          update current end:
 *
 *          currentEnd = max(currentEnd, nextEnd)
 *
 *      Otherwise:
 *
 *          add current interval to result
 *          start a new current interval.
 *
 * 5. Add the final current interval.
 *
 * 6. Return result.
 *
 *
 * ------------------------------------------------------------
 * WHY IS THIS OPTIMAL?
 * ------------------------------------------------------------
 *
 * Sorting puts intervals in increasing order of their
 * starting points.
 *
 * Therefore, once we process an interval, we never need to
 * go backward and compare it with an earlier interval.
 *
 * We simply scan from left to right.
 *
 *
 * Sorting:
 *
 * O(n log n)
 *
 * Single traversal:
 *
 * O(n)
 *
 * Therefore:
 *
 * Overall Time Complexity:
 *
 * O(n log n)
 *
 *
 * Space Complexity:
 *
 * O(n) for the output.
 *
 * The auxiliary space apart from the output depends on the
 * sorting implementation.
 *
 *
 * ------------------------------------------------------------
 * KEY LEARNING
 * ------------------------------------------------------------
 *
 * For interval problems:
 *
 *      SORT FIRST
 *
 * Then ask:
 *
 *      "Does the current interval overlap with the next?"
 *
 *
 * If:
 *
 *      currentEnd >= nextStart
 *
 * merge.
 *
 * Otherwise:
 *
 *      store current
 *      move to next.
 *
 *
 * This pattern is extremely important for interval problems.
 */
public class D35_01_MergeIntervals {

    public static void main(String[] args) {

        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        int[][] result = merge(intervals);

        System.out.println("Merged intervals:");

        for (int[] interval : result) {
            System.out.println(
                    "[" + interval[0] + ", " + interval[1] + "]"
            );
        }
    }


    /*
     * Optimal Approach:
     *
     * Sort + Linear Scan
     *
     * Time Complexity:
     * O(n log n)
     *
     * Space Complexity:
     * O(n) for the result.
     */
    public static int[][] merge(int[][] intervals) {

        /*
         * If there are no intervals, return an empty array.
         */
        if (intervals.length == 0) {
            return new int[0][0];
        }

        /*
         * Step 1:
         * Sort intervals by their starting point.
         *
         * Example:
         *
         * [[5,7], [1,3], [4,6], [8,10]]
         *
         * becomes:
         *
         * [[1,3], [4,6], [5,7], [8,10]]
         */
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));


        /*
         * Result list stores the merged intervals.
         */
        List<int[]> result = new ArrayList<>();


        /*
         * Start with the first interval.
         */
        int currentStart = intervals[0][0];
        int currentEnd = intervals[0][1];


        /*
         * Step 2:
         * Traverse the remaining intervals.
         */
        for (int i = 1; i < intervals.length; i++) {

            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];


            /*
             * Check whether current and next interval overlap.
             *
             * Example:
             *
             * current = [1,5]
             * next    = [3,6]
             *
             * currentEnd = 5
             * nextStart  = 3
             *
             * 5 >= 3
             *
             * Therefore they overlap.
             */
            if (currentEnd >= nextStart) {

                /*
                 * Merge the intervals.
                 *
                 * The start remains currentStart because
                 * the intervals are sorted by start.
                 *
                 * The end should be the larger of the two ends.
                 */
                currentEnd = Math.max(currentEnd, nextEnd);

            } else {

                /*
                 * No overlap.
                 *
                 * Therefore current interval is complete.
                 *
                 * Add it to the result.
                 */
                result.add(new int[]{
                        currentStart,
                        currentEnd
                });


                /*
                 * Start tracking the next interval.
                 */
                currentStart = nextStart;
                currentEnd = nextEnd;
            }
        }


        /*
         * The final current interval has not been added yet.
         *
         * Add it now.
         */
        result.add(new int[]{
                currentStart,
                currentEnd
        });


        /*
         * Convert List<int[]> into int[][].
         */
        return result.toArray(new int[result.size()][]);
    }
}


/*
 * ------------------------------------------------------------
 * TRACE
 * ------------------------------------------------------------
 *
 * Input:
 *
 * [[1,3], [2,6], [8,10], [15,18]]
 *
 *
 * Step 1: Sort
 *
 * [[1,3], [2,6], [8,10], [15,18]]
 *
 *
 * Start:
 *
 * current = [1,3]
 *
 *
 * ------------------------------------------------------------
 *
 * Compare:
 *
 * current = [1,3]
 * next    = [2,6]
 *
 * Check:
 *
 * 3 >= 2
 *
 * TRUE
 *
 * They overlap.
 *
 * Merge:
 *
 * [1, max(3,6)]
 *
 * = [1,6]
 *
 *
 * current = [1,6]
 *
 *
 * ------------------------------------------------------------
 *
 * Compare:
 *
 * current = [1,6]
 * next    = [8,10]
 *
 * Check:
 *
 * 6 >= 8
 *
 * FALSE
 *
 * No overlap.
 *
 * Add:
 *
 * [1,6]
 *
 * Start new current:
 *
 * [8,10]
 *
 *
 * ------------------------------------------------------------
 *
 * Compare:
 *
 * current = [8,10]
 * next    = [15,18]
 *
 * Check:
 *
 * 10 >= 15
 *
 * FALSE
 *
 * No overlap.
 *
 * Add:
 *
 * [8,10]
 *
 * Start new current:
 *
 * [15,18]
 *
 *
 * ------------------------------------------------------------
 *
 * End of loop.
 *
 * Add final interval:
 *
 * [15,18]
 *
 *
 * FINAL RESULT:
 *
 * [[1,6], [8,10], [15,18]]
 *
 *
 * ------------------------------------------------------------
 * SECOND IMPORTANT EXAMPLE
 * ------------------------------------------------------------
 *
 * Input:
 *
 * [[1,4], [4,5]]
 *
 * Check:
 *
 * 4 >= 4
 *
 * TRUE
 *
 * Therefore they overlap.
 *
 * Merge:
 *
 * [1,5]
 *
 *
 * Final:
 *
 * [[1,5]]
 *
 *
 * ------------------------------------------------------------
 * QUICK MEMORY TRICK
 * ------------------------------------------------------------
 *
 * INTERVAL PROBLEMS:
 *
 *          SORT
 *           ↓
 *      TAKE CURRENT
 *           ↓
 *   DOES IT OVERLAP?
 *       ↙       ↘
 *     YES        NO
 *      ↓          ↓
 *    MERGE     STORE CURRENT
 *      ↓          ↓
 *   CONTINUE   START NEW
 *
 *
 * Main condition:
 *
 *      currentEnd >= nextStart
 *
 * means:
 *
 *      OVERLAP → MERGE
 *
 * Otherwise:
 *
 *      NO OVERLAP → STORE
 */