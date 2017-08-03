public class Solution {
    private String[] one = new String[]{"I", "X", "C", "M"};
    private String[] five = new String[]{"V", "L", "D", "."};
    private String[] strings = new String[10];
    public String intToRoman(int num) {
        int i = 0;
        while (num > 0) {
            strings[i] = generateOnePos(i, num % 10);
            num = num / 10;
            i++;
        }
        i--;
        String s = "";
        while (i >= 0) {
            s += strings[i--];
        }
        return s;
    }

    private String generateOnePos(int digit, int value) {
        switch (value) {
            case 0:
                return "";
            case 1:
                return one[digit];
            case 2:
                return one[digit] + one[digit];
            case 3:
                return one[digit] + one[digit] + one[digit];
            case 4:
                return one[digit] + five[digit];
            case 5:
                return five[digit];
            case 6:
                return five[digit] + one[digit];
            case 7:
                return five[digit] + one[digit] + one[digit];
            case 8:
                return five[digit] + one[digit] + one[digit] + one[digit];
            case 9:
                return one[digit] + one[digit + 1];
            default:
                // impossible
                return "";
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.intToRoman(1970));
    }
}