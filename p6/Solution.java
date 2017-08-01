public class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int groupSize = numRows * 2 - 2;
        int len = s.length();
        StringBuilder sb = new StringBuilder(s.length());
        for (int row = 1; row <= numRows; row++) {
            int p = row - 1;
            while (p < len) {
                sb.append(s.charAt(p));
                if (row != 1 && row != numRows && p + numRows + numRows - row - row< len) {
                    sb.append(s.charAt(p + numRows + numRows - row - row));
                }
                p += groupSize;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.convert("PAYPALISHIRING", 3));
    }
}
