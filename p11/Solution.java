public class Solution {
    public int maxArea(int[] height) {
        int p = 0;
        int q = height.length - 1;
        int max = (q - p) * Integer.min(height[p], height[q]);
        int t;
        while (q > p) {
            if (height[p] >= height[q]) {
                q--;
            } else {
                p++;
            }
            t = (q - p) * Integer.min(height[p], height[q]);
            if (t > max) {
                max = t;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxArea(new int[]{2, 1, 2, 11, 15, 3}));
    }
}