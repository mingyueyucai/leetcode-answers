#include <stdio.h>
#include <stdlib.h>

struct ListNode {
   int val;
   struct ListNode *next;
};

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

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {
    int needCarry = 0;
    struct ListNode *x, *h, *pre;
    h = malloc(sizeof(struct ListNode));
    pre = h;
    while (l1 != NULL || l2 != NULL) {
        printf("%d, %d", l1->val, l2->val);
        x = malloc(sizeof(struct ListNode));
        x->val = (l1 == NULL ? 0 : l1->val) + (l2 == NULL ? 0 : l2->val)
                + (needCarry ? 1 : 0);
        needCarry = x->val >= 10;
        x->val = needCarry ? x->val - 10 : x->val;
        pre->next = x;
        pre = x;
        if (l1 != NULL) {
            l1 = l1->next;
        }
        if (l2 != NULL) {
            l2 = l2->next;
        }
    }
    return h->next;
}

int main()
{
    int a[] = {1, 2, 3};
    int b[] = {2, 9, 8};
    int t;
    struct ListNode *ha, *hb, *result;
printf("%d", 1);
    ha = transform(a, sizeof(a) / sizeof(int));
    hb = transform(b, sizeof(b) / sizeof(int));
    result = addTwoNumbers(ha, hb);
    while (result != NULL) {
        printf("%d", result->val);
        result = result->next;
    }

}
