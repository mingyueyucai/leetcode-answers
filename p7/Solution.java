public class Solution {
    public int reverse(int x) {
        if (x == 0x8000000) {
            return 0;
        }

        int data[] = new int[10];
        int p = 0;
        boolean positive = x > 0;
        if (!positive) {
            x = -x;
        }
        while (x > 0) {
            data[p] = x % 10;
            x = x / 10;
            p++;
        }
        if (p < 10) {
            return (positive ? 1 : -1) * build(data, p);
        }
        if (isOverflow(data)) {
            return 0;
        } else {
            return (positive ? 1 : -1) * build(data, p);
        }
    }

    private int build(int data[], int length) {
        int x = 0;
        for (int i = 0; i < length; i++) {
            x = x * 10 + data[i];
        }
        return x;
    }

    private boolean isOverflow(int data[]) {
        int data2[] = new int[10];
        int p = 0;
        int x = Integer.MAX_VALUE;
        while (x > 0) {
            data2[9 - p] = x % 10;
            x = x / 10;
            p++;
        }
        return isReverseResultGreaterThan(data, data2, 10);
    }

    private boolean isReverseResultGreaterThan(int data1[], int data2[], int length) {
        int i = 0;
        while (i < length && data1[i] == data2[i]) {
            i++;
        }
        return i < length && data1[i] > data2[i];
    }

    public static void main(String[] args) {
        System.out.println(-14 % 5);
        Solution solution = new Solution();
        System.out.println(solution.reverse(-1234567802));
    }
}