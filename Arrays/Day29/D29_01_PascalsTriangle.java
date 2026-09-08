package Day29;

import java.util.ArrayList;
import java.util.List;

public class D29_01_PascalsTriangle {

    /*
     * ============================================================
     * PROBLEM: PASCAL'S TRIANGLE
     * ============================================================
     *
     * Given an integer numRows, return the first numRows rows of
     * Pascal's Triangle.
     *
     *
     * Pascal's Triangle:
     *
     *              1
     *             1 1
     *            1 2 1
     *           1 3 3 1
     *          1 4 6 4 1
     *
     *
     * Every row starts and ends with 1.
     *
     * Every middle element is the sum of the two elements directly
     * above it.
     *
     *
     * Example:
     *
     * Input:
     *
     * numRows = 5
     *
     * Output:
     *
     * [
     *   [1],
     *   [1, 1],
     *   [1, 2, 1],
     *   [1, 3, 3, 1],
     *   [1, 4, 6, 4, 1]
     * ]
     *
     *
     * ============================================================
     * APPROACH 1: FACTORIAL / BINOMIAL COEFFICIENT
     * ============================================================
     *
     * Every element can be calculated using:
     *
     * C(n, r) = n! / (r! * (n-r)!)
     *
     * Example:
     *
     * C(4, 2)
     *
     * = 4! / (2! * 2!)
     * = 6
     *
     * This approach repeatedly calculates factorials and is not
     * efficient.
     *
     *
     * Time Complexity:
     * More expensive due to repeated factorial calculations.
     *
     *
     * ============================================================
     * APPROACH 2: BUILD FROM PREVIOUS ROW
     * ============================================================
     *
     * This is the preferred approach for generating the complete
     * Pascal's Triangle.
     *
     *
     * For every row:
     *
     * 1. Add 1 at the beginning.
     *
     * 2. Calculate the middle elements using the previous row.
     *
     * 3. Add 1 at the end.
     *
     *
     * Example:
     *
     * Previous row:
     *
     * 1 3 3 1
     *
     * New row:
     *
     * 1 ? ? ? 1
     *
     *
     * Middle values:
     *
     * 1 + 3 = 4
     * 3 + 3 = 6
     * 3 + 1 = 4
     *
     *
     * New row:
     *
     * 1 4 6 4 1
     *
     *
     * ============================================================
     * ALGORITHM
     * ============================================================
     *
     * Start with an empty result.
     *
     * For row = 0 to numRows - 1:
     *
     *     Create a new row.
     *
     *     Add 1.
     *
     *     For every middle position:
     *
     *         current value =
     *         previousRow[j - 1] + previousRow[j]
     *
     *     Add 1.
     *
     *     Add the current row to result.
     *
     *
     * ============================================================
     * TRACE
     * ============================================================
     *
     * numRows = 5
     *
     *
     * ROW 1:
     *
     * [1]
     *
     *
     * ROW 2:
     *
     * [1, 1]
     *
     *
     * ROW 3:
     *
     * Previous:
     * [1, 1]
     *
     * Middle:
     * 1 + 1 = 2
     *
     * New:
     * [1, 2, 1]
     *
     *
     * ROW 4:
     *
     * Previous:
     * [1, 2, 1]
     *
     * Middle:
     *
     * 1 + 2 = 3
     * 2 + 1 = 3
     *
     * New:
     * [1, 3, 3, 1]
     *
     *
     * ROW 5:
     *
     * Previous:
     * [1, 3, 3, 1]
     *
     * Middle:
     *
     * 1 + 3 = 4
     * 3 + 3 = 6
     * 3 + 1 = 4
     *
     * New:
     * [1, 4, 6, 4, 1]
     *
     *
     * FINAL:
     *
     * [
     *   [1],
     *   [1, 1],
     *   [1, 2, 1],
     *   [1, 3, 3, 1],
     *   [1, 4, 6, 4, 1]
     * ]
     *
     *
     * ============================================================
     * TIME COMPLEXITY
     * ============================================================
     *
     * Number of elements generated:
     *
     * 1 + 2 + 3 + ... + numRows
     *
     * = numRows * (numRows + 1) / 2
     *
     * Therefore:
     *
     * O(numRows²)
     *
     *
     * ============================================================
     * SPACE COMPLEXITY
     * ============================================================
     *
     * The result itself contains O(numRows²) elements.
     *
     * Therefore:
     *
     * O(numRows²)
     *
     * including the output.
     *
     * Additional working space apart from the result is:
     *
     * O(numRows)
     *
     *
     * ============================================================
     * PASCAL'S TRIANGLE I
     * ============================================================
     *
     * Sometimes the problem asks for the value at a particular
     * row and column.
     *
     * Example:
     *
     * r = 4
     * c = 2
     *
     * Pascal's Triangle:
     *
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     *
     * Answer:
     *
     * 3
     *
     *
     * Because:
     *
     * value = C(r - 1, c - 1)
     *
     *
     * ============================================================
     * N-TH ROW
     * ============================================================
     *
     * To generate a single row, we can use:
     *
     * C(n, r) = C(n, r - 1) * (n - r + 1) / r
     *
     * This avoids constructing all previous rows.
     *
     * ============================================================
     * KEY LEARNING
     * ============================================================
     *
     * Pascal's Triangle has three important patterns:
     *
     * 1. First and last elements of every row are 1.
     *
     * 2. Middle element:
     *
     *    previousRow[j - 1] + previousRow[j]
     *
     * 3. Every element is a binomial coefficient:
     *
     *    C(n, r)
     *
     *
     * For generating the complete triangle:
     *
     * BUILD FROM THE PREVIOUS ROW.
     *
     * For generating one particular row:
     *
     * USE THE BINOMIAL COEFFICIENT FORMULA.
     * ============================================================
     */

    public static void main(String[] args) {

        int numRows = 5;

        List<List<Integer>> triangle = generate(numRows);

        System.out.println("Pascal's Triangle:");

        for (List<Integer> row : triangle) {
            System.out.println(row);
        }
    }

    /*
     * Generates the first numRows of Pascal's Triangle.
     */
    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> triangle = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {

            List<Integer> currentRow = new ArrayList<>();

            // Every row starts with 1.
            currentRow.add(1);

            /*
             * Calculate middle elements using the previous row.
             *
             * Example:
             *
             * Previous row = [1, 3, 3, 1]
             *
             * Current middle values:
             *
             * 1 + 3 = 4
             * 3 + 3 = 6
             * 3 + 1 = 4
             */
            if (row > 0) {

                List<Integer> previousRow = triangle.get(row - 1);

                for (int j = 1; j < row; j++) {

                    int value = previousRow.get(j - 1)
                            + previousRow.get(j);

                    currentRow.add(value);
                }
            }

            // Every row ends with 1.
            if (row > 0) {
                currentRow.add(1);
            }

            triangle.add(currentRow);
        }

        return triangle;
    }
}