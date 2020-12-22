from typing import List, Set, Dict
import sys

class Solution:

    def coinChange(self, coins: List[int], amount: int) -> int:
        internal = self.solve_internal(coins, amount, dict())
        if internal == float('inf'):
            return -1
        return internal

    def solve_internal(self, coins: List[int], amount: int, mem: Dict[int, int]) -> List[int]:
        if amount in mem:
            return mem[amount]

        if amount == 0:
            return 0
        if amount < 0:
            return float('inf')

        min_result = float('inf')
        for i in coins:
            local_result = self.solve_internal(coins, amount - i, mem) + 1
            min_result = min(min_result, local_result)
        mem[amount] = min_result
        return min_result


print(Solution().coinChange([2, 5], 14))
print(Solution().coinChange([2, 3, 5], 1))
print(Solution().coinChange([5, 8], 13))
print(Solution().coinChange([3, 5], 37))
print(Solution().coinChange([2, 3, 5], 7))
print(Solution().coinChange([2, 3, 5], 6))
print(Solution().coinChange([2, 3, 5], 13))
print(Solution().coinChange([2, 3, 5], 17))
