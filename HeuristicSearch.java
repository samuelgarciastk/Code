import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Author: stk
 * Date: 2016/6/2
 * Time: 16:43
 * http://hihocoder.com/contest/hiho100/problem/1
 * 搜索三·启发式搜索
 */
public class HeuristicSearch {
    static int[] factorial = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    static int[][] dim = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int[] status;
    int g, h, f;

    /**
     * 康托展开
     * @param num 序列
     * @return 序列在全排列中的位置
     */
    public int cantor(int[] num) {
        int x = 0, len = num.length;
        for (int i = 0; i < len; i++) {
            int tp = 0;
            for (int j = i + 1; j < len; j++) {
                if (num[i] > num[j])
                    tp++;
            }
            x += tp * factorial[len - 1 - i];
        }
        return x;
    }

    /**
     * A*算法
     * @return 到达目标的步数
     */
    public int aStarSearch() {
        g = 0;
        h = evaluate(this);
        f = g + h;
        PriorityQueue<HeuristicSearch> openList = new PriorityQueue<>(9, new Comparator<HeuristicSearch>() {
            @Override
            public int compare(HeuristicSearch o1, HeuristicSearch o2) {
                if (o1.f > o2.f) return 1;
                else if (o1.f < o2.f) return -1;
                else return 0;
            }
        });
        openList.add(this);
        boolean[] closeList = new boolean[362880];
        while (!openList.isEmpty()) {
            HeuristicSearch u = openList.poll();
            int x = cantor(u.status);
            if (x == 0) return u.g;//到达目标
            closeList[x] = true;
            int i;
            for (i = 0; i < 9 && u.status[i] != 9; i++);
            int row = i / 3, col = i % 3;//定位
            for (i = 0; i < 4; i++) {
                int newRow = row + dim[i][0];
                int newCol = col + dim[i][1];
                if (0 <= newRow && newRow < 3 && 0 <= newCol && newCol < 3) {
                    HeuristicSearch tmp = new HeuristicSearch();
                    tmp.status = new int[9];
                    for (int k = 0; k < 9; k++) {
                        tmp.status[k] = u.status[k];
                    }
                    tmp.status[3 * row + col] = tmp.status[3 * newRow + newCol];
                    tmp.status[3 * newRow + newCol] = 9;
                    int tmpX = cantor(tmp.status);
                    if (!closeList[tmpX] && canSolve(tmp)) {
                        tmp.g = u.g + 1;
                        tmp.h = evaluate(tmp);
                        tmp.f = tmp.g + tmp.h;
                        openList.add(tmp);
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 估值函数
     */
    public int evaluate(HeuristicSearch status) {
        int ret = 0;
        for (int i = 0; i < 9; i++) {
            if (status.status[i] == 9) continue;
            if (status.status[i] != i + 1) {
                int tmp = Math.abs(status.status[i] - 1 - i);
                ret += (tmp / 3 + tmp % 3);
            }
        }
        return ret;
    }

    /**
     * 状态是否有解
     */
    public boolean canSolve(HeuristicSearch status) {
        int ret = 0;
        for (int i = 0; i < 9; i++) {
            if (status.status[i] == 9) continue;
            for (int j = i + 1; j < 9; j++) {
                if (status.status[j] < status.status[i] && status.status[j] != 9) ret++;
            }
        }
        return ret % 2 == 0;
    }

    /**
     * 输入
     * 第1行：1个正整数t，表示数据组数。1≤t≤8。
     * 接下来有t组数据，每组数据有3行，每行3个整数，包含0~8，每个数字只出现一次，其中0表示空位。
     * 输出
     * 第1..t行：每行1个整数，表示该组数据解的步数。若无解输出"No Solution!"
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            HeuristicSearch heuristicSearch = new HeuristicSearch();
            heuristicSearch.status = new int[9];
            int tmp;
            for (int i = 0; i < 9; i++)
                heuristicSearch.status[i] = ((tmp = scanner.nextInt()) == 0 ? 9 : tmp);
            System.out.println((tmp = heuristicSearch.aStarSearch()) == -1 ? "No Solution!" : tmp);
        }
    }
}
