package com.my.leetcode.range_sum_query_2d_mutable;

class NumMatrix {

    public static void main(String[] args) {
        int[][] ints = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(ints);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        numMatrix.update(3, 2, 2);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));

        ints = new int[][]{{1}};
        numMatrix = new NumMatrix(ints);
        System.out.println(numMatrix.sumRegion(0,0,0,0));
        numMatrix.update(0,0,-1);
        System.out.println(numMatrix.sumRegion(0,0,0,0));

        ints = new int[][]{{2,4},{-3,5}};
        numMatrix = new NumMatrix(ints);
        numMatrix.update(0,1,3);
        numMatrix.update(1,1,-3);
        numMatrix.update(0,1,1);
        System.out.println(numMatrix.sumRegion(0,0,1,1));
    }

    private FenwickTree[] rows;
    private int[][] matrix;

    public NumMatrix(int[][] matrix) {
        rows = new FenwickTree[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rows[i] = new FenwickTree(matrix[i].length);
            for (int j = 0; j < matrix[i].length; j++) {
                rows[i].update(j + 1, matrix[i][j]);
            }
        }
        this.matrix = matrix;
    }

    public void update(int row, int col, int val) {
        rows[row].update(col + 1, val - this.matrix[row][col]);
        this.matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += rows[i].rsq(col1 + 1, col2 + 1);
        }
        return sum;
    }

    public static class FenwickTree {

        int[] array; // 1-indexed array, In this array We save cumulative information to perform efficient range queries and updates

        public FenwickTree(int size) {
            array = new int[size + 1];
        }

        /**
         * Range Sum query from 1 to ind
         * ind is 1-indexed
         * <p>
         * Time-Complexity:    O(log(n))
         *
         * @param  ind index
         * @return sum
         */
        public int rsq(int ind) {
            //if (ind <= 0)
            int sum = 0;
            while (ind > 0) {
                sum += array[ind];
                //Extracting the portion up to the first significant one of the binary representation of 'ind' and decrementing ind by that number
                ind -= ind & (-ind);
            }

            return sum;
        }

        /**
         * Range Sum Query from a to b.
         * Search for the sum from array index from a to b
         * a and b are 1-indexed
         * <p>
         * Time-Complexity:    O(log(n))
         *
         * @param  a left index
         * @param  b right index
         * @return sum
         */
        public int rsq(int a, int b) {
            //if (!(b >= a && a > 0 && b > 0))

            return rsq(b) - rsq(a - 1);
        }

        /**
         * Update the array at ind and all the affected regions above ind.
         * ind is 1-indexed
         * <p>
         * Time-Complexity:    O(log(n))
         *
         * @param  ind   index
         * @param  value value
         */
        public void update(int ind, int value) {
            //if (ind <= 0)
            while (ind < array.length) {
                array[ind] += value;
                //Extracting the portion up to the first significant one of the binary representation of 'ind' and incrementing ind by that number
                ind += ind & (-ind);
            }
        }

        public int size() {
            return array.length - 1;
        }
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
