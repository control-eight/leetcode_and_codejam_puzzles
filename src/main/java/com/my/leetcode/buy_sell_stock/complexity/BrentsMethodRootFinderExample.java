package com.my.leetcode.buy_sell_stock.complexity;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BrentsMethodRootFinderExample {

    private double start;
    private double end;
    private double size;

    public BrentsMethodRootFinderExample(double start, double end, double size) {
        this.start = start;
        this.end = end;
        this.size = size;
    }

    public List<Double> solve (double[] coeffs, double[] degrees) {
        BrentSolver solver = new BrentSolver();
        UnivariateFunction f = x -> {
            double result = 0;
            for (int i = 0; i < coeffs.length; i++) {
                result += coeffs[i] * Math.pow(x, degrees[i]);
            }
            return result;
        };

        double intervalStart = start;
        double intervalSize = size;
        List<Double> roots = new ArrayList<>();
        while (intervalStart < end) {
            intervalStart += intervalSize;
            if (Math.signum(f.value(intervalStart)) != Math.signum(f.value(intervalStart + intervalSize))) {
                roots.add(solver.solve(10, f, intervalStart, intervalStart + intervalSize));
            }
        }
        return roots;
    }

    public static void printRoots(List<Double> roots) {
        for (Double root : roots) {
            //if (root >= 0 || root <= 1.0) {
                double rootRound = BigDecimal.valueOf(root).setScale(12, RoundingMode.HALF_UP).doubleValue();
                System.out.print(rootRound + ", ");
            //}
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BrentsMethodRootFinderExample solver = new BrentsMethodRootFinderExample(-10, 10, 0.1);
        //solveAndPrint(solver, new double[]{1, -5, 6}, new double[]{2, 1, 0});
        //solveAndPrint(solver, new double[] {1, -2, 1}, new double[]{2, 1, 0});
        //solveAndPrint(solver, new double[] {1, -1, -1}, new double[]{2, 1, 0});

        for (int i = 1; i < 50; i++) {
            solveAndPrintEquation(solver, i);
        }
    }

    //q(z) = z^N - z^(N-1) - 2*z^(N-2) - 3*z^(N-3) - ... - N = 0
    private static void solveAndPrintEquation(BrentsMethodRootFinderExample solver, int length) {
        double[] coeffs = new double[length + 1];
        coeffs[0] = 1;
        for (int i = 1; i <= length; i++) {
            coeffs[i] = -1 * i;
        }
        double[] degrees = new double[length + 1];
        for (int i = 0; i < length + 1; i++) {
            degrees[i] = length - i;
        }
        solveAndPrint(solver, coeffs, degrees);
    }

    private static void solveAndPrint(BrentsMethodRootFinderExample solver, double[] coeffs, double[] degrees) {
        for (int i = 0; i < coeffs.length; i++) {
            //x^10 - x^9 - 2*x^8 - 3*x^7 - 4*x^6 - 5*x^5 - 6*x^4 - 7*x^3 - 8*x^2 - 9*x - 10 = 0
            System.out.print(coeffs[i] + "*" + "x^(" + degrees[i] + ")");
        }
        System.out.println(" = 0");


        List<Double> roots = solver.solve(coeffs, degrees);
        printRoots(roots);
        //System.out.println();
    }
}
