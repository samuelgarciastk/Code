/*
http://hihocoder.com/contest/hiho97/problem/1
数论六·模线性方程组
*/
#include <iostream>

using namespace std;

typedef long long LL;

LL gcd(LL a, LL b) {
	if (b == 0)
		return a;
	return gcd(b, a % b);
}

LL* extend_gcd(LL a, LL b) {
	LL *ret = new LL[2];
	if (a % b == 0) {
		ret[0] = 0;
		ret[1] = 1;
		return ret;
	}
	LL *temp = extend_gcd(b, a % b);
	ret[0] = temp[1];
	ret[1] = temp[0] - (a / b) * temp[1];
	delete[] temp;
	return ret;
}

LL modularLinear(LL* m, LL* r, int n) {
	LL M = m[0], R = r[0];
	for (int i = 1; i < n; i++) {
		LL d = gcd(M, m[i]);
		LL c = r[i] - R;
		if (c % d)//无解的情况
			return -1;
		LL *k = extend_gcd(M / d, m[i] / d);//扩展欧几里德计算
		k[0] = (c / d * k[0]) % (m[i] / d);//扩展解系
		R = R + k[0] * M;
		M = M / d * m[i];//求解lcm(M, m[i])
		R %= M;//求解合并后的新R，同时让R最小
	}
	if (R < 0)
		R += M;
	return R;
}

int main() {
	int n;
	cin >> n;
	LL m[n], r[n];
	for (int i = 0; i < n; i++)
		cin >> m[i] >> r[i];
	cout << modularLinear(m, r, n) << endl;
	return 0;
}
