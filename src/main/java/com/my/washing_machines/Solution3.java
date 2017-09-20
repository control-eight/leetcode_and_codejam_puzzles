package com.my.washing_machines;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by alex.bykovsky on 9/18/17.
 */
public class Solution3 {

	public static void main(String[] args) {

		//assertEquals(50000, new Solution3().findMinMoves(new int[]{100000,0,100000,0,100000,0,100000,0,100000,0,100000,0}));

		int length = 10;
		int maxNumber = 4;
		int count = 1000;
		Random random = new Random(length * maxNumber);
		for(int i = 0; i < count; i++) {
			int[] arr = new int[length];
			for(int j = 0; j < arr.length; j++) {
				arr[j] = (int) (random.nextDouble() * maxNumber);
			}
			try {
				assertEquals(new Solution2().findMinMoves(arr), new Solution3().findMinMoves(arr));
			} catch (Throwable e) {
				System.out.println(e + " " + Arrays.toString(arr));
			}
		}

		assertEquals(2, new Solution3().findMinMoves(new int[]{0,2,3,3}));
		assertEquals(3, new Solution3().findMinMoves(new int[]{5,4,3,2,1}));
		assertEquals(4, new Solution3().findMinMoves(new int[]{0,0,4,4}));
		assertEquals(2, new Solution3().findMinMoves(new int[]{0,3,0}));
		assertEquals(2, new Solution3().findMinMoves(new int[]{0,2,4}));
		assertEquals(1, new Solution3().findMinMoves(new int[]{3,0,3}));
		assertEquals(1, new Solution3().findMinMoves(new int[]{1,2,3}));
		assertEquals(1, new Solution3().findMinMoves(new int[]{3,2,1}));
		assertEquals(3, new Solution3().findMinMoves(new int[]{1,0,5}));
		assertEquals(-1, new Solution3().findMinMoves(new int[]{0,2,0}));
		assertEquals(2, new Solution3().findMinMoves(new int[]{0,3,2,3}));
		assertEquals(2, new Solution3().findMinMoves(new int[]{0,4,0,4}));
		assertEquals(2, new Solution3().findMinMoves(new int[]{0,4,0,4,0,4}));
		assertEquals(2, new Solution3().findMinMoves(new int[]{4,0,0,4}));
		assertEquals(2, new Solution3().findMinMoves(new int[]{0,0,2,2}));
		assertEquals(3, new Solution3().findMinMoves(new int[]{0,0,0,4}));
		assertEquals(3, new Solution3().findMinMoves(new int[]{2,2,2,6}));
		assertEquals(3, new Solution3().findMinMoves(new int[]{1,2,4,5}));
		assertEquals(2, new Solution3().findMinMoves(new int[]{0,2,4,2}));
		assertEquals(2, new Solution3().findMinMoves(new int[]{0,0,2,0,3}));
		assertEquals(2, new Solution3().findMinMoves(new int[]{4,0,4,0}));
		assertEquals(4, new Solution3().findMinMoves(new int[]{0,0,4,0,5,0,5,0,4}));

		/*assertEquals(8, new Solution3().findMinMoves(new int[]{0,0,10,0,0,0,10,0,0,0}));
		assertEquals(11, new Solution3().findMinMoves(new int[]{0,0,14,0,10,0,0,0}));
		assertEquals(8, new Solution3().findMinMoves(new int[]{0,4,12,0}));
		assertEquals(7, new Solution3().findMinMoves(new int[]{0,1,1,10}));
		assertEquals(6, new Solution3().findMinMoves(new int[]{7,6,5,4,3,2,1}));*/
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public int findMinMoves(int[] machines) {

		int sum = 0;
		for (int machine : machines) {
			sum += machine;
		}
		int length = machines.length;
		if (sum % length != 0) return -1;
		int avg = sum / length;

		if(need(machines, avg,0, machines.length) == 0) return 0;

		return findMinMoves(machines, new Boolean[machines.length], 0, avg, 1, new HashMap<>());
	}

	private int findMinMoves(int[] machines, Boolean[] fromTheRight, int index, int avg, int iteration, Map<Key, Value> cache) {

		if(need(machines, avg,0, machines.length) == 0) {
			return iteration;
		}

		if(index == machines.length) {
			index = 0;
			iteration++;
		}

		Key key = new Key(copy(machines), index, iteration);
		Value value = cache.get(key);
		if(value != null) {
			return value.iteration;
		}

		int first = Integer.MAX_VALUE;
		if(index > 0 && machines[index] > 0 && needLeft(machines, avg, index) > 0 &&
				haveRight(machines, avg, index) > 0 && !Boolean.FALSE.equals(fromTheRight[index])) {
			first = getFirst(machines, fromTheRight, index, avg, iteration, cache);
		}

		int second = Integer.MAX_VALUE;
		if(machines[index] <= avg) {
			second = getSecond(machines, fromTheRight, index, avg, iteration, cache);
		}

		int third = Integer.MAX_VALUE;
		if(index < machines.length - 1 && machines[index] > 0 && needRight(machines, avg, index) > 0
				&& haveLeft(machines, avg, index) > 0 && !Boolean.TRUE.equals(fromTheRight[index])) {
			third = getThird(machines, fromTheRight, index, avg, iteration, cache);
		}

		int min = Math.min(first, Math.min(second, third));

		cache.put(key, new Value(min));

		return min;
	}

	private int getFirst(int[] machines, Boolean[] fromTheRight, int index, int avg, int iteration, Map<Key, Value> cache) {
		machines[index]--;
		machines[index - 1]++;
		fromTheRight[index - 1] = true;

		int result = findMinMoves(copy(machines), fromTheRight, index + 1, avg, iteration, cache);

		fromTheRight[index - 1] = null;
		machines[index]++;
		machines[index - 1]--;

		return result;
	}

	private int getSecond(int[] machines, Boolean[] fromTheRight, int index, int avg, int iteration, Map<Key, Value> cache) {
		return findMinMoves(copy(machines), fromTheRight, index + 1, avg, iteration, cache);
	}

	private int getThird(int[] machines, Boolean[] fromTheRight, int index, int avg, int iteration, Map<Key, Value> cache) {
		machines[index]--;
		machines[index + 1]++;
		fromTheRight[index + 1] = false;

		int result = findMinMoves(copy(machines), fromTheRight, index + 1, avg, iteration, cache);

		fromTheRight[index + 1] = null;
		machines[index]++;
		machines[index + 1]--;

		return result;
	}

	private int needLeft(int[] machines, int avg, int index) {
		return need(machines, avg, 0, index);
	}

	private int needRight(int[] machines, int avg, int index) {
		return need(machines, avg, index + 1, machines.length);
	}

	private int need(int[] machines, int avg, int start, int end) {
		int need = 0;
		for (int i = start; i < end; i++) {
			int machine = machines[i];
			if(machine < avg) {
				need += avg - machine;
			}
		}
		return need;
	}

	private int haveLeft(int[] machines, int avg, int index) {
		return have(machines, avg, 0, index + 1);
	}

	private int haveRight(int[] machines, int avg, int index) {
		return have(machines, avg, index, machines.length);
	}

	private int have(int[] machines, int avg, int start, int end) {
		int have = 0;
		for (int i = start; i < end; i++) {
			int machine = machines[i];
			if(machine > avg) {
				have += machine - avg;
			}
		}
		return have;
	}

	private int[] copy(int[] machines) {
		int[] result = new int[machines.length];
		System.arraycopy(machines, 0, result, 0, machines.length);
		return result;
	}

	private static class Key {

		private int[] machines;

		private int index;

		private int iteration;

		public Key(int[] machines, int index, int iteration) {
			this.machines = machines;
			this.index = index;
			this.iteration = iteration;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Key key = (Key) o;

			if (index != key.index) return false;
			if (iteration != key.iteration) return false;
			return Arrays.equals(machines, key.machines);
		}

		@Override
		public int hashCode() {
			int result = Arrays.hashCode(machines);
			result = 31 * result + index;
			result = 31 * result + iteration;
			return result;
		}
	}

	private static class Value {

		private int iteration;

		public Value(int iteration) {
			this.iteration = iteration;
		}
	}
}
