package com.my.leetcode.evaluate_division;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        /*System.out.println(Arrays.toString(new Solution().calcEquation(
                new String[][] {{"a",  "b"}, {"b", "c"} },
                new double []{2.0, 3.0},
                new String[][] { {"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"} }
        )));*/
        //[6.0, 0.5, -1.0, 1.0, -1.0 ]
        System.out.println(Arrays.toString(new Solution().calcEquation(
                new String[][] {{"a",  "b"}, {"e", "f"}, {"b", "e"} },
                new double []{3.4, 1.4, 2.3},
                new String[][] { {"b", "a"}, {"a", "f"}, {"f", "f"}, {"e", "e"}, {"c", "c"}, {"a", "c"}, {"f", "e"} }
        )));
        //[0.29412,10.948,1.0,1.0,-1.0,-1.0,0.71429]
        //[0.29412,7.82,  1.0,1.0,-1.0,-1.0,0.71429]
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        UnionFind unionFind = new UnionFind(equations.length * 2);
        Map<String, Integer> indexMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            for (String s : equation) {
                if (!indexMap.containsKey(s)) {
                    indexMap.put(s, count++);
                }
            }
            unionFind.union(indexMap.get(equation[0]), indexMap.get(equation[1]), values[i]);
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            result[i] = -1.0;
            if (indexMap.get(query[0]) != null &&
                    indexMap.get(query[1]) != null &&
                    unionFind.find(indexMap.get(query[0])).index == unionFind.find(indexMap.get(query[1])).index) {
                double x1 = unionFind.calculate(indexMap.get(query[0]));
                double x2 = unionFind.calculate(indexMap.get(query[1]));
                result[i] = x1 / x2;
            }
        }
        return result;
    }

    /*
     Can calculate any Math.Group
     */
    public static class UnionFind {

        private Container[] parent;

        public UnionFind(int size) {
            this.parent = new Container[size];

            for (int i = 0; i < size; i++) {
                this.parent[i] = new Container(i, 1.0);
            }
        }

        public Container find(int index) {
            if (parent[index].index == index) return parent[index];
            else {
                Container pp = find(parent[index].index);
                return new Container(pp.index, pp.multiply * parent[index].multiply);
            }
        }

        public double calculate(int index) {
            if (parent[index].index == index) return 1.0;
            else return parent[index].multiply * calculate(parent[index].index);
        }

        public void union(int index1, int index2, double value) {
            Container parent1 = find(index1);
            Container parent2 = find(index2);
            parent[parent1.index] = new Container(parent2.index, (parent2.multiply * value)/(parent1.multiply));
        }

        private static class Container {
            private int index;
            private double multiply;
            public Container(int index, double multiply) {
                this.index = index;
                this.multiply = multiply;
            }
        }
    }
}
