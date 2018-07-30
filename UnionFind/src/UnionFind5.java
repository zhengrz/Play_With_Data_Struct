/**
 * @author zhengrz
 * @date 2018/7/30 11:38
 */
public class UnionFind5 implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i ++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }


    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p) {
        if (p < 0 || p > parent.length)
            throw new IllegalArgumentException("p is out of bound");
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];  // 路径压缩
            p = parent[p];
        }
        return p;
    }

    /**
     * 递归写法
     * @param p
     * @return
     */
    private int _find(int p) {
        if (p < 0 || p > parent.length)
            throw new IllegalArgumentException("p is out of bound");
        if (p != parent[p])
            parent[p] = find(parent[p]);
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {

        int pRoot = _find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

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
