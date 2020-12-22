from typing import List, Set, Dict
import sys

class Solution:

    def coinChange(self, coins: List[int], amount: int) -> int:
        if amount == 0:
            return 0
        coins = [coin for coin in coins if coin <= amount]
        if len(coins) == 0:
            return -1

        start = min(coins)
        max_coin = max(coins)
        print(1)
        solutions = [float('inf')] * (max_coin + 1)
        solutions[0] = 0

        for i in range(start, amount + 1):
            local_min = float('inf')
            for coin in coins:
                local_min = min(local_min, solutions[(i - coin) % max_coin] + 1)
            solutions[i % max_coin] = local_min

        if solutions[amount % max_coin] == float('inf'):
            return -1
        return solutions[amount % max_coin]


print(Solution().coinChange([1, 2147483647], 3))
print(Solution().coinChange([2], 3))
print(Solution().coinChange([2, 3, 5], 1))
print(Solution().coinChange([2, 5], 14))
print(Solution().coinChange([5, 8], 13))
print(Solution().coinChange([3, 5], 37))
print(Solution().coinChange([2, 3, 5], 7))
print(Solution().coinChange([2, 3, 5], 6))
print(Solution().coinChange([2, 3, 5], 13))
print(Solution().coinChange([2, 3, 5], 17))
