package com.my.leetcode.encode_decode_strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Codec {

    public static void main(String[] args) {
        Codec code = new Codec();
        String s = code.encode(Arrays.asList("abc", "apple", "baggwa"));
        System.out.println(code.decode(s));
    }

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append(":").append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        if (s.isEmpty()) return result;
        while (!s.isEmpty()) {
            int length = Integer.parseInt(s.substring(0, s.indexOf(":")));
            s = s.substring(s.indexOf(':') + 1);
            result.add(s.substring(0, length));
            s = s.substring(length);
        }
        return result;
    }
}
