public class Solution {
    private int maxLength = 1;
    private int startIndex = 0;

    public String longestPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            testPalindromic(s, i, i);
            testPalindromic(s, i, i + 1);
        }
        return s.substring(startIndex, startIndex + maxLength);
    }

    private void testPalindromic(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        if (r - l - 1 > maxLength) {
            maxLength = r - l - 1;
            startIndex = l + 1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("babad"));
    }
}