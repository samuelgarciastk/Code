/*
http://hihocoder.com/contest/hiho99/problem/1
搜索二·骑士问题
*/
#include <iostream>
#include <queue>

using namespace std;

int row[8] = { -2,-1,1,2,-2,-1,1,2 };
int col[8] = { -1,-2,-2,-1,1,2,2,1 };
int dis[8][8][3];

struct point
{
	int x, y;
	point(int x, int y) :x(x), y(y) {}
};

void bfs(int x, int y, int n)
{
	dis[x][y][n] = 0;
	queue<point> queue;
	queue.push(point(x, y));
	while (!queue.empty())
	{
		point p = queue.front();
		queue.pop();
		int d = dis[p.x][p.y][n];
		for (int i = 0; i < 8; i++)
		{
			int nx = p.x + row[i];
			int ny = p.y + col[i];
			if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8 || dis[nx][ny][n] >= 0)continue;
			dis[nx][ny][n] = d + 1;
			queue.push(point(nx, ny));
		}
	}
}
/*
输入
第1行：1个正整数t，表示数据组数，2≤t≤10。
第2..t+1行：用空格隔开的3个坐标, 每个坐标由2个字符AB组成，A为'A'~'H'的大写字母，B为'1'~'8'的数字，表示3个棋子的初始位置。
输出
第1..t行：每行1个数字，第i行表示第i组数据中3个棋子移动到同一格的最小行动步数。
*/
int main()
{
	int t;
	cin >> t;
	while (t--)
	{
		memset(dis, -1, sizeof(dis));
		for (int i = 0; i < 3; i++)
		{
			char node[3];
			scanf("%s", node);
			bfs(node[0] - 'A', node[1] - '1', i);
		}
		int min = 1000000;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				int sum = dis[i][j][0] + dis[i][j][1] + dis[i][j][2];
				if (sum < min)
					min = sum;
			}
		}
		cout << min << endl;
	}
	return 0;
}
