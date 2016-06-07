/*
http://hihocoder.com/contest/hiho95/problem/1
数论四·扩展欧几里德
*/
#include <iostream>

using namespace std;

typedef long long LL;

/*
求a和b的最大公约数
*/
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

LL solve(LL s1, LL s2, LL v1, LL v2, LL m) {
	LL a = v1 - v2;
	LL b = m;
	LL c = s2 - s1;
	if (a < 0)
		a += m;
	LL d = gcd(a, b);
	if (c % d)
		return -1;
	a = a / d;
	b = b / d;
	c = c / d;
	LL *temp = extend_gcd(a, b);
	temp[0] = (temp[0] * c) % b;
	while (temp[0] < 0)
		temp[0] += b;
	return temp[0];
}
/*
输入
第1行：每行5个整数s1,s2,v1,v2,m，0≤v1,v2≤m≤1,000,000,000，0≤s1,s2<m
输出
第1行：每行1个整数，表示解，若该组数据无解则输出-1
*/
int main() {
	LL s1, s2, v1, v2, m;
	cin >> s1 >> s2 >> v1 >> v2 >> m;
	cout << solve(s1, s2, v1, v2, m) << endl;
	return 0;
}
