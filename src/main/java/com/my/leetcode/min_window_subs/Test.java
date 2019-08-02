package com.my.leetcode.min_window_subs;

public class Test {

    public static void main(String[] args) {
        String s = "qjnkluedopzyhozjzqnjentspwffoawwbgyhrrapncwetqulmaupkkwugkpfztgejujlakrnkvefbvn";
        StringBuilder sb = new StringBuilder();
        String t = "qkkwtlzbbn";
        for (int i = 0; i < s.length(); i++) {
            if (t.contains(s.charAt(i) + "")) {
                sb.append(s.charAt(i));
            }
        }
        System.out.println(sb);
    }
}


//qnklzzzqnntwwwbnwtqlkkwkztlknkbn
//qkkwtlzbbn