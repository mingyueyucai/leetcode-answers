#include <stdio.h>
#include <stdlib.h>

void swap(int* i, int* j) {
    int k;
    k = *i;
    *i = *j;
    *j = k;
}

void usort(int* data, int* index, int l, int r)
{
    int x = data[(l + r) / 2];
    int i = l;
    int j = r;
    while (i < j) {
        while (data[i] < x) {
            i++;
        }
        while (data[j] > x) {
            j--;
        }
        if (i <= j) {
            swap(&data[i], &data[j]);
            swap(&index[i], &index[j]);
            i++;
            j--;
        }
    }
    if (l < j) {
        usort(data, index, l, j);
    }
    if (i < r) {
        usort(data, index, i, r);
    }
}

int binSearch(int* data, int l, int r, int v) {
    int t = (l + r) / 2;
    if (data[t] == v) {
        return t;
    } else if (data[t] < v && t + 1 <= r) {
        return binSearch(data, t + 1, r, v);
    } else if (data[t] > v && l <= t - 1) {
        return binSearch(data, l, t - 1, v);
    }
    return -1;
}

int* twoSum(int* nums, int numsSize, int target) {
    int i,j;
    int* result = malloc(2 * sizeof(int));
    int* index = malloc(numsSize * sizeof(int));
    int temp, pos;
    for (i = 0; i < numsSize; i++) {
        index[i] = i + 1;
    }
    usort(nums, index, 0, numsSize - 1);
    /*
    for (i = 0; i < numsSize; i++) {
        printf("%d ", nums[i]);
    }
    */
    for (i = 0; i < numsSize; i++) {
        temp = target - nums[i];
        pos = binSearch(nums, 0, numsSize - 1, temp);
        if (pos >= 0 && pos != i) {
            if (index[i] > index[pos]) {
                swap(&i, &pos);
            }
            result[0] = index[i];
            result[1] = index[pos];
            break;
        }
    }
    return result;
}

int main() {
    int nums[] = {230,863,916,585,981,404,316,785,88,12,70,435,384,778,887,755,740,337,86,92,325,422,815,650,920,125,277,336,221,847,168,23,677,61,400,136,874,363,394,199,863,997,794,587,124,321,212,957,764,173,314,422,927,783,930,282,306,506,44,926,691,568,68,730,933,737,531,180,414,751,28,546,60,371,493,370,527,387,43,541,13,457,328,227,652,365,430,803,59,858,538,427,583,368,375,173,809,896,370,789};
    size_t numSize = sizeof(nums) / sizeof(int);
    int target = 542;
    int* result = twoSum(nums, numSize, target);
    printf("%d, %d\n", result[0], result[1]);
}
