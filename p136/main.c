#include <stdio.h>

int singleNumber(int* nums, int numsSize) {
    int x = nums[0];
    for (int i = 1; i < numsSize; i++) {
        x = x ^ nums[i];
    }
    return x;
}

int main() {
    int a[] = {1, 1, 2, 3, 2};
    printf("%d\n", singleNumber(a, 5));
    return 0;
}
