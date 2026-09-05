package Day26;

public class D26_01_RotateImage {

    /*
     * ============================================================
     * PROBLEM: ROTATE IMAGE BY 90 DEGREES CLOCKWISE
     * ============================================================
     *
     * Given an N x N matrix, rotate the matrix by 90 degrees
     * clockwise.
     *
     * The rotation must be done IN-PLACE.
     *
     * This means:
     *
     * - We must modify the original matrix.
     * - We must NOT create another 2D matrix.
     *
     *
     * Example:
     *
     * Input:
     *
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     * Output:
     *
     * 7 4 1
     * 8 5 2
     * 9 6 3
     *
     *
     * ============================================================
     * BRUTE FORCE APPROACH
     * ============================================================
     *
     * Create another N x N matrix.
     *
     * For every element:
     *
     * matrix[i][j]
     *
     * its rotated position is:
     *
     * result[j][n - 1 - i]
     *
     *
     * Example:
     *
     * matrix[0][0] = 1
     *
     * New position:
     *
     * result[0][2] = 1
     *
     *
     * This approach is easy to understand, but it uses O(N²)
     * extra space.
     *
     *
     * Time Complexity:
     * O(N²)
     *
     * Space Complexity:
     * O(N²)
     *
     *
     * ============================================================
     * OPTIMAL APPROACH
     * ============================================================
     *
     * We can rotate the matrix in-place using two steps:
     *
     * STEP 1: TRANSPOSE THE MATRIX
     * STEP 2: REVERSE EVERY ROW
     *
     *
     * ============================================================
     * STEP 1: TRANSPOSE
     * ============================================================
     *
     * Transpose means rows become columns.
     *
     * Before transpose:
     *
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     *
     * After transpose:
     *
     * 1 4 7
     * 2 5 8
     * 3 6 9
     *
     *
     * We achieve this by swapping:
     *
     * matrix[i][j]
     *
     * with
     *
     * matrix[j][i]
     *
     *
     * IMPORTANT:
     *
     * We only traverse the upper triangle.
     *
     * Therefore:
     *
     * j = i + 1
     *
     * This prevents swapping the same elements twice.
     *
     *
     * ============================================================
     * STEP 2: REVERSE EVERY ROW
     * ============================================================
     *
     * After transpose:
     *
     * 1 4 7
     * 2 5 8
     * 3 6 9
     *
     *
     * Reverse each row:
     *
     * 7 4 1
     * 8 5 2
     * 9 6 3
     *
     *
     * This is exactly the matrix rotated 90 degrees clockwise.
     *
     *
     * ============================================================
     * TRACE
     * ============================================================
     *
     * Original:
     *
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     *
     * -------------------------
     * STEP 1: TRANSPOSE
     * -------------------------
     *
     * Swap 2 and 4:
     *
     * 1 4 3
     * 2 5 6
     * 7 8 9
     *
     *
     * Swap 3 and 7:
     *
     * 1 4 7
     * 2 5 6
     * 3 8 9
     *
     *
     * Swap 6 and 8:
     *
     * 1 4 7
     * 2 5 8
     * 3 6 9
     *
     *
     * Transposed matrix:
     *
     * 1 4 7
     * 2 5 8
     * 3 6 9
     *
     *
     * -------------------------
     * STEP 2: REVERSE EACH ROW
     * -------------------------
     *
     * Row 1:
     *
     * 1 4 7
     *
     * becomes:
     *
     * 7 4 1
     *
     *
     * Row 2:
     *
     * 2 5 8
     *
     * becomes:
     *
     * 8 5 2
     *
     *
     * Row 3:
     *
     * 3 6 9
     *
     * becomes:
     *
     * 9 6 3
     *
     *
     * Final:
     *
     * 7 4 1
     * 8 5 2
     * 9 6 3
     *
     *
     * ============================================================
     * WHY DOES TRANSPOSE + REVERSE WORK?
     * ============================================================
     *
     * A 90-degree clockwise rotation changes:
     *
     * (i, j)
     *
     * into:
     *
     * (j, n - 1 - i)
     *
     *
     * Transpose first changes:
     *
     * (i, j)
     *
     * into:
     *
     * (j, i)
     *
     *
     * Reversing the row changes the column position:
     *
     * i
     *
     * into:
     *
     * n - 1 - i
     *
     *
     * Therefore:
     *
     * (i, j)
     *
     * becomes:
     *
     * (j, n - 1 - i)
     *
     *
     * which is exactly a 90-degree clockwise rotation.
     *
     *
     * ============================================================
     * TIME COMPLEXITY
     * ============================================================
     *
     * Transpose:
     *
     * O(N²)
     *
     * Reverse all rows:
     *
     * O(N²)
     *
     * Overall:
     *
     * O(N²)
     *
     *
     * ============================================================
     * SPACE COMPLEXITY
     * ============================================================
     *
     * No additional matrix is created.
     *
     * Only a few variables are used for swapping.
     *
     * Auxiliary Space:
     *
     * O(1)
     *
     *
     * ============================================================
     * WHY IS THIS OPTIMAL?
     * ============================================================
     *
     * Every element of the matrix may need to be processed.
     *
     * Therefore O(N²) time is necessary.
     *
     * We also don't create another matrix.
     *
     * Therefore auxiliary space is O(1).
     *
     * This satisfies the in-place requirement.
     *
     *
     * ============================================================
     * KEY LEARNING
     * ============================================================
     *
     * Remember this pattern:
     *
     * 90° CLOCKWISE ROTATION
     *
     *       ↓
     *
     * TRANSPOSE
     *       ↓
     * REVERSE EVERY ROW
     *
     *
     * For 90° ANTI-CLOCKWISE:
     *
     * TRANSPOSE
     *       ↓
     * REVERSE EVERY COLUMN
     *
     *
     * This is a very important matrix pattern for DSA.
     * ============================================================
     */

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        rotate(matrix);

        System.out.println("Matrix after 90 degree clockwise rotation:");

        printMatrix(matrix);
    }

    public static void rotate(int[][] matrix) {

        int n = matrix.length;

        // ========================================================
        // STEP 1: TRANSPOSE THE MATRIX
        // ========================================================
        //
        // We swap:
        //
        // matrix[i][j]
        // with
        // matrix[j][i]
        //
        // We start j from i + 1 so that we don't swap the same
        // pair twice.
        // ========================================================

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int temp = matrix[i][j];

                matrix[i][j] = matrix[j][i];

                matrix[j][i] = temp;
            }
        }

        // ========================================================
        // STEP 2: REVERSE EVERY ROW
        // ========================================================

        for (int i = 0; i < n; i++) {

            int left = 0;
            int right = n - 1;

            while (left < right) {

                int temp = matrix[i][left];

                matrix[i][left] = matrix[i][right];

                matrix[i][right] = temp;

                left++;
                right--;
            }
        }
    }

    // Method to print the matrix.
    public static void printMatrix(int[][] matrix) {

        for (int[] row : matrix) {

            for (int value : row) {

                System.out.print(value + " ");
            }

            System.out.println();
        }
    }
}