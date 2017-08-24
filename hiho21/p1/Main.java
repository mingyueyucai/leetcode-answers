import java.util.Scanner;

public class Main {
    private static int[] modValue = new int[100000];
    private static final int DIVIDER = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }
        sort(0, n - 1, data);
        long count = 0;
        int endPos = binSearch(k, 0, n - 1, data);
        while (endPos >= 0) {
            int startPos = binSearch(k - data[endPos], 0, n - 1, data);
            if (startPos > endPos) {
                startPos = endPos;
            }
            count = addCount(getCount(endPos) - getCount(endPos - startPos - 1), count);
                /*
                for (int j = i; j <= endPos; j++) {
                    System.out.print(data[j] + " ");
                }
                System.out.println(" = " + getCount(endPos - i - 1));
                System.out.println();
                */
            endPos--;
        }
        System.out.print(count);
    }

    private static long addCount(int v, long count) {
        return (count + v) % DIVIDER;
    }

    private static int getCount(int v) {
        if (v == -1) {
            return 0;
        }
        if (modValue[0] == 0) {
            int t = 1;
            modValue[0] = 1;
            for (int i = 1; i < 100000; i++) {
                t = (t << 1) % DIVIDER;
                modValue[i] = t;
            }
        }
        return modValue[v];
    }

    private static int binSearch(int v, int l, int r, int[] data) {
        int mid = (l + r) / 2;
        if (data[mid] == v) {
            return mid;
        } else if (v < data[mid]) {
            if (l < mid) {
                return binSearch(v, l, mid - 1, data);
            } else {
                return -1;
            }
        } else {
            if (mid < r) {
                int t = binSearch(v, mid + 1, r, data);
                if (t == -1) {
                    return mid;
                } else {
                    return t;
                }
            } else {
                return r;
            }
        }
    }

    private static void sort(int l, int r, int[] data) {
        int i = l;
        int j = r;
        int pivot = data[l + (r - l) / 2];

        while (i <= j) {
            while (data[i] < pivot) {
                i++;
            }
            while (pivot < data[j]) {
                j--;
            }
            if (i <= j) {
                int t = data[i];
                data[i] = data[j];
                data[j] = t;
                i++;
                j--;
            }
        }
        if (l < j) {
            sort(l, j, data);
        }
        if (i < r) {
            sort(i, r, data);
        }
    }

}
