public class Solution {
    public int myAtoi(String str) {
        int v = 0;
        boolean positive = true;
        while (str.length() > 0) {
            if (str.charAt(0) == '-') {
                positive = !positive;
                str = str.substring(1);
            } else if (str.charAt(0) == '+') {
                str = str.substring(1);
            } else {
                break;
            }
        }
        if (str.length() == 0) {
            return 0;
        }
        while (str.length() >= 1) {
            v = v * 10 + (str.charAt(0) - '0');
            str = str.substring(1);
        }
        return positive ? v : -v;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myAtoi("---+1"));
    }
}
