import java.util.concurrent.ExecutionException;

public class Solution {
    private class Word {
        public boolean isWildcard = false;
        public char ch;
    }

    // isMatch[i][j] = true means s.substring(0,i) matches words.subList(0,j)
    boolean[][] isMatch;

    public boolean isMatch(String s, String p) {
        isMatch = new boolean[100][s.length() + 1];
        Word[] words = tokenize(p);
        int wordLen = 0;
        while (words[wordLen++] != null) {}
        wordLen--;
        // str pointer
        int sPointer = 0;
        // pattern pointer
        int pPointer = 0;
        boolean match = true;
        for (int i = 0; i <= wordLen; i++) {
            for (int j = 0; j <= s.length(); j++) {
                try {
                    if (i == 0 && j == 0) {
                        isMatch[i][j] = true;
                    } else if (i == 0) {
                        isMatch[i][j] = false;
                    } else if (j == 0) {
                        isMatch[i][j] = isMatch[i - 1][j] && words[i - 1].isWildcard;
                    } else {
                        isMatch[i][j] = isMatch[i - 1][j] && words[i - 1].isWildcard
                                || isMatch[i - 1][j - 1] && matchWord(s.charAt(j - 1), words[i - 1])
                                || isMatch[i][j - 1] && words[i - 1].isWildcard && matchWord(s.charAt(j - 1), words[i - 1]);
                    }
                } catch (Exception e) {
                    System.out.print(e.toString());
                    throw e;
                }
            }
        }
        return isMatch[wordLen][s.length()];
    }

    private boolean matchWord(char ch, Word word) {
        if (word.ch == '.') {
            return true;
        }
        return ch == word.ch;
    }

    private Word[] tokenize(String s) {
        int p = 0;
        Word[] words = new Word[10000];
        int count = 0;
        while (p < s.length()) {
            Word word = new Word();
            words[count++] = word;
            word.ch = s.charAt(p);
            if (p + 1 < s.length() && s.charAt(p + 1) == '*') {
                word.isWildcard = true;
                p += 2;
            } else {
                p++;
            }
        }
        p = 0;
        Word cache = null;
        int q = 0;
        Word[] impactedWords = new Word[1000];
        while (words[p] != null) {
            if (!words[p].isWildcard) {
                if (cache != null) {
                    impactedWords[q++] = cache;
                    cache = null;
                }
                impactedWords[q++] = words[p];
            } else if (cache == null) {
                cache = words[p];
            } else if (cache.ch == words[p].ch) {
                // do nothing
            } else if (cache.ch == '.' || words[p].ch == '.') {
                cache.ch = '.';
            } else {
                impactedWords[q++] = cache;
                cache = words[p];
            }
            p++;
        }
        if (cache != null) {
            impactedWords[q++] = cache;
        }
        return impactedWords;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isMatch("aaca", "ab*a*c*a"));
    }
}
