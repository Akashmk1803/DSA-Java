package Day27;

import java.util.ArrayList;
import java.util.List;

public class D27_01_SpiralMatrix {

    /*
     * ============================================================
     * PROBLEM: SPIRAL MATRIX
     * ============================================================
     *
     * Given an m x n matrix, return all elements of the matrix
     * in clockwise spiral order.
     *
     *
     * Example 1:
     *
     * Input:
     *
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     * Spiral order:
     *
     * 1 → 2 → 3 → 6 → 9 → 8 → 7 → 4 → 5
     *
     * Output:
     *
     * [1, 2, 3, 6, 9, 8, 7, 4, 5]
     *
     *
     * Example 2:
     *
     * Input:
     *
     * 1  2  3  4
     * 5  6  7  8
     * 9 10 11 12
     *
     * Spiral order:
     *
     * 1 → 2 → 3 → 4
     *                 ↓
     * 8 → 12
     * ↑
     * 5 ← 9 ← 10 ← 11
     *       6 → 7
     *
     * Output:
     *
     * [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
     *
     *
     * ============================================================
     * BRUTE FORCE APPROACH
     * ============================================================
     *
     * One approach is to use an additional visited[][] matrix.
     *
     * visited[i][j] tells us whether an element has already
     * been included in the spiral traversal.
     *
     * We can move in four directions:
     *
     * Right → Down → Left → Up
     *
     * Whenever we reach a boundary or a visited cell, we change
     * direction.
     *
     * This works, but it requires an additional matrix.
     *
     *
     * Time Complexity:
     * O(m * n)
     *
     * Space Complexity:
     * O(m * n)
     *
     *
     * ============================================================
     * OPTIMAL APPROACH
     * ============================================================
     *
     * We do not need a visited matrix.
     *
     * Instead, we maintain four boundaries:
     *
     * top
     * bottom
     * left
     * right
     *
     *
     * Initially:
     *
     * top = 0
     * bottom = rows - 1
     * left = 0
     * right = cols - 1
     *
     *
     * We then traverse the matrix in four directions.
     *
     *
     * ------------------------------------------------------------
     * STEP 1: LEFT → RIGHT
     * ------------------------------------------------------------
     *
     * Traverse the top row.
     *
     * Example:
     *
     * 1 2 3
     * -------
     *
     * Add:
     *
     * 1, 2, 3
     *
     * After processing the top row:
     *
     * top++
     *
     *
     * ------------------------------------------------------------
     * STEP 2: TOP → BOTTOM
     * ------------------------------------------------------------
     *
     * Traverse the right column.
     *
     * Example:
     *
     *       ↓
     *       6
     *       9
     *
     * Add:
     *
     * 6, 9
     *
     * After processing the right column:
     *
     * right--
     *
     *
     * ------------------------------------------------------------
     * STEP 3: RIGHT → LEFT
     * ------------------------------------------------------------
     *
     * Traverse the bottom row.
     *
     * Example:
     *
     * 8 ← 7
     *
     * Add:
     *
     * 8, 7
     *
     * After processing the bottom row:
     *
     * bottom--
     *
     *
     * ------------------------------------------------------------
     * STEP 4: BOTTOM → TOP
     * ------------------------------------------------------------
     *
     * Traverse the left column from bottom to top.
     *
     * Example:
     *
     * ↑
     * 4
     *
     * Add:
     *
     * 4
     *
     * After processing the left column:
     *
     * left++
     *
     *
     * ============================================================
     * TRACE
     * ============================================================
     *
     * Matrix:
     *
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     *
     * Initial boundaries:
     *
     * top = 0
     * bottom = 2
     * left = 0
     * right = 2
     *
     *
     * STEP 1:
     * Traverse top row:
     *
     * 1 2 3
     *
     * Result:
     * [1, 2, 3]
     *
     * top = 1
     *
     *
     * STEP 2:
     * Traverse right column:
     *
     * 6
     * 9
     *
     * Result:
     * [1, 2, 3, 6, 9]
     *
     * right = 1
     *
     *
     * STEP 3:
     * Traverse bottom row:
     *
     * 8 7
     *
     * Result:
     * [1, 2, 3, 6, 9, 8, 7]
     *
     * bottom = 1
     *
     *
     * STEP 4:
     * Traverse left column:
     *
     * 4
     *
     * Result:
     * [1, 2, 3, 6, 9, 8, 7, 4]
     *
     * left = 1
     *
     *
     * Remaining matrix:
     *
     * 5
     *
     * STEP 5:
     * Traverse top row:
     *
     * 5
     *
     * Result:
     * [1, 2, 3, 6, 9, 8, 7, 4, 5]
     *
     *
     * Final answer:
     *
     * [1, 2, 3, 6, 9, 8, 7, 4, 5]
     *
     *
     * ============================================================
     * WHY DO WE NEED THE EXTRA IF CONDITIONS?
     * ============================================================
     *
     * Consider a matrix with only one row:
     *
     * 1 2 3 4
     *
     * After processing the top row:
     *
     * top becomes greater than bottom.
     *
     * Therefore we must NOT process the bottom row again.
     *
     * That's why:
     *
     * if (top <= bottom)
     *
     * is required.
     *
     *
     * Similarly, if only one column remains, we must not traverse
     * that column twice.
     *
     * Therefore:
     *
     * if (left <= right)
     *
     * is required.
     *
     *
     * ============================================================
     * TIME COMPLEXITY
     * ============================================================
     *
     * Every matrix element is visited exactly once.
     *
     * Therefore:
     *
     * O(m * n)
     *
     *
     * ============================================================
     * SPACE COMPLEXITY
     * ============================================================
     *
     * The returned ArrayList contains all m*n elements.
     *
     * Therefore output space:
     *
     * O(m * n)
     *
     * Auxiliary space excluding the returned result:
     *
     * O(1)
     *
     *
     * ============================================================
     * WHY IS THIS OPTIMAL?
     * ============================================================
     *
     * We must visit every element at least once because the
     * answer contains every matrix element.
     *
     * Therefore O(m*n) time is optimal.
     *
     * We do not create a visited matrix or another matrix.
     *
     * Therefore auxiliary space is O(1), excluding the output.
     *
     *
     * ============================================================
     * KEY LEARNING
     * ============================================================
     *
     * For spiral matrix problems, remember:
     *
     *       TOP
     *        ↓
     * LEFT → → → RIGHT
     *        ↓
     *      BOTTOM
     *
     *
     * Four boundaries:
     *
     * top
     * bottom
     * left
     * right
     *
     *
     * Four movements:
     *
     * 1. Left → Right
     * 2. Top → Bottom
     * 3. Right → Left
     * 4. Bottom → Top
     *
     *
     * After each movement, shrink the corresponding boundary.
     *
     * ============================================================
     */

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        List<Integer> result = spiralOrder(matrix);

