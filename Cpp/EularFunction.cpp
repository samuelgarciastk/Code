/*
http://hihocoder.com/contest/hiho96/problem/1
数论五·欧拉函数
*/
#include <iostream>

using namespace std;
/*
Inputs 2≤l≤r≤5,000,000
Outputs [l,r]中φ(n)最小并且值也最小的数
*/
int eularFunction(int l, int r) {
	//char中8位分别对应一个数
	char isPrime[625001] = { 0 };
	//位权重
	int weight[8] = { 1,2,4,8,16,32,64,128 };
	//质数列表
	int *primeList = new int[r + 1];
	//欧拉函数值φ(n)
	int *phi = new int[r + 1];
	int primeCount = 0;
	for (int i = 2; i <= r; i++) {
		//i / 8是数组中的位置，i % 8是char中的位置
		if (!((isPrime[i >> 3] >> i % 8) & 0x1)) {
			primeList[++primeCount] = i;
			//质数p的欧拉函数为p-1
			phi[i] = i - 1;
		}
		for (int j = 1; j <= primeCount; j++) {
			int temp = i * primeList[j];
			if (temp > r)break;
			isPrime[temp >> 3] |= weight[temp % 8];
			if (i % primeList[j] == 0)
				//primeList[j]是i的约数，φ(n*p) = φ(n) * p
				phi[temp] = phi[i] * primeList[j];
			else
				//primeList[j]不是i的约数，φ(n*p) = φ(n) * (p-1)
				phi[temp] = phi[i] * (primeList[j] - 1);
		}
	}
	//查找最小值k
	int k = l, min = phi[l];
	for (int i = l + 1; i <= r; i++) {
		if (phi[i] < min) {
			k = i;
			min = phi[i];
		}
	}
	delete[] primeList;
	delete[] phi;
	return k;
}
/*
输入
第1行：2个正整数，l，r，2≤l≤r≤5,000,000
输出
第1行：1个整数，表示满足要求的数字K
*/
int main() {
	int l, r;
	cin >> l >> r;
	cout << eularFunction(l, r) << endl;
	return 0;
}
