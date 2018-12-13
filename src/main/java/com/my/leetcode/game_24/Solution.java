package com.my.leetcode.game_24;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().judgePoint24(new int[] {3, 3, 8, 8}));
        //true
        //8 / ( 3 - 8 / 3)
        System.out.println(new Solution().judgePoint24(new int[] {1, 3, 4, 6}));
        //true
        //6 / (1 - 3 / 4)
        System.out.println(new Solution().judgePoint24(new int[] {7, 9, 3, 7}));
        //true
        //3 * (9 - 7 / 7)
        System.out.println(new Solution().judgePoint24(new int[] {3, 9, 7, 7}));
        //true
        //3 * ( 9 - 7 / 7)
        System.out.println(new Solution().judgePoint24(new int[] {1, 2, 3, 4}));
        //true
        // 1 * 2 * 3 * 4
        System.out.println(new Solution().judgePoint24(new int[] {1, 9, 1, 2}));
        //true
        //(9 - 1) * (1 + 2)
        System.out.println(new Solution().judgePoint24(new int[] {4, 1, 8, 7}));
        //true
        //(8-4) * (7-1) = 24
        System.out.println(new Solution().judgePoint24(new int[] {1, 2, 1, 2}));
        //false
        System.out.println(new Solution().judgePoint24(new int[] {1, 5, 9, 1}));
        //false
    }

    public boolean judgePoint24(int[] nums) {
        if (nums.length == 0) return false;

        Collection<Expr> exprs = generate(nums);
        //System.out.println(exprs);

        Collection<Expr> prevExprs = exprs;
        int i = 0;
        while (i < nums.length - 1) {
            Collection<Expr> newExprs = new HashSet<>();
            for (Expr expr : exprs) {
                for (Expr expr1 : prevExprs) {
                    if (expr1.canMergeWith(expr)) {
                        if (foundOrAdd(newExprs, expr1.add(expr))) return true;
                        if (foundOrAdd(newExprs, expr1.substract(expr))) return true;
                        if (foundOrAdd(newExprs, expr1.multiply(expr))) return true;
                        if (foundOrAdd(newExprs, expr1.divide(expr))) return true;

                        if (foundOrAdd(newExprs, expr.substract(expr1))) return true;
                        if (foundOrAdd(newExprs, expr.divide(expr1))) return true;
                    }
                }
            }
            prevExprs = newExprs;
            i++;
            //System.out.println(prevExprs);
        }

        return false;
    }

    private boolean foundOrAdd(Collection<Expr> newExprs, Expr result) {
        if (result.isFound()) return true;
        if (!result.isFinished()) {
            newExprs.add(result);
        }
        return false;
    }

    private Collection<Expr> generate(int[] nums) {
        Collection<Expr> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            Expr expr = new Expr(nums[i], i);
            result.add(expr);
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    Expr other = new Expr(nums[j], j);
                    result.add(expr.add(other));
                    result.add(expr.substract(other));
                    result.add(expr.multiply(other));
                    result.add(expr.divide(other));
                }
            }
        }
        return result;
    }

    private static class Expr {

        private double result;
        private boolean[] used;
        private int count;

        public Expr(int num, int index) {
            this.result = num;
            this.used = new boolean[4];
            this.used[index] = true;
            this.count = 1;
        }

        public Expr(Expr expr) {
            this.result = expr.result;
            this.used = new boolean[4];
            System.arraycopy(expr.used, 0, this.used, 0, expr.used.length);
            this.count = expr.count;
        }

        public Expr add(Expr other) {
            Expr expr = new Expr(this);
            expr.result += other.result;
            mergeUsed(expr.used, other.used);
            expr.count += other.count;
            return expr;
        }

        public Expr substract(Expr other) {
            Expr expr = new Expr(this);
            expr.result -= other.result;
            mergeUsed(expr.used, other.used);
            expr.count += other.count;
            return expr;
        }

        public Expr multiply(Expr other) {
            Expr expr = new Expr(this);
            expr.result *= other.result;
            mergeUsed(expr.used, other.used);
            expr.count += other.count;
            return expr;
        }

        public Expr divide(Expr other) {
            Expr expr = new Expr(this);
            expr.result /= other.result;
            mergeUsed(expr.used, other.used);
            expr.count += other.count;
            return expr;
        }

        public boolean isFound() {
            return isFinished() &&
                    (result == 24.0 || result + 0.00000000000001 == 24.0 || result - 0.00000000000001 == 24.0);
        }

        public boolean isFinished() {
            return count == used.length;
        }

        private void mergeUsed(boolean[] used, boolean[] used1) {
            for (int i = 0; i < used.length; i++) {
                used[i] |= used1[i];
            }
        }

        public boolean canMergeWith(Expr expr) {
            for (int i = 0; i < used.length; i++) {
                if (used[i] && expr.used[i]) return false;
            }
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Expr expr = (Expr) o;
            return Double.compare(expr.result, result) == 0 &&
                    Arrays.equals(used, expr.used);
        }

        @Override
        public int hashCode() {
            int result1 = Objects.hash(result);
            result1 = 31 * result1 + Arrays.hashCode(used);
            return result1;
        }

        @Override
        public String toString() {
            return result + "";
        }
    }
}
