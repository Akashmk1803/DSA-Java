package Day25;

public class D25_01_SetMatrixZeroes {

    /*
     * ============================================================
     * PROBLEM: SET MATRIX ZEROES
     * ============================================================
     *
     * Given a matrix, if an element is 0, set its entire row and
     * entire column to 0.
     *
     * The modification must be done in-place.
     *
     *
     * Example 1:
     *
     * Input:
     *
     * 1 1 1
     * 1 0 1
     * 1 1 1
     *
     * Output:
     *
     * 1 0 1
     * 0 0 0
     * 1 0 1
     *
     *
     * Example 2:
     *
     * Input:
     *
     * 0 1 2 0
     * 3 4 5 2
     * 1 3 1 5
     *
     * Output:
     *
     * 0 0 0 0
     * 0 4 5 0
     * 0 3 1 0
     *
     *
     * ============================================================
     * BRUTE FORCE APPROACH
     * ============================================================
     *
     * For every 0, mark its complete row and column.
     *
     * We cannot immediately change everything to 0 because the
     * newly-created zeroes may be treated as original zeroes.
     *
     * Therefore, we can use a temporary marker such as -1.
     *
     * After finding all original zeroes, convert the markers to 0.
     *
     * Time Complexity:
     * O(m * n * (m + n))
     *
     * Space Complexity:
     * O(1)
     *
     *
     * ============================================================
     * BETTER APPROACH
     * ============================================================
     *
     * Use two arrays:
     *
     * row[m]
     * col[n]
     *
     * First pass:
     *
     * If matrix[i][j] == 0:
     *
     * row[i] = true
     * col[j] = true
     *
     * Second pass:
     *
     * If row[i] == true OR col[j] == true:
     *
     * matrix[i][j] = 0
     *
     * Time Complexity:
     * O(m * n)
     *
     * Space Complexity:
     * O(m + n)
     *
     *
     * ============================================================
     * OPTIMAL APPROACH
     * ============================================================
     *
     * We want O(1) extra space.
     *
     * Instead of creating separate row[] and col[] arrays,
     * we use the first row and first column of the matrix
     * as marker arrays.
     *
     *
     * Example:
     *
     *     1  1  1
     *     1  0  1
     *     1  1  1
     *     ↑
     *     first column
     *
     * First row:
     *     ↑
     *
     *
     * If matrix[i][j] is zero:
     *
     * matrix[i][0] = 0
     * matrix[0][j] = 0
     *
     * These act as markers saying:
     *
     * "This row needs to become zero."
     *
     * "This column needs to become zero."
     *
     *
     * ============================================================
     * IMPORTANT PROBLEM
     * ============================================================
     *
     * What if the FIRST ROW itself contains 0?
     *
     * Or the FIRST COLUMN itself contains 0?
     *
     * Since we are using them as markers, we cannot distinguish
     * between:
     *
     * 1. A marker created by us
     * 2. An original zero
     *
     * Therefore we separately remember:
     *
     * firstRowZero
     * firstColZero
     *
     *
     * ============================================================
     * ALGORITHM
     * ============================================================
     *
     * STEP 1:
     * Check whether the first row contains 0.
     *
     * STEP 2:
     * Check whether the first column contains 0.
     *
     * STEP 3:
     * Traverse the matrix excluding first row and first column.
     *
     * If matrix[i][j] == 0:
     *
     *     matrix[i][0] = 0
     *     matrix[0][j] = 0
     *
     * STEP 4:
     * Traverse the inner matrix again.
     *
     * If:
     *
     * matrix[i][0] == 0
     * OR
     * matrix[0][j] == 0
     *
     * then:
     *
     * matrix[i][j] = 0
     *
     * STEP 5:
     * If firstRowZero is true, make the entire first row zero.
     *
     * STEP 6:
     * If firstColZero is true, make the entire first column zero.
     *
     *
     * ============================================================
     * TRACE
     * ============================================================
     *
     * Input:
     *
     * 1 1 1
     * 1 0 1
     * 1 1 1
     *
     *
     * The zero is at:
     *
     * row = 1
     * col = 1
     *
     * Therefore mark:
     *
     * matrix[1][0] = 0
     * matrix[0][1] = 0
     *
     *
     * Matrix becomes:
     *
     * 1 0 1
     * 0 0 1
     * 1 1 1
     *
     * The first row and first column are now acting as markers.
     *
     *
     * Now check the inner cells.
     *
     * matrix[1][0] == 0
     *
     * Therefore row 1 must be zero.
     *
     * matrix[0][1] == 0
     *
     * Therefore column 1 must be zero.
     *
     * Final:
     *
     * 1 0 1
     * 0 0 0
     * 1 0 1
     *
     *
     * ============================================================
     * WHY IS THIS OPTIMAL?
     * ============================================================
     *
     * We need to inspect every matrix element.
     *
     * Therefore O(m * n) time is optimal.
     *
     * The better approach uses O(m+n) additional space.
     *
     * But we can reuse the matrix itself to store the row and
     * column information.
     *
     * Therefore auxiliary space becomes O(1).
     *
     *
     * TIME COMPLEXITY:
     *
     * O(m * n)
     *
     *
     * AUXILIARY SPACE:
     *
     * O(1)
     *
     *
     * ============================================================
     * KEY LEARNING
     * ============================================================
     *
     * 1. Do not immediately convert discovered zeroes into zeroes
     *    while scanning.
     *
     * 2. Use the first row and first column as marker storage.
     *
     * 3. Always separately remember whether the first row or first
     *    column originally contained a zero.
     *
     * 4. This is a common matrix problem where we reduce space by
     *    using the input matrix itself as storage.
     *
     * 5. O(m*n) time is optimal because every element may need to
     *    be inspected.
     * ============================================================
     */

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        setZeroes(matrix);

        System.out.println("Matrix after setting zeroes:");

        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void setZeroes(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        /*
         * These variables remember whether the first row and
         * first column originally contained a zero.
         */
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // --------------------------------------------------------
        // STEP 1: Check whether the first row contains zero.
        // --------------------------------------------------------

        for (int j = 0; j < cols; j++) {

            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // --------------------------------------------------------
        // STEP 2: Check whether the first column contains zero.
        // --------------------------------------------------------

        for (int i = 0; i < rows; i++) {

            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // --------------------------------------------------------
        // STEP 3:
        // Use first row and first column as markers.
        //
        // We start from index 1 because index 0 is being used
        // for storing marker information.
        // --------------------------------------------------------

        for (int i = 1; i < rows; i++) {

            for (int j = 1; j < cols; j++) {

                if (matrix[i][j] == 0) {

                    // Mark this row.
                    matrix[i][0] = 0;

                    // Mark this column.
                    matrix[0][j] = 0;
                }
            }
        }

        // --------------------------------------------------------
        // STEP 4:
        // Use the markers to set the inner matrix cells to zero.
        // --------------------------------------------------------

        for (int i = 1; i < rows; i++) {

            for (int j = 1; j < cols; j++) {

                if (matrix[i][0] == 0 || matrix[0][j] == 0) {

                    matrix[i][j] = 0;
                }
            }
        }

        // --------------------------------------------------------
        // STEP 5:
        // If the first row originally contained zero,
        // make the entire first row zero.
        // --------------------------------------------------------

        if (firstRowZero) {

            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }

        // --------------------------------------------------------
        // STEP 6:
        // If the first column originally contained zero,
        // make the entire first column zero.
        // --------------------------------------------------------

        if (firstColZero) {

            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}