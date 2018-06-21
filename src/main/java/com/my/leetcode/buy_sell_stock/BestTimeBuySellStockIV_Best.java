package com.my.leetcode.buy_sell_stock;

/**
 * Created by alex.bykovsky on 9/7/17.
 */
public class BestTimeBuySellStockIV_Best {

	public static void main(String[] args) {
		assertEquals(0, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {4, 2, 1}));
		assertEquals(7, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {3, 2, 6, 5, 0, 3}));
		assertEquals(18, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {4,8,6,8,7,8,9,20}));
		assertEquals(29, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {1, 10, 0, 20}));
		assertEquals(1000, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {0, 100, 200, 1000}));
		assertEquals(100, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {0, 50, 0, 50}));
		assertEquals(8, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {0, 5, 2, 5}));
		assertEquals(1, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {1, 2}));
		assertEquals(7, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {6, 1, 3, 2, 4, 7}));
		assertEquals(11, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {2, 1, 4, 5, 2, 9, 7}));
		assertEquals(13, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
		assertEquals(6, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {3, 3, 5, 0, 0, 3, 1, 4}));
		assertEquals(13, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {4, 7, 2, 1, 11}));
		assertEquals(6, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {1, 4, 1, 4, 3, 1}));
		assertEquals(6, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {1, 7, 4, 2}));
		assertEquals(0, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {7, 4, 2, 1}));
		assertEquals(10, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {1,3,5,4,3,7,6,9,2,4}));
		assertEquals(7, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {5,7,2,7,3,3,5,3,0}));
		assertEquals(10, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {1,4,2,9,4,3}));
		assertEquals(14, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {5,2,3,2,6,6,2,9,1,0,7,4,5,0}));
		assertEquals(17, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {1,2,4,2,5,7,2,4,9,0,9}));
		assertEquals(14, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {2,6,8,7,8,7,9,4,1,2,4,5,8}));
		assertEquals(12, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {5,5,4,9,3,8,5,5,1,6,8,3,4}));
		assertEquals(0, new BestTimeBuySellStockIV_Best().maxProfit(0, new int[] {1,3}));
		assertEquals(469, new BestTimeBuySellStockIV_Best().maxProfit(7, new int[] {48,12,60,93,97,42,25,64,17,56,85,93,9,48,52,42,58,85,81,84,69,36,1,54,23,15,72,15,11,94}));
		assertEquals(9, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {4,0,1,0,0,0,6,1,4}));
		assertEquals(7, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {6,5,4,8,6,8,7,8,9,4,5}));
		assertEquals(11, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {8,6,4,3,3,2,3,5,8,3,8,2,6}));
		assertEquals(15, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {8,3,6,2,8,8,8,4,2,0,7,2,9,4,9}));
		assertEquals(27, new BestTimeBuySellStockIV_Best().maxProfit(5, new int[] {1,4,7,5,6,2,5,1,9,7,9,7,0,6,8}));
		assertEquals(6, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {0, 1, 2, 0, 3, 0, 2, 2, 1, 3}));
		assertEquals(7, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {1, 4, 1, 4, 0, 1, 2, 1, 3, 4}));
		assertEquals(7, new BestTimeBuySellStockIV_Best().maxProfit(2, new int[] {1, 0, 4, 2, 2, 2, 2, 0, 3, 2}));
		assertEquals(8, new BestTimeBuySellStockIV_Best().maxProfit(3, new int[] {3, 4, 2, 4, 0, 2, 1, 4, 0, 2}));
		assertEquals(2818, new BestTimeBuySellStockIV_Best().maxProfit(29, new int[] {70,4,83,56,94,72,78,43,2,86,65,100,94,56,41,66,3,33,10,3,45,94,15,12,78,60,58,0,58,15,21,7,11,41,12,96,83,77,47,62,27,19,40,63,30,4,77,52,17,57,21,66,63,29,51,40,37,6,44,42,92,16,64,33,31,51,36,0,29,95,92,35,66,91,19,21,100,95,40,61,15,83,31,55,59,84,21,99,45,64,90,25,40,6,41,5,25,52,59,61,51,37,92,90,20,20,96,66,79,28,83,60,91,30,52,55,1,99,8,68,14,84,59,5,34,93,25,10,93,21,35,66,88,20,97,25,63,80,20,86,33,53,43,86,53,55,61,77,9,2,56,78,43,19,68,69,49,1,6,5,82,46,24,33,85,24,56,51,45,100,94,26,15,33,35,59,25,65,32,26,93,73,0,40,92,56,76,18,2,45,64,66,64,39,77,1,55,90,10,27,85,40,95,78,39,40,62,30,12,57,84,95,86,57,41,52,77,17,9,15,33,17,68,63,59,40,5,63,30,86,57,5,55,47,0,92,95,100,25,79,84,93,83,93,18,20,32,63,65,56,68,7,31,100,88,93,11,43,20,13,54,34,29,90,50,24,13,44,89,57,65,95,58,32,67,38,2,41,4,63,56,88,39,57,10,1,97,98,25,45,96,35,22,0,37,74,98,14,37,77,54,40,17,9,28,83,13,92,3,8,60,52,64,8,87,77,96,70,61,3,96,83,56,5,99,81,94,3,38,91,55,83,15,30,39,54,79,55,86,85,32,27,20,74,91,99,100,46,69,77,34,97,0,50,51,21,12,3,84,84,48,69,94,28,64,36,70,34,70,11,89,58,6,90,86,4,97,63,10,37,48,68,30,29,53,4,91,7,56,63,22,93,69,93,1,85,11,20,41,36,66,67,57,76,85,37,80,99,63,23,71,11,73,41,48,54,61,49,91,97,60,38,99,8,17,2,5,56,3,69,90,62,75,76,55,71,83,34,2,36,56,40,15,62,39,78,7,37,58,22,64,59,80,16,2,34,83,43,40,39,38,35,89,72,56,77,78,14,45,0,57,32,82,93,96,3,51,27,36,38,1,19,66,98,93,91,18,95,93,39,12,40,73,100,17,72,93,25,35,45,91,78,13,97,56,40,69,86,69,99,4,36,36,82,35,52,12,46,74,57,65,91,51,41,42,17,78,49,75,9,23,65,44,47,93,84,70,19,22,57,27,84,57,85,2,61,17,90,34,49,74,64,46,61,0,28,57,78,75,31,27,24,10,93,34,19,75,53,17,26,2,41,89,79,37,14,93,55,74,11,77,60,61,2,68,0,15,12,47,12,48,57,73,17,18,11,83,38,5,36,53,94,40,48,81,53,32,53,12,21,90,100,32,29,94,92,83,80,36,73,59,61,43,100,36,71,89,9,24,56,7,48,34,58,0,43,34,18,1,29,97,70,92,88,0,48,51,53,0,50,21,91,23,34,49,19,17,9,23,43,87,72,39,17,17,97,14,29,4,10,84,10,33,100,86,43,20,22,58,90,70,48,23,75,4,66,97,95,1,80,24,43,97,15,38,53,55,86,63,40,7,26,60,95,12,98,15,95,71,86,46,33,68,32,86,89,18,88,97,32,42,5,57,13,1,23,34,37,13,65,13,47,55,85,37,57,14,89,94,57,13,6,98,47,52,51,19,99,42,1,19,74,60,8,48,28,65,6,12,57,49,27,95,1,2,10,25,49,68,57,32,99,24,19,25,32,89,88,73,96,57,14,65,34,8,82,9,94,91,19,53,61,70,54,4,66,26,8,63,62,9,20,42,17,52,97,51,53,19,48,76,40,80,6,1,89,52,70,38,95,62,24,88,64,42,61,6,50,91,87,69,13,58,43,98,19,94,65,56,72,20,72,92,85,58,46,67,2,23,88,58,25,88,18,92,46,15,18,37,9,90,2,38,0,16,86,44,69,71,70,30,38,17,69,69,80,73,79,56,17,95,12,37,43,5,5,6,42,16,44,22,62,37,86,8,51,73,46,44,15,98,54,22,47,28,11,75,52,49,38,84,55,3,69,100,54,66,6,23,98,22,99,21,74,75,33,67,8,80,90,23,46,93,69,85,46,87,76,93,38,77,37,72,35,3,82,11,67,46,53,29,60,33,12,62,23,27,72,35,63,68,14,35,27,98,94,65,3,13,48,83,27,84,86,49,31,63,40,12,34,79,61,47,29,33,52,100,85,38,24,1,16,62,89,36,74,9,49,62,89}));
		assertEquals(15, new BestTimeBuySellStockIV_Best().maxProfit(3, new int[] {0, 6, 4, 6, 1, 7, 3, 5, 2, 5}));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public int maxProfit(int k, int[] prices) {
		if(prices.length <= 1 || k == 0) return 0;
		if (k>prices.length/2){ int ans = 0; for (int i=1; i<prices.length; ++i){ ans += Math.max(prices[i] - prices[i-1],0);}return ans;}
		int[] next = new int[] {0, 0};
		DLQueue queue = new DLQueue();
		IndexMinPQ<Range> heap = new IndexMinPQ<>(Math.min(k, prices.length));
		for(int i = 0; i < Math.min(k, prices.length); i++) {
			heap.insert(i, new Range(0, 0, 0, Integer.MAX_VALUE, i));
		}
		int sumCurrent = 0;
		for(int i = 1; i < prices.length; i++) {
			int diff = prices[i] - prices[i - 1];
			if(diff > 0) {
				next[1] = i;
			} else if(diff < 0) {
				sumCurrent = oneStep(prices, next, queue, heap, sumCurrent, i);
			}
		}
		sumCurrent = oneStep(prices, next, queue, heap, sumCurrent, prices.length);
		return sumCurrent;
	}

	private int oneStep(int[] prices, int[] next, DLQueue queue, IndexMinPQ<Range> heap, int sumCurrent, int i) {
		boolean operation = false;
		if(next[1] > next[0]) {
			boolean joinBiggerThanSwitch = sumNext(prices, queue, sumCurrent, next) >= calcProfitSum(prices, heap.minKey(), next, sumCurrent);
			if(queue.end != null && joinBiggerThanSwitch && sumNext(prices, queue, sumCurrent, next) >= sumCurrent) {
				sumCurrent += (prices[next[1]] - prices[queue.end.range.hi]);
				queue.end.range.hi = next[1];
				queue.end.range.diff = prices[queue.end.range.hi] - prices[queue.end.range.lo];
				calcProfit3(prices, queue.end, heap);
				operation = true;
			} else {
				Range min = heap.minKey();
				if(min.node == null && prices[next[1]] > prices[next[0]]) {
					addNode(queue, min);
					sumCurrent -= min.diff;
					switchNode(next, prices, heap, min);
					calcProfit(prices, min.node, heap);
					calcProfit(prices, min.node.left, heap);
					sumCurrent += min.diff;
					operation = true;
				} else if(calcProfitSum(prices, heap.minKey(), next, sumCurrent) >= sumCurrent) {
					int leftDiff = getLeftDiff(prices, min.node);
					int rightDiff = getRightDiff(prices, min.node);
					if(rightDiff >= leftDiff && rightDiff > 0 && min.node.right != null) {
						Range rightRange = min.node.right.range;
						rightRange.lo = min.lo;
						if(prices[min.hi] > prices[rightRange.hi]) {
							rightRange.hi = min.hi;
						}
						sumCurrent -= rightRange.diff;
						rightRange.diff = prices[rightRange.hi] - prices[rightRange.lo];
						sumCurrent += rightRange.diff;
						calcProfit3(prices, rightRange.node, heap);
					} else if (leftDiff > 0 && min.node.left != null) {
						Range leftRange = min.node.left.range;
						leftRange.hi = min.hi;
						if(prices[min.lo] < prices[leftRange.lo]) {
							leftRange.lo = min.lo;
						}
						sumCurrent -= leftRange.diff;
						leftRange.diff = prices[leftRange.hi] - prices[leftRange.lo];
						sumCurrent += leftRange.diff;
						calcProfit3(prices, leftRange.node, heap);
					}
					sumCurrent -= min.diff;
					Node left = min.node.left;
					Node right = min.node.right;
					switchNode(next, prices, heap, min);
					sumCurrent += min.diff;
					moveToEnd(queue, min.node);
					calcProfit(prices, left, heap);
					calcProfit(prices, right, heap);
					calcProfit(prices, min.node, heap);
					calcProfit(prices, min.node.left, heap);
					operation = true;
				}
			}
		}
		if(i < prices.length && (operation || prices[i] < prices[next[0]])) {
			next[0] = i;
		}
		return sumCurrent;
	}
	private void switchNode(int[] next, int[] prices, IndexMinPQ<Range> heap, Range min) {
		min.lo = next[0];
		min.hi = next[1];
		min.diff = prices[min.hi] - prices[min.lo];
	}
	private void calcProfit3(int[] prices, Node node, IndexMinPQ<Range> heap) {
		calcProfit(prices, node, heap);
		calcProfit(prices, node.left, heap);
		calcProfit(prices, node.right, heap);
	}
	private void calcProfit(int[] prices, Node node, IndexMinPQ<Range> heap) {
		if(node == null) return;
		int leftDiff = getLeftDiff(prices, node);
		int rightDiff = getRightDiff(prices, node);
		node.range.profit = Math.max(leftDiff, rightDiff) - node.range.diff;
		heap.changeKey(node.range.index, node.range);
	}
	private int calcProfitSum(int[] prices, Range range, int[] next, int sumCurrent) {
		Node node = range.node;
		int leftDiff = getLeftDiff(prices, node);
		int rightDiff = getRightDiff(prices, node);
		int max = Math.max(leftDiff, rightDiff);
		return sumCurrent - range.diff + (max > 0? max: 0) + prices[next[1]] - prices[next[0]];
	}
	private int getLeftDiff(int[] prices, Node node) {
		int leftDiff = 0;
		if(node != null && node.left != null) {
			leftDiff = prices[node.range.hi] - prices[node.left.range.hi];
			int leftLoDiff = prices[node.left.range.lo] - prices[node.range.lo];
			leftDiff += leftLoDiff > 0? leftLoDiff: 0;
		}
		return leftDiff;
	}
	private int getRightDiff(int[] prices, Node node) {
		int rightDiff = 0;
		if(node != null && node.right != null) {
			rightDiff = prices[node.right.range.lo] - prices[node.range.lo];
			int rightHiDiff = prices[node.range.hi] - prices[node.right.range.hi];
			rightDiff += rightHiDiff > 0? rightHiDiff: 0;
		}
		return rightDiff;
	}
	private int sumNext(int[] prices, DLQueue queue, int sumCurrent, int[] next) {
		Node end = queue.end;
		return prices[next[1]] - (end == null? prices[next[0]]: prices[end.range.lo]) + sumCurrent - (end == null? 0: end.range.diff);
	}
	private static class Range implements Comparable<Range> {
		private int lo;
		private int hi;
		private int diff;
		private int profit;
		private Node node;
		private int index;
		Range(int lo, int hi, int diff, int profit, int index) {
			this.lo = lo;
			this.hi = hi;
			this.diff = diff;
			this.profit = profit;
			this.index = index;
		}
		public int compareTo(Range o) {
			long diff = (long) o.profit - (long) this.profit;
			return diff == 0? this.diff - o.diff: diff > 0? 1: -1;
		}
	}
	private static class DLQueue {
		private Node end;
		private int length;
	}
	private static class Node {
		private Node left;
		private Node right;
		private Range range;
		public Node(Range range) {
			this.range = range;
		}
	}
	private void addNode(DLQueue queue, Range range) {
		Node end = queue.end;
		Node node = new Node(range);
		range.node = node;
		node.left = end;
		node.right = null;
		if(end != null) {
			end.right = node;
		}
		queue.end = node;
		queue.length++;
	}
	private void moveToEnd(DLQueue queue, Node node) {
		Node end = queue.end;
		if(end == node) return;
		removeNode(node);
		node.left = end;
		node.right = null;
		if(end != null) {
			end.right = node;
		}
		queue.end = node;
	}
	private void removeNode(Node node) {
		if(node.left != null) {
			node.left.right = node.right;
		}
		if(node.right != null) {
			node.right.left = node.left;
		}
	}
	private static class IndexMinPQ<Key extends Comparable<Key>> {
		private int n;
		private int[] pq;
		private int[] qp;
		private Key[] keys;
		public IndexMinPQ(int maxN) {
			n = 0;
			keys = (Key[]) new Comparable[maxN + 1];
			pq   = new int[maxN + 1];
			qp   = new int[maxN + 1];
			for (int i = 0; i <= maxN; i++)
				qp[i] = -1;
		}
		public boolean isEmpty() {
			return n == 0;
		}
		public int size() {
			return n;
		}
		public void insert(int i, Key key) {
			n++;
			qp[i] = n;
			pq[n] = i;
			keys[i] = key;
			swim(n);
		}
		public Key minKey() {
			return keys[pq[1]];
		}
		public void changeKey(int i, Key key) {
			keys[i] = key;
			swim(qp[i]);
			sink(qp[i]);
		}
		private boolean greater(int i, int j) {
			return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
		}
		private void exch(int i, int j) {
			int swap = pq[i];
			pq[i] = pq[j];
			pq[j] = swap;
			qp[pq[i]] = i;
			qp[pq[j]] = j;
		}
		private void swim(int k) {
			while (k > 1 && greater(k/2, k)) {
				exch(k, k/2);
				k = k/2;
			}
		}
		private void sink(int k) {
			while (2*k <= n) {
				int j = 2*k;
				if (j < n && greater(j, j+1)) j++;
				if (!greater(k, j)) break;
				exch(k, j);
				k = j;
			}
		}
	}
}
