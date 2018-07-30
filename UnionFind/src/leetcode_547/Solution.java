package leetcode_547;

import java.util.TreeSet;

/**
 * 547. 朋友圈 (https://leetcode-cn.com/problems/friend-circles/description/)
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * 示例 1:
 * 输入:
 *      [[1,1,0],
 *      [1,1,0],
 *      [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 *      第2个学生自己在一个朋友圈。所以返回2。
 *
 * @author zhengrz
 * @date 2018/7/30 12:03
 */
public class Solution {
    private interface UF {
        int getSize();
        boolean isConnected(int p, int q);
        void UnionElements(int p, int q);
    }
    private class UnionFind implements UF {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i ++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int p) {
            if (p < 0 || p > parent.length)
                throw new IllegalArgumentException("p is out of bound");

            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        @Override
        public int getSize() {
            return parent.length;
        }

        @Override
        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        @Override
        public void UnionElements(int p, int q) {

            int pRoot = find(p);
            int qRoot = find(q);
            if (rank[pRoot] < rank[qRoot]) {
                parent[pRoot] = qRoot;
            } else if (rank[pRoot] > rank[qRoot]) {
                parent[qRoot] = pRoot;
            } else {
                parent[pRoot] = qRoot;
                rank[qRoot] += 1;
            }
        }
    }

    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind unionFind = new UnionFind(n);

        for (int i = 0; i < n; i ++)
            for (int j = 0; j < i; j ++)
                if (M[i][j] == 1)
                    unionFind.UnionElements(i, j);
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < n; i ++)
            treeSet.add(unionFind.find(i));
        return treeSet.size();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] m = new int[][]{ new int[] {1, 1, 0}, new int[] { 1, 1, 0 }, new int[] { 0, 0, 1 }};
        System.out.println(s.findCircleNum(m));

        m = new int[][]{ new int[] {1, 1, 0}, new int[] { 1, 1, 1 }, new int[] { 0, 1, 1 }};
        System.out.println(s.findCircleNum(m));
    }

}
