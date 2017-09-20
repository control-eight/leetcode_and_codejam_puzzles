package com.my.can_i_win;

/**
 * Created by alex.bykovsky on 9/12/17.
 */
public class Solution {

	public static void main(String[] args) {
		assertEquals(false, new Solution().canIWin(5, 6));
		assertEquals(true, new Solution().canIWin(5, 7));
		assertEquals(true, new Solution().canIWin(5, 8));
		assertEquals(true, new Solution().canIWin(5, 9));
		assertEquals(true, new Solution().canIWin(5, 9));
		assertEquals(false, new Solution().canIWin(5, 11));
		assertEquals(true, new Solution().canIWin(5, 12));
		assertEquals(false, new Solution().canIWin(10, 40));
		assertEquals(false, new Solution().canIWin(10, 11));
		assertEquals(true, new Solution().canIWin(18, 79));
	}

	private static void assertEquals(boolean expected, boolean actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		if(desiredTotal == 0) return true;
		if(maxChoosableInteger == 0) return false;
		if(maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) return false;

		return firstPlayerMove(0, maxChoosableInteger, desiredTotal, new Boolean[1 << maxChoosableInteger]);
	}

	private boolean firstPlayerMove(int integers, int maxChoosableInteger, int desiredTotal, Boolean[] cache) {

		Boolean result = false;
		for (int i = 1; i <= maxChoosableInteger; i++) {
			int bit = 1 << (i - 1);
			if((integers & bit) >= 1) continue;

			int newIntegers = integers;
			newIntegers |= bit;

			int newTotal = desiredTotal - i;
			Boolean localResult = cache[newIntegers];

			boolean secondWin;
			if(localResult != null) {
				secondWin = localResult;
			} else {
				if(newTotal <= 0) {
					secondWin = false;
				} else {
					secondWin = secondPlayerMove(newIntegers, maxChoosableInteger, newTotal, cache);
				}
			}

			if(!secondWin) {
				result = true;
				break;
			}
		}

		cache[integers] = result;

		return result;
	}

	private boolean secondPlayerMove(int integers, int maxChoosableInteger, int desiredTotal, Boolean[] cache) {

		Boolean result = false;
		for (int i = 1; i <= maxChoosableInteger; i++) {
			int bit = 1 << (i - 1);
			if((integers & bit) >= 1) continue;

			int newIntegers = integers;
			newIntegers |= bit;

			int newTotal = desiredTotal - i;
			Boolean localResult = cache[newIntegers];

			boolean firstWin;
			if(localResult != null) {
				firstWin = localResult;
			} else {
				if(newTotal <= 0) {
					firstWin = false;
				} else {
					firstWin = secondPlayerMove(newIntegers, maxChoosableInteger, desiredTotal - i, cache);
				}
			}

			if(!firstWin) {
				result = true;
				break;
			}
		}

		cache[integers] = result;

		return result;
	}
}

