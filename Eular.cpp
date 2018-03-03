/*
http://hihocoder.com/contest/hiho93/problem/1
数论二·Eular质数筛法
Eular质数筛法
*/
#include <iostream>

using namespace std;
/*
Inputs 2≤n≤1,000,000
Outputs 1~n中质数个数
*/
int eular(int n) {
	//char中8位分别对应一个数
	char isPrime[125001] = { 0 };
	//位权重
	int weight[8] = { 1, 2, 4, 8, 16, 32, 64, 128 };
	int n;
	//质数列表
	int primeList[n + 1];
	int primeCount = 0;
	for(int i = 2; i <= n; i++) {
		//i / 8是数组中的位置，i % 8是char中的位置
		if(!((isPrime[i >> 3] >> i % 8) & 0x1))
			primeList[++primeCount] = i;
		for(int j = 1; j <= primeCount; j++) {
			int tmp = i * primeList[j];
			if (tmp > n) break;
			isPrime[tmp >> 3] |= weight[tmp % 8];
			if (!(i % primeList[j])) break;
		}
	}
	return primeCount;
}
/*
输入
第1行：1个正整数n，表示数字的个数，2≤n≤1,000,000
输出
第1行：1个整数，表示从1到n中质数的个数
*/
int main() {
	int n;
	cin >> n;
	cout << eular(n) << endl;
	return 0;
}
