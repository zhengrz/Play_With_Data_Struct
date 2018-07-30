/**
 * @author zhengrz
 * @date 2018/7/30 9:49
 */
public class UnionFind1 implements UF {

    private int[] ids;

    public UnionFind1(int size) {
        ids = new int[size];
        for (int i = 0; i < size; i ++)
            ids[i] = i;

    }

    @Override
    public int getSize() {
        return ids.length;
    }

    private int find(int p) {
        return ids[p];
    }

    @Override
    public boolean isConnected(int p, int q) {


        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pID = ids[p];
        int qID = ids[q];

        if (pID == qID)
            return;
        for (int i = 0; i < ids.length; i ++)
            if (ids[i] == pID)
                ids[i] = qID;
    }
}
