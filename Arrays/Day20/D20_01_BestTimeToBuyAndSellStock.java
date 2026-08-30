package Day20;

/*
 * ============================================================
 * Day 20 - Problem 01
 * Best Time to Buy and Sell Stock
 * ============================================================
 *
 * Problem Statement:
 * Given an array prices where prices[i] represents the stock
 * price on the ith day, find the maximum profit that can be
 * achieved by buying the stock once and selling it once.
 *
 * The stock must be bought before it is sold.
 *
 * If no profit can be achieved, return 0.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * prices = [7, 1, 5, 3, 6, 4]
 *
 * Output:
 * 5
 *
 * Explanation:
 *
 * Buy on day 2 at price 1.
 * Sell on day 5 at price 6.
 *
 * Profit:
 *
 * 6 - 1 = 5
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * prices = [7, 6, 4, 3, 1]
 *
 * Output:
 * 0
 *
 * Explanation:
 *
 * Prices keep decreasing.
 *
 * No profitable transaction is possible.
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Try every possible pair of buy and sell days.
 *
 * The buy day must always be before the sell day.
 *
 * For every pair:
 *
 *      profit = prices[j] - prices[i]
 *
 * Keep track of the maximum profit.
 *
 * Algorithm:
 *
 * 1. Choose every possible buying day i.
 *
 * 2. For every buying day, check every future selling day j.
 *
 * 3. Calculate:
 *
 *      prices[j] - prices[i]
 *
 * 4. Update the maximum profit.
 *
 * 5. Return the maximum profit.
 *
 * Time Complexity:
 * O(n²)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * Every possible buy/sell combination may need to be checked.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 *
 * Approach:
 * Traverse the array once while maintaining the minimum
 * stock price seen so far.
 *
 * For every current price, calculate the profit that could
 * be made by buying at the minimum price and selling today.
 *
 * Two variables are maintained:
 *
 * minPrice  -> minimum price seen so far
 * maxProfit -> maximum profit found so far
 *
 * ============================================================
 *
 * KEY FORMULA:
 *
 * profit = currentPrice - minPrice
 *
 * ============================================================
 *
 * ALGORITHM:
 *
 * 1. Initialize minPrice with the first price.
 *
 * 2. Initialize maxProfit = 0.
 *
 * 3. Traverse the array.
 *
 * 4. Update the minimum price:
 *
 *      minPrice = Math.min(minPrice, prices[i])
 *
 * 5. Calculate the current possible profit:
 *
 *      profit = prices[i] - minPrice
 *
 * 6. Update:
 *
 *      maxProfit = Math.max(maxProfit, profit)
 *
 * 7. Return maxProfit.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 *
 * [7, 1, 5, 3, 6, 4]
 *
 * ------------------------------------------------------------
 *
 * Initially:
 *
 * minPrice = 7
 * maxProfit = 0
 *
 * ------------------------------------------------------------
 *
 * i = 0
 * price = 7
 *
 * minPrice = 7
 *
 * profit = 7 - 7
 *        = 0
 *
 * maxProfit = 0
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * price = 1
 *
 * New minimum:
 *
 * minPrice = 1
 *
 * profit = 1 - 1
 *        = 0
 *
 * maxProfit = 0
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * price = 5
 *
 * profit = 5 - 1
 *        = 4
 *
 * maxProfit = 4
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * price = 3
 *
 * profit = 3 - 1
 *        = 2
 *
 * maxProfit remains 4.
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * price = 6
 *
 * profit = 6 - 1
 *        = 5
 *
 * maxProfit = 5
 *
 * ------------------------------------------------------------
 *
 * i = 5
 * price = 4
 *
 * profit = 4 - 1
 *        = 3
 *
 * maxProfit remains 5.
 *
 * ============================================================
 *
 * FINAL RESULT:
 *
 * Maximum Profit = 5
 *
 * Buy at price = 1
 * Sell at price = 6
 *
 * ============================================================
 *
 * DECREASING ARRAY:
 *
 * Input:
 *
 * [7, 6, 4, 3, 1]
 *
 * No price after a purchase is greater than the buying price.
 *
 * Therefore:
 *
 * maxProfit = 0
 *
 * ============================================================
 *
 * WHY IS BUYING BEFORE SELLING GUARANTEED?
 *
 * minPrice is updated only using prices encountered while
 * traversing from left to right.
 *
 * Therefore, when calculating:
 *
 *      currentPrice - minPrice
 *
 * minPrice always represents a price from the current day
 * or an earlier day.
 *
 * This ensures the stock is bought before it is sold.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * The array is traversed exactly once.
 *
 * ============================================================
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * Only minPrice, profit and maxProfit are used.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - One-pass array traversal
 * - Maintaining minimum value
 * - Maximum profit
 * - Buy before sell constraint
 * - Greedy approach
 * - O(n) time
 * - O(1) space
 *
 * ============================================================
 */

public class D20_01_BestTimeToBuyAndSellStock {

    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};

        int maxProfit = findMaxProfit(prices);

        System.out.println(
                "Maximum Profit: " + maxProfit
        );
    }

    public static int findMaxProfit(int[] prices) {

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            minPrice = Math.min(minPrice, prices[i]);

            int profit = prices[i] - minPrice;

            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }
}