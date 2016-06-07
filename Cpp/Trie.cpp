/*
http://hihocoder.com/problemset/problem/1014
Trie树
*/
#include <iostream>
#include <string>

using namespace std;
/*
节点结构体
*/
struct trieNode
{
	int terminableSize;
	trieNode *child[26];
	trieNode() :terminableSize(0) {
		for (int i = 0; i < 26; i++) {
			child[i] = NULL;
		}
	}
};
/*
Trie树
*/
class trie
{
public:
	trie() {
		root = new trieNode;
	}

	void insert(string str) {
		int len = str.size();
		trieNode *tree = root;
		for (int i = 0; i < len; i++) {
			int index = str[i] - 'a';
			if (!tree->child[index])
				tree->child[index] = new trieNode;
			tree = tree->child[index];
			tree->terminableSize++;
		}
	}

	int search(string str) {
		int len = str.size();
		trieNode *tree = root;
		for (int i = 0; i < len; i++) {
			int index = str[i] - 'a';
			if (!tree->child[index])
				return 0;
			tree = tree->child[index];
		}
		return tree->terminableSize;
	}

private:
	trieNode *root;
};
/*
输入
输入的第一行为一个正整数n，表示词典的大小，其后n行，每一行一个单词（不保证是英文单词，也有可能是火星文单词哦），单词由不超过10个的小写英文字母组成，可能存在相同的单词，此时应将其视作不同的单词。接下来的一行为一个正整数m，表示小Hi询问的次数，其后m行，每一行一个字符串，该字符串由不超过10个的小写英文字母组成，表示小Hi的一个询问。
在20%的数据中n, m<=10，词典的字母表大小<=2.
在60%的数据中n, m<=1000，词典的字母表大小<=5.
在100%的数据中n, m<=100000，词典的字母表大小<=26.
输出
对于小Hi的每一个询问，输出一个整数Ans,表示词典中以小Hi给出的字符串为前缀的单词的个数。
*/
int main() {
	int n, m;
	cin >> n;
	trie trie;
	string s;
	while (n--) {
		cin >> s;
		trie.insert(s);
	}
	cin >> m;
	while (m--) {
		cin >> s;
		cout << trie.search(s) << endl;
	}
	return 0;
}