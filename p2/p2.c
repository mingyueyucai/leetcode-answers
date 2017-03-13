#include <stdio.h>
#include <stdlib.h>
#include <math.h>

struct ListNode {
   int val;
   struct ListNode *next;
};

int len(struct ListNode* l)
{
    int n = 0;
    while (l != NULL) {
        n++;
        l = l->next;
    }
    return n;
}

struct ListNode* transform(int* v, int len)
{
    int i, first;
    struct ListNode *h, *p, *q;
    first = 0;
    while (v[first] == 0) {
        first ++;
    }
    if (first >= len) {
        first = 1;
    }
    h = malloc(sizeof(struct ListNode));
    h->val = v[first];
    h->next = NULL;
    p = h;
    for (i = first + 1; i < len; i++) {
        q = malloc(sizeof(struct ListNode));
        q->val = v[i];
        q->next = NULL;
        p->next = q;
        p = q;
    }
    return h;
}

int* transform2(struct ListNode* l, int len)
{
    int* result = malloc(sizeof(int) * len);
    int i;
    for (i = len - 1; i >= 0; i--) {
        result[i] = l->val;
        l = l->next;
    }
    return result;
}

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {
    int len1, len2;
    int *a, *b, *c; 
    int m, i, t;
    struct ListNode *result, *p, *q;
    len1 = len(l1);
    len2 = len(l2);
    a = transform2(l1, len1);
    b = transform2(l2, len2);;
    m = len1 > len2 ? len1 : len2;
    c = malloc((m + 1)* sizeof(int));
    t = 0;
    for (i = 0; i < m; i++) {
        c[i] = (i >= len1 ? 0 : a[i]) + (i >= len2 ? 0 : b[i]) + t;
        if (c[i] > 9) {
            c[i] -= 10;
            t = 1;
        } else {
            t = 0;
        }
    }
    c[m] = t;
    return transform(c, m + 1);
}

int main()
{
    int a[] = {0};
    int b[] = {0};
    int t;
    struct ListNode *ha, *hb, *result;
    ha = transform(a, sizeof(a) / sizeof(int));
    hb = transform(b, sizeof(b) / sizeof(int));
    result = addTwoNumbers(ha, hb);
    while (result != NULL) {
        printf("%d", result->val);
        result = result->next;
    }

}
