/*
http://hihocoder.com/problemset/problem/1015
KMP算法
*/
#include <iostream>
#include <cstring>
#include <string>

using namespace std;

/*
计算next数组
Inputs 模式串p和空next数组
*/
void getNext(const string &p, int *next) {
    int len = p.size();
    next[0] = -1;
    int k = -1;
    int j = 0;
    while (j < len) {
        if (k == -1 || p[k] == p[j]) {
            ++k;
            ++j;
            //优化算法
            if (p[j] != p[k])
                next[j] = k;//原来只有这行
            else
                next[j] = next[k];
        }
        else
            k = next[k];
    }
}
/*
Inputs s为文本串，p为模式串
Outputs s中p的个数
*/
int kmp(const string &s, const string &p) {
    int i = 0, j = 0, sLen = s.size(), pLen = p.size(), num = 0;
    int next[10001];
    getNext(p, next);
    while (i < sLen) {
        if (j == -1 || s[i] == p[j]) {
            i++;
            j++;
        }
        else {
            j = next[j];
        }
        if (j == pLen) {
            num++;
            j = next[j];
        }
    }
    return num;
}
/*
输入
第一行一个整数N，表示测试数据组数。
接下来的N*2行，每两行表示一个测试数据。在每一个测试数据中，第一行为模式串，由不超过10^4个大写字母组成，第二行为原串，由不超过10^6个大写字母组成。
其中N<=20
输出
对于每一个测试数据，按照它们在输入中出现的顺序输出一行Ans，表示模式串在原串中出现的次数。
*/
int main() {
    int n;
    cin >> n;
    string s, p;
    while (n--) {
        cin >> p;
        cin >> s;
        cout << kmp(s, p) << endl;
    }
    return 0;
}