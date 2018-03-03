/*
http://hihocoder.com/contest/hiho94/problem/1
数论三·约瑟夫问题
*/
#include <iostream>

using namespace std;
/*
Inputs 总数n，报数k，2≤n≤1,000,000,000，2≤k≤1,000
Outputs 最后剩下的数
*/
int josephus(int n, int k) {
    if (n == 1) return 0;
    int ret;
    if (n < k) {
        ret = 0;
        for (int i = 2; i <= n; i++)
            ret = (ret + k) % i;
        return ret;
    }
    ret = josephus(n - n / k, k);
    if (ret < n % k)
        ret = ret - n % k + n;
    else
        ret = ret - n % k + (ret - n % k) / (k - 1);
    return ret;
}
/*
输入
第1行：1个正整数t，表示多组输入数据，1≤t≤100
第2..t+1行：每行2个正整数n,k，第i+1行表示第i组测试数据，2≤n≤1,000,000,000，2≤k≤1,000
输出
第1..t行：每行1个整数，第i行表示第i组数据的解
*/
int main () {
    int t;
    cin >> t;
    while (t--) {
        int n, k;
        cin >> n >> k;
        cout << josephus(n, k) << endl;
    }
	return 0;
}
