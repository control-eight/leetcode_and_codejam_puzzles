package com.my.codechef.may20b.twostrs.aho_corasick;

import java.io.*;
import java.util.*;

public class SolutionOpt {

    static class VocabularyEntry {
        String s;
        int happiness;

        public VocabularyEntry(String s, int happiness) {
            this.s = s;
            this.happiness = happiness;
        }
    }

    static class AhoCorasick {
        private static class Node {
            static int nextId = 0;

            private final int id;
            private Node[] children = new Node[26];
            private Node[] go = new Node[26];
            private final Node parent;
            private Node suffixLink = null;
            private final int charToParent;
            private long wordsSum = 0;
            private boolean isWord;
            private long b;

            public Node(Node parent, int charToParent) {
                id = nextId++;
                this.parent = parent;
                this.charToParent = charToParent;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                Node currentNode = this;
                while (currentNode.parent != null) {
                    sb.append((char)('a' + currentNode.charToParent));
                    currentNode = currentNode.parent;
                }
                return sb.reverse().toString();
            }
        }

        public class Iterator {
            private Node currentNode;

            private Iterator() {
                this.currentNode = root;
            }

            public long wordsSum() {
                return currentNode.wordsSum;
            }

            public void move(char ch) {
                int chI = ch - 'a';
                currentNode = getLink(currentNode, chI);
            }

            @Override
            public Iterator clone() {
                Iterator cl = new Iterator();
                cl.currentNode = currentNode;
                return cl;
            }

            @Override
            public String toString() {
                return currentNode.toString();
            }
        }

        private Node root;

        public AhoCorasick() {
            root = new Node(null, -1);
            root.suffixLink = root;
        }

        public void addWord(char[] s, long val) {
            Node currentNode = root;
            for (int i = 0; i < s.length; ++i) {
                char c = s[i];
                int cI = c - 'a';
                Node nextNode = currentNode.children[cI];
                if (nextNode == null) {
                    nextNode = currentNode.children[cI] = new Node(currentNode, cI);
                }
                if (i == s.length - 1) {
                    nextNode.isWord = true;
                    nextNode.b = val;
                }
                currentNode = nextNode;
            }
            currentNode.wordsSum += val;
        }

        public void ready() {
            Queue<Node> nodes = new LinkedList<>();
            nodes.add(root);
            while (!nodes.isEmpty()) {
                Node currentNode = nodes.poll();
                currentNode.suffixLink = getSuffixLink(currentNode);
                for (Node child : currentNode.children) {
                    if (child != null) {
                        nodes.add(child);
                    }
                }
            }
        }

        public Iterator iterator() {
            return new Iterator();
        }

        private Node getSuffixLink(Node node) {
            if (node.suffixLink == null) {
                if (node == root || node.parent == root) {
                    node.suffixLink = root;
                } else {
                    node.suffixLink = getLink(getSuffixLink(node.parent), node.charToParent);
                }
                node.wordsSum += node.suffixLink.wordsSum;
            }
            return node.suffixLink;
        }

        private Node getLink(Node node, int charIndex) {
            if (node.go[charIndex] != null) return node.go[charIndex];

            if (node.children[charIndex] != null) {
                return node.go[charIndex] = node.children[charIndex];
            } else if (node == root) {
                return node.go[charIndex] = root;
            } else {
                return node.go[charIndex] = getLink(getSuffixLink(node), charIndex);
            }
        }

        private void computeIntervals(String S, long[][] bSum) {
            for (int i = 0; i < S.length(); i++) {
                this.compute(this.root, S, i, i, bSum);
                for (int j = 1; j < bSum[0].length; j++) {
                    bSum[i][j] += bSum[i][j - 1];
                }
            }
            for (int i = bSum.length - 2; i >= 0; i--) {
                bSum[i][26] += bSum[i + 1][26];
            }
        }

