/*
http://hihocoder.com/contest/hiho98/problem/1
搜索一·24点
*/
#include <iostream>
#include <algorithm>

using namespace std;

double num[4];
bool flag;

double calculate(double a, double b, int op) {
	double ret = 0.0;
	switch (op) {
	case 0: ret = a + b; break;
	case 1: ret = a - b; break;
	case 2: ret = a * b; break;
	case 3: if (b != 0) ret = a / b; break;
	case 4: ret = b - a; break;
	case 5: if (a != 0) ret = b / a; break;
	}
	return ret;
}

bool judgeType(int a, int b, int c) {
	if (calculate(calculate(calculate(num[0], num[1], a), num[2], b), num[3], c) == 24) return true;
	if (calculate(calculate(num[0], num[1], a), calculate(num[2], num[3], c), b) == 24) return true;
	return false;
}

void solve() {
	for (int a = 0; a < 6; a++)
		for (int b = 0; b < 6; b++)
			for (int c = 0; c < 6; c++)
				if (judgeType(a, b, c)) {
					flag = true;
					return;
				}
}
/*
输入
第1行：1个正整数, t，表示数据组数，2≤t≤100。
第2..t+1行：4个正整数, a,b,c,d，1≤a,b,c,d≤10。
输出
第1..t行：每行一个字符串，第i行表示第i组能否计算出24点。若能够输出"Yes"，否则输出"No"。
*/
int main() {
	int t;
	cin >> t;
	while (t--) {
		flag = false;
		cin >> num[0] >> num[1] >> num[2] >> num[3];
		sort(num, num + 4);
		do {
			solve();
			if (flag) break;
		} while (next_permutation(num, num + 4));
		cout << (flag ? "Yes" : "No") << endl;
	}
	return 0;
}
