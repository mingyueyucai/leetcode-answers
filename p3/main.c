#include <stdio.h>

int lengthOfLongestSubstring(char* s) {
    int i = 0, j = 0;
    int barrier = 0;
    int longest = 0;
    int nearest[256];
    for (j = 0; j < 256; j++) {
        nearest[j] = -1;
    }
    while (s[i] != 0) {
        if (nearest[s[i]] != -1) {
            if (barrier < nearest[s[i]] + 1) {
                barrier = nearest[s[i]] + 1;
            }
        }
        nearest[s[i]] = i;
        if (i - barrier + 1 > longest) {
            longest = i - barrier + 1;
        }
        i++;
    }
    return longest;
}

int main() {
    printf("%d", lengthOfLongestSubstring("absadfa"));
    return 0;
}