        private void compute(Node node, String S, int start, int current, long[][] bSum) {
            if (node.isWord) {
                bSum[start][current - start] += node.b;
            }
            if (current >= S.length()) return;
            Node next = node.children[S.charAt(current) - 'a'];
            if (next != null) {
                this.compute(next, S, start, current + 1, bSum);
            }
        }
    }

    static long solve(String a, String b, VocabularyEntry[] voc) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        AhoCorasick vocPrefixAC = new AhoCorasick();

        int maxVocStringLength = 0;
        int minVocStringLength = Integer.MAX_VALUE;

        for (VocabularyEntry entry : voc) {
            char[] entryChars = entry.s.toCharArray();
            vocPrefixAC.addWord(entryChars, entry.happiness);
            maxVocStringLength = Math.max(entry.s.length(), maxVocStringLength);
            minVocStringLength = Math.min(entry.s.length(), minVocStringLength);
        }
        vocPrefixAC.ready();

        long[][] bSum = new long[b.length()][27];
        vocPrefixAC.computeIntervals(b, bSum);

        long result = 0;
        AhoCorasick.Iterator aIt = vocPrefixAC.iterator();
        long matchedInRemainingA = 0;

        for (int aEndPos = 0; aEndPos < a.length(); ++aEndPos) {
            aIt.move(aChars[aEndPos]);
            matchedInRemainingA += aIt.wordsSum();

            for (int bStartPos = 0; bStartPos < b.length(); ++bStartPos) {
                long newMatch = 0;
                AhoCorasick.Iterator bIt = aIt.clone();
                long matchedInRemainingB = bSum[bStartPos][26];
                int bEndIndex = Math.min(b.length() - 1, bStartPos + maxVocStringLength - 2);
                for (int bMatchEndPos = bStartPos; bMatchEndPos <= bEndIndex; ++bMatchEndPos) {
                    bIt.move(bChars[bMatchEndPos]);
                    newMatch += bIt.wordsSum();
                    newMatch -= bSum[bMatchEndPos][bEndIndex - bMatchEndPos + 1];
                }
                long candidate = matchedInRemainingA + matchedInRemainingB + newMatch;
                result = Math.max(result, candidate);
            }
        }
        return result;
    }

    public static void main(String[] params) throws Exception {
        InputReader reader = new InputReader(System.in);
        int t = reader.readInt();
        for (int i = 0; i < t; ++i) {
            String a = reader.readWord();
            String b = reader.readWord();
            int n = reader.readInt();
            VocabularyEntry[] voc = new VocabularyEntry[n];
            for (int j = 0; j < n; ++j) voc[j] = new VocabularyEntry(reader.readWord(), reader.readInt());
            System.out.println(solve(a, b, voc));
        }
    }

    static class InputReader {
        static final int bufferSize = 1 << 25;

        private char[] content;
        private int pos = 0;
        private int[] readTo = new int[1];

        public InputReader(InputStream in) throws IOException {
            Reader charReader = new InputStreamReader(in);
            content = new char[bufferSize];
            charReader.read(content, 0, content.length);
            charReader.close();
        }

        public String readWord() {
            while (pos < content.length && (content[pos] == ' ' || content[pos] == 13 || content[pos] == 10)) ++pos;
            int count = 0;
            int initialPos = pos;
            while (pos < content.length) {
                char ch = content[pos];
                if (ch != ' ' && ch != 10 && ch != 13) {
                    ++count;
                    ++pos;
                } else break;
            }
            while (pos < content.length && (content[pos] == ' ' || content[pos] == 13 || content[pos] == 10)) ++pos;
            return new String(content, initialPos, count);
        }

        public int readInt() {
            readIntArray(readTo, 0, 1);
            return readTo[0];
        }

        public void readIntArray(int[] arr, int from, int length) {
            for (int i = from, k = 0; k < length; ++k, ++i) {
                while (pos < content.length && content[pos] < '0') ++pos;

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
}
