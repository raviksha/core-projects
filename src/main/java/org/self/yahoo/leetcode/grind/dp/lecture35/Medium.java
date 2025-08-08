package org.self.yahoo.leetcode.grind.dp.lecture35;

public class Medium {
    public static void main(String[] args) {
        System.out.println("DP 35. Best Time to Buy and Sell Stock | DP on Stocks");
        int [] stock = {7, 1, 5, 3, 6, 4};

        /*
            Time complexity: O(n) : Single pass over the stocks[]

            Space complexity: O(1): No extra auxiliary space required
         */
        int profit = testBestTimeToBuySellStocks(stock);
        System.out.println("testBestTimeToBuySellStocks: " + profit);
    }

    private static int testBestTimeToBuySellStocks(int[] stock) {
        int lastMin = stock[0];
        int maxProfit = 0;

        for (int i = 1; i < stock.length; i++) {
            int profit = stock[i] - lastMin;
            maxProfit = Math.max(profit, maxProfit);
            lastMin = Math.min(lastMin, stock[i]);
        }

        return maxProfit;
    }
}
