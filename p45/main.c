#include <stdio.h>

int jump(int* nums, int numsSize) {
    if (numsSize == 1) {
        return 0;
    }
    int currentMax = 0;
    int nextMax = -1;
    int currentStep = 0;
    for (int i = 0; i < numsSize; i++) {
        if (i > currentMax) {
            currentStep++;
            currentMax = nextMax;
            nextMax = -1;
        }
        if (i + nums[i] >= numsSize - 1) {
            return currentStep + 1;
        }
        if (nums[i] + i > nextMax) {
            nextMax = nums[i] + i;
        }
    }
    return -1;
}

int main() {
    int a[] = {2, 3, 1, 1, 4};
    printf("%d\n", jump(a, 5));
    return 0;
}