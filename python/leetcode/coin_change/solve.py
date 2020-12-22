from typing import List, Set

class Solution:

    def coinChange(self, coins: List[int], amount: int) -> int:
        return self.solve_internal(coins, amount, [], {-1})

    def solve_internal(self, coins: List[int], amount: int, result: int, mem: Set[int]) -> List[int]:
        if amount in mem:
            return []

        if amount == 0:
            return result
        if amount < 0:
            return []

        for i in coins:
            result.append(i)
            if self.solve_internal(coins, amount - i, result, mem):
                return result
            mem.add(amount - i)
            result.pop()
        return []


print(Solution().coinChange([2, 5], 14))
print(Solution().coinChange([2, 3, 5], 1))
print(Solution().coinChange([5, 8], 13))
print(Solution().coinChange([3, 5], 37))
print(Solution().coinChange([2, 3, 5], 7))
print(Solution().coinChange([2, 3, 5], 6))
print(Solution().coinChange([2, 3, 5], 13))
print(Solution().coinChange([2, 3, 5], 17))
