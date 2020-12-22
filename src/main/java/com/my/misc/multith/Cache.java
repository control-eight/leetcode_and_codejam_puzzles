package com.my.misc.multith;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class Cache {

    private final Transformer t;
    private final AtomicReference<byte[]> data;
    private final ExecutorService pool;

    Cache(Transformer t) {
        this.t = t;
        this.data = new AtomicReference<>();
        this.pool = Executors.newFixedThreadPool(10);
    }

    byte[] getTransformedData() {
        return this.data.get();
    }

    void setData(String s) {
        this.pool.submit(new Task(this.data.get(), s));
    }

    private class Task implements Runnable {
        private final byte[] expected;
        private final String s;

        public Task(byte[] data, String s) {
            this.expected = data;
            this.s = s;
        }

        @Override
        public void run() {
            byte[] newData = Cache.this.t.transform(s);
            data.compareAndSet(expected, newData);
        }
    }
}

interface Transformer {
    byte[] transform(String s);
}