        System.out.println("Spiral Order: " + result);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Four boundaries.
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;

        // Continue while there is an unvisited region.
        while (top <= bottom && left <= right) {

            // ====================================================
            // STEP 1: LEFT → RIGHT
            // ====================================================

            for (int j = left; j <= right; j++) {

                result.add(matrix[top][j]);
            }

            // Top row has been processed.
            top++;

            // ====================================================
            // STEP 2: TOP → BOTTOM
            // ====================================================

            for (int i = top; i <= bottom; i++) {

                result.add(matrix[i][right]);
            }

            // Right column has been processed.
            right--;

            // ====================================================
            // STEP 3: RIGHT → LEFT
            // ====================================================

            /*
             * Check whether a row still exists.
             *
             * This prevents processing the same row again when
             * the matrix has only one remaining row.
             */
            if (top <= bottom) {

                for (int j = right; j >= left; j--) {

                    result.add(matrix[bottom][j]);
                }

                // Bottom row has been processed.
                bottom--;
            }

            // ====================================================
            // STEP 4: BOTTOM → TOP
            // ====================================================

            /*
             * Check whether a column still exists.
             *
             * This prevents processing the same column twice when
             * the matrix has only one remaining column.
             */
            if (left <= right) {

                for (int i = bottom; i >= top; i--) {

                    result.add(matrix[i][left]);
                }

                // Left column has been processed.
                left++;
            }
        }

        return result;
    }
}