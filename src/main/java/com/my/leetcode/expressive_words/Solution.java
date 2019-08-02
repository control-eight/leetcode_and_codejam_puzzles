package com.my.leetcode.expressive_words;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().expressiveWords("heeellooo", new String[] {"hello", "hi", "helo"}));
        System.out.println(new Solution().expressiveWords("aaa", new String[] {"aaaa"}));
        System.out.println(new Solution().expressiveWords("dddiiiinnssssssoooo", new String[] {
                "dinnssoo","ddinso","ddiinnso","ddiinnssoo","ddiinso","dinsoo","ddiinsso","dinssoo","dinso"
        }));
        System.out.println(new Solution().expressiveWords("vvvppppeeezzzzztttttkkkkkkugggggbbffffffywwwwwwbbbccccddddddkkkkksssppppddpzzzzzhhhhbbbbbmmmy", new String[] {
                "vvpeezttkkuggbbfywwbbccddkkspdpzhbbmmyy","vvppeeztkkugbfywwbccddkksspdppzhhbmyy","vppezzttkkugbffyywbccddksspddpzhhbmy","vvppezztkugbffyywwbbccddkssppddpzzhhbbmmy","vvppezttkuggbfyywwbbcddkspdppzhhbmy","vppeezzttkkuugbfyywwbbccdkkssppdpzzhbbmy","vpeezztkkugbbffyywwbbccddkksppdpzzhhbbmmy","vppeeztkkuuggbffywbbccddkksppdppzhhbmyy","vpeeztkkuggbfyywbbccdksppdpzhbmy","vpeezztkkugbffywwbbccdkkssppddppzzhhbbmmy","vvpeztkkugbbfyywbcdkssppddpzzhhbbmyy","vpezztkugbbffyywwbcddksppddpzzhbbmy","vvpeezztkkugbbffywwbccdkkspddpzzhbmmyy","vvpeezzttkkuuggbbffyywbbccdkspdppzhhbmy","vvppeezztkkuggbbfywbcdkspdpzhhbmyy","vvppeezzttkkuugbffyywwbbccddkkspddpzzhbmyy","vppezztkuuggbffywwbcdksspdppzhhbmyy","vvppeezzttkuuggbffywbccddkksspddppzzhhbmmy","vvppezzttkuggbffywbbccdkspddppzzhhbmy","vvpezzttkuugbbfywwbccdkssppdpzhbbmmy","vvpeezzttkuugbbffyywbccdksppddppzhhbmyy","vpeezzttkkuggbbffywbccddksppddpzhhbbmy","vvpezttkuuggbffywwbbccddkspdppzhhbmmyy","vppeezzttkkuugbffywbccddksppddpzhhbmmyy","vvpezttkkuugbbfywbccdkspddppzzhbbmmy","vppezzttkkuugbbffywwbcddkssppddpzhhbmmy","vppezzttkugbfywbbcdksppddppzzhhbmyy","vppeeztkuggbbffywbbccdkkspddppzzhbbmmy","vvpeeztkuuggbbfywbcdkksspddppzhhbbmmyy","vpezttkkuuggbbffyywwbbcdksspddppzhhbmy","vpeezzttkkuuggbffywwbccdkksspddppzzhbmyy","vpezttkkuugbffyywbccdksspddppzhbbmmyy","vvppezztkugbbffyywbbccdkksppdppzhbmyy","vvpeezttkuggbbfyywwbbcddkksppdpzzhbbmyy","vvpeztkuuggbffyywbbccdkksspddppzzhbbmy","vppeezzttkugbbffyywbccddksppdppzzhbmmyy","vppeezttkkuugbbfywwbccddkksspdpzhhbmmy","vpeezzttkugbbffywbbccdkksspddppzhbbmyy","vpeezttkkuugbbfywbbccddksppddppzzhhbmmy","vpeezztkuuggbbffywwbbccddksspddpzzhhbbmmyy","vppeezttkkuggbbffyywwbccdksspdpzzhbmy","vpezzttkkuugbbfyywbbcdksspdppzzhbbmyy","vvppezttkkuggbbfyywbbccdkksspddpzhbbmyy","vvpezzttkuggbbffyywbbcdkksppdpzzhbmmyy","vvpeztkugbfywwbccddkkspddpzhhbbmyy","vvppezttkuugbbfyywwbcddkksspdppzhhbbmy","vvpeeztkkuuggbbfywwbcdkspddpzzhhbmmy","vvpeezttkugbffywbbccdkkssppddppzhhbbmyy","vpeztkuuggbbfyywwbcddksppddpzhbbmy","vppeztkuggbbfyywbcdksspdppzzhhbmy","vppeezttkkugbbffyywbccddkksppdpzhhbmy","vvppeeztkugbfyywbcdkksppdppzhbmyy","vpezttkuugbbffywbcdksppddpzzhhbbmmy","vppezzttkuugbfyywbcddkksspdpzhbbmmy","vppezzttkkuggbffywbbcdksspdpzzhhbbmmyy","vpezzttkuggbfyywbbccdksspdpzhhbbmmy","vvppezttkkugbffyywbcdkssppdpzzhbmy","vvpeezttkkuuggbbfyywbbccdkspdppzhhbmy","vpeezttkkuugbfywbccddkksppddpzzhhbmmy","vvppezttkuuggbbffywbbccdkksppdpzzhhbbmmy","vvppeeztkuggbbffyywbccdksspddppzzhbmmyy","vvppeezztkuggbfywwbccddkkspddpzhbbmy","vpezttkuuggbfyywwbcdkkspdpzhhbbmmyy","vppezzttkuggbffywbbcdkkssppddppzhhbmyy","vppeztkuuggbffyywbccdkkspdppzzhhbmmyy","vppeezztkuuggbfywbccddkksspddppzhhbbmyy","vvppeztkuugbfywwbccdkkspddppzzhhbmmy","vvpezztkuugbbffyywwbbccddksppdpzhbbmmyy","vvpezzttkkuuggbffyywwbbcdkspdpzhbmmyy","vvppeztkkuuggbbfyywbbccdksppdppzzhbmmyy","vvppezztkuggbffyywwbcddkkssppdpzhbmmyy","vvpezzttkkuggbbffywwbcddkksspdpzzhhbbmmy","vpezztkkuuggbfyywwbccddkssppdppzhhbbmmy","vvppezztkuugbffywwbccdkkspdppzhhbmmy","vpeztkugbfyywwbcdkksspdppzzhbmmy","vvpeezzttkkugbbfywwbcdkkspdpzzhhbmmy","vpezzttkuuggbbfywbccdkspddppzzhhbbmmy","vppeztkkuugbffyywwbbcddksspddppzhbbmyy","vpeztkkuggbffyywbbccddkssppdppzhbmyy","vvppeezztkuggbffyywwbcddkksppdppzhbmyy","vpeezztkugbfyywbbccdkkspdppzhbmmyy","vvppeezttkugbfywwbcddkkssppdppzhbmmyy","vpeeztkuggbffywwbbccddksspdppzzhhbmmy","vvppeeztkuugbfywbcddkssppddppzzhhbbmyy","vpezzttkuggbbffyywwbbccdkssppddppzhbbmy","vpezttkugbfywbbcddkksspddppzhbbmy","vpeezzttkkuggbbffyywwbccddkspddppzhbmyy","vppeezzttkugbffywbccdkkspddpzhhbbmyy","vpezzttkuggbbfyywbbccdkksspddpzzhhbmmy","vvppezttkugbfywwbbcdkksspddpzzhhbbmyy","vppezztkkuggbffyywbcddkkssppddpzzhhbbmmy","vppeztkkuggbfywwbccdkksppdppzhhbmmy","vvpeezzttkugbffyywwbbcddkssppddpzzhbmmy","vvpezztkkuuggbfyywbccdkksspddpzhhbbmyy","vpezttkuuggbffywbbccdksppdpzhbmmyy","vvpezzttkuggbbfywbccddksspdpzzhhbmmy","vvpeezzttkkugbbfywwbcdkksppddpzhbmy","vppeezttkkuugbbfyywwbcddkkspdpzhhbbmmyy","vvppeeztkkuugbbfyywwbbcddkksspdppzhbbmyy","vvpeezzttkkuugbfywwbbcddkspdpzzhbbmyy"
        }));
    }

    public int expressiveWords(String S, String[] words) {
        int result = 0;
        for (String word : words) {
            int group = 0;
            boolean isStretchy = true;
            int j = 0;
            if (word.length() > S.length()) {
                 isStretchy = false;
            } else {
                for (int i = 0; i < S.length(); i++) {
                    if (j == word.length() || word.charAt(j) != S.charAt(i)) {
                        if (j > 0 && word.charAt(j - 1) == S.charAt(i)) {
                            group++;
                            if (j > 1 && word.charAt(j - 2) == S.charAt(i)) {
                                group++;
                            }
                        } else {
                            isStretchy = false;
                            break;
                        }
                    } else {
                        if (group == 1) {
                            isStretchy = false;
                            break;
                        }
                        group = 0;
                        j++;
                    }
                }
            }
            if (isStretchy && group != 1 && j == word.length()) {
                result++;
            }
        }
        return result;
    }
}
