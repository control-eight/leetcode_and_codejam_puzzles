package com.my.hackerrank;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author abykovsky
 * @since 6/21/18
 */
public class InputReader {

    private char[] content;
    private int pos = 0;
    private int[] readTo = new int[1];

    public InputReader(InputStream in) throws IOException {
        Reader charReader = new InputStreamReader(in);
        content = new char[in.available()];
        charReader.read(content, 0, content.length);
        charReader.close();
    }

    public int readInt() {
        readIntArray(readTo, 0, 1);
        return readTo[0];
    }

    public void readIntArray(int[] arr, int from, int length) {
        for (int i = from, k = 0; k < length; ++k, ++i) {
            int num = 0;
            int sign = 1;
            if (content[pos] == '-') {
                sign = -1;
                ++pos;
            } else if (content[pos] == '+') ++pos;

            while (pos < content.length) {
                char ch = content[pos++];
                if (ch < '0') {
                    break;
                } else {
                    int digit = ch - '0';
                    num = (num << 3) + (num << 1) + digit;
                }
            }
            arr[from++] = num * sign;
        }
    }
}
