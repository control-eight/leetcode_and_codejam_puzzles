package com.my.code_jam.y2019.qualification.dat_bae;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SolutionLogN {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int index = 1;

        while (index <= T) {
            int N = in.nextInt();
            int B = in.nextInt();
            int F = in.nextInt();

            int[][] mapping = new int[N-B][3];
            for (int i = 0; i < mapping.length; i++) {
                mapping[i][0] = 0;
                mapping[i][1] = N;
                mapping[i][2] = 0;
            }

            char[] buffer = new char[N];
            for (int moves = 1; moves <= Math.ceil(Math.log(N) / Math.log(2)); moves++) {
                for (int m = 0; m < mapping.length; m++) {
                    int mid = (mapping[m][1] - mapping[m][0]) / 2;
                    for(int k = mapping[m][0]; k < mapping[m][0] + mid; k++) {
                        if (mapping[m][2] == 0) {
                            buffer[k] = '0';
                        } else {
                            buffer[k] = '1';
                        }
                    }
                    for(int k = mapping[m][0] + mid; k < mapping[m][1]; k++) {
                        if (mapping[m][2] == 0) {
                            buffer[k] = '1';
                        } else {
                            buffer[k] = '0';
                        }
                    }
                    //System.out.println("sb: " + Arrays.toString(buffer));
                }
                System.out.print(new String(buffer) + "\n");
                System.out.flush();

                String workingServersResponse = in.next();
                for (int m = 0; m < workingServersResponse.length(); m++) {
                    int newValue = Integer.parseInt(workingServersResponse.charAt(m) + "");
                    int mid = (mapping[m][1] - mapping[m][0]) / 2;
                    if (mapping[m][2] == newValue) {
                        mapping[m][1] -= mid;
                    } else {
                        mapping[m][0] += mid;
                        mapping[m][2] = newValue;
                    }
                }
                /*System.out.println("mappings");
                for (int[] ints : mapping) {
                    System.out.println(Arrays.toString(ints));
                }*/
            }

            Set<Integer> works = new HashSet<>();
            for (int p = 0; p < mapping.length; p++) {
                works.add(mapping[p][0]);
            }
            if (!works.contains(0)) {
                System.out.print('0');
            }
            for (int i = 1; i < N; i++) {
                if (!works.contains(i)) {
                    System.out.print(" " + i);
                }
            }
            System.out.print("\n");
            System.out.flush();

            if (in.nextInt() != 1) throw new RuntimeException();
            index++;
        }
    }
}
