#include <cstdio>

#define LOCAL 1
/*
XXXX XXYY XYYX XYXY
四种情况包含了全部的2对数字情况

求每两对相同的数字是一组,求一共有多少组数字
组与组之间元素不重叠
*/
int main() {
    int N, a[10005], result = 0, temp = 0, from = 0;
    scanf("%d", &N);
    for(int i = 0; i < N; i++)
        scanf("%d", &a[i]);
#ifdef LOCAL // 如果定义了LOCAL
    puts("===");
#endif
    for(int i = 0; i < N; i++) {
        for(int j = from; j < i; j++) {
            if(a[i] == a[j]) {
                temp++;
#ifdef LOCAL
                printf("a[%d] == a[%d]: %d\n", i, j, a[i]);
                for(int o = 0; o < N; o++)
                    printf("%d ", a[o]);
                printf("\n");
#endif
                a[i] = a[j] = 0;
                break;
            }
        }
        if(temp == 2)
            result++, temp = 0, from = i;
    }
    printf("%d", result);
    return 0;
}