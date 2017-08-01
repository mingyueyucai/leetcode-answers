// it is not the best solution for problem 4
double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size) {
    int p = 0, q = 0;
    int odd = (nums1Size + nums2Size) & 1;
    int middle = ((nums1Size + nums2Size) >> 1) + 1;
    int pre = 0;
    int x;
    while (p < nums1Size || q < nums2Size) {
        if (p == nums1Size) {
            x = nums2[q];
            q++;
        } else if (q == nums2Size || nums1[p] < nums2[q]) {
            x = nums1[p];
            p++;
        } else {
            x = nums2[q];
            q++;
        }
        if (p + q == middle) {
            if (odd) {
                return x;
            } else {
                return (x + pre) / 2.0;
            }
        }
        pre = x;
    }
    // impossible
    return 0;
}
