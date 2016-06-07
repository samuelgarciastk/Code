/*
http://hihocoder.com/contest/hiho92/problem/1
数论一·Miller-Rabin质数测试
*/
#include <iostream>
#include <cstdlib>
//单个质数测试的次数
#define TIMES 1
using namespace std;
typedef long long LL;
/*
快速计算(x * y) % n
*/
LL multiMod(LL x, LL y, LL n) {
    LL ret = 0, tmp = x % n;
    while (y) {
        if (y & 0x1) {
            if ((ret += tmp) > n)
                ret -= n;
        }
        if ((tmp <<= 1) > n)
            tmp -= n;
        y >>= 1;
    }
    return ret;
}
/*
快速幂取模(a ^ b) % c
*/
LL powerMod(LL a, LL b, LL c) {
    LL ret = 1;
    while (b) {
        if (b & 0x1)
            ret = multiMod(ret, a, c);
        a = multiMod(a, a, c);
        b >>= 1;
    }
    return ret;
}
/*
Outputs 产生一个随机数
*/
LL random() {
    LL ret = rand();
    return ret * rand();
}
/*
Inputs 2≤n≤10^18
Outputs n是否为质数
*/
bool isPrime(LL n) {
    if (n < 2)
        return false;
    if (n == 2)
        return true;
    //偶数
    if (!(n & 0x1))
        return false;
    LL k = 0, u, x, i;
    //u是指数
    for (u = n - 1; !(u & 0x1); u >>= 1, k++);
    int t = TIMES;
    while (t--) {
        x = powerMod(2 + random() % (n - 2), u, n);
        if (x != 1) {
            for (i = 0; i < k && x != n - 1; i++) {
                x = multiMod(x, x, n);
            }
            if (i >= k) {
                return false;
            }
        }
    }
    return true;
}
/*
输入
第1行：1个正整数t，表示数字的个数，10≤t≤50
第2..t+1行：每行1个正整数，第i+1行表示正整数a[i]，2≤a[i]≤10^18
输出
第1..t行：每行1个字符串，若a[i]为质数，第i行输出"Yes"，否则输出"No"
*/
int main() {
    int amount;
    cin >> amount;
    LL i;
    while (amount--) {
        cin >> i;
        cout << (isPrime(i) ? "Yes" : "No") << endl;
    }
    return 0;
}
