package com.my.hackerrank.week_of_code_38.cyclical_queries;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class CyclicalQueriesRegression {
    static final int NODES_NUM = 10000;
    static final int QUERIES_NUM = 5000;
    static final int MAX_WEIGHT = 100000;

    static void generateTest(String testName) throws IOException {
        File f = new File(testName);
        f.createNewFile();
        PrintStream out = new PrintStream(f);
        out.println(NODES_NUM);


        for (int i = 0; i < NODES_NUM; ++i) {
            if (i > 0) out.print(' ');
            out.print(Math.round(Math.random() * (MAX_WEIGHT - 1)) + 1);
        }
        out.println();

        out.println(QUERIES_NUM * (NODES_NUM + 1));
        for (int i = 0; i < QUERIES_NUM; ++i) {
            int queryType = (int)Math.round(Math.random() * 2) + 1;
            int param1 = (int)Math.round(Math.random() * (NODES_NUM - 1)) + 1;
            int param2 = (int)Math.round(Math.random() * (MAX_WEIGHT - 1) + 1);
            switch (queryType) {
                case 1:
                case 2:
                    out.println(queryType + " " + param1 + " " + param2);
                    break;
                case 3:
                    out.println(queryType + " " + param1);
                    break;
            }
            for (int j = 1; j <= NODES_NUM; ++j) {
                out.println("4 " + j);
            }
        }

        out.close();
    }

    public static void main(String[] params) throws IOException {
        for (File f : new File("./tests/").listFiles()) {
            f.delete();
        }
        for (int i = 1; i < 10; ++i) {
            String inputName = "./tests/" + String.format("%02d", i) + "_input.txt";
            generateTest(inputName);

            System.setIn(new FileInputStream(new File(inputName)));
            String expectedOutputName = "./tests/" + String.format("%02d", i) + "_output_expected.txt";
            File f = new File(expectedOutputName);
            f.createNewFile();
            PrintStream expectedOut = new PrintStream(f);
            System.setOut(expectedOut);
            EtalonSolution.main(new String[]{});
            expectedOut.close();

            System.setIn(new FileInputStream(new File(inputName)));
            String myOutputName = "./tests/" + String.format("%02d", i) + "_output_my.txt";
            f = new File(myOutputName);
            f.createNewFile();
            PrintStream myOut = new PrintStream(f);
            System.setOut(myOut);
            Solution.main(new String[]{});
            myOut.close();

            if (FileUtils.contentEquals(new File(expectedOutputName), new File(myOutputName))) {
                File f1 = new File(expectedOutputName);
                f1.delete();
                File f2 = new File(myOutputName);
                f2.delete();
                File f3 = new File(inputName);
                f3.delete();
            }
        }
    }
}
