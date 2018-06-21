package com.my.leetcode;

import java.util.function.Function;

/**
 * Created by alex.bykovsky on 10/3/17.
 */
public class Timed<R> {

	public TimedResult<R> act(Function<Object, R> function) {
		long start = System.currentTimeMillis();
		R apply = function.apply(null);
		return new TimedResult<>(apply, System.currentTimeMillis() - start);
	}

	public static class TimedResult<R> {
		private R result;
		private long time;

		public TimedResult(R result, long time) {
			this.result = result;
			this.time = time;
		}

		public R getResult() {
			return result;
		}

		public long getTime() {
			return time;
		}
	}
}
