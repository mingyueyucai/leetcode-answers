#include <stdio.h>

int main() {
    int testNum = 0;
    scanf("%d", &testNum);
    int i;
    for (i = 0; i < testNum; i++) {
        int len = 0;
        scanf("%d", &len);
        len++;  // + \n
        getchar();
        int count = 0;
        char lastChar = '0';
        char lastDifferentChar = '0';
        int lastSameLen = 0;
        int sameLen = 0;
        int valid = 0;
        while (len > 0) {
            char ch = getchar();
            len--;
            if (ch == lastChar) {
                sameLen++;
                continue;
            }
            if (lastChar - lastDifferentChar == 1 && count == 1 && sameLen <= lastSameLen) {
                count = 2;
            } else if (lastChar - lastDifferentChar == 1 && count == 2 && sameLen >= lastSameLen) {
                valid = 1;
            } else if (lastChar - lastDifferentChar == 1 && count == 2) {
                count = 2;
            } else {
                count = 1;
            }
            lastSameLen = sameLen;
            lastDifferentChar = lastChar;
            lastChar = ch;
            sameLen = 1;
        }
        if (valid) {
            printf("YES\n");
        } else {
            printf("NO\n");
        }
    }
    return 0;
}