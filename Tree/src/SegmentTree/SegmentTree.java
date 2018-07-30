package SegmentTree;

/**
 * @author zhengrz
 * @date 2018/7/26 9:57
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;

    private Merger<E> merger;


    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i ++) data[i] = arr[i];
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {

        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 返回父节点索引
     * @param childIndex
     * @return
     */
    private int parent(int childIndex) {
        if (childIndex == 0) throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (childIndex - 1) / 2;
    }

    /**
     * 返回父节点对应的左节点索引
     * @param parentIndex
     * @return
     */
    private int leftChild(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    /**
     * 返回父节点对应的右节点索引
     * @param parentIndex
     * @return
     */
    private int rightChild(int parentIndex) {
        return parentIndex * 2 + 2;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tree.length; i ++) {
            if (tree[i] != null) sb.append(tree[i]);
            else sb.append("null");
            if (i != tree.length -1) sb.append("->");
        }
        return sb.toString();
    }

    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) throw new IllegalArgumentException("index is illegal");
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL , int queryR) {
        if (l == queryL && r == queryR) return tree[treeIndex];
        int mid = l + (r - l) / 2;  // ( l - r ) / 2
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 所查询元素左区间索引 大于 左右区间中间值 也就是  读取的区间在右边  意思是  查找范围在中间值右边
        if (queryL >= mid + 1) return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        // 所查询元素左区间索引 小于 左右区间中间值 也就是  读取的区间在左边  意思是  查找范围在中间值左边
        if (queryR <= mid) return query(leftTreeIndex, l, mid, queryL, queryR);
        // 下面两个求值情况是，所查找范围在中间值左右（包括中间值）
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        // 聚合求值
        return merger.merge(leftResult, rightResult);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= data.length) throw new IllegalArgumentException("index is illegal");
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + ( r - l ) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else
            set(leftTreeIndex, l, mid, index, e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }



    public static void main(String[] args) {
       Integer[] nums = { -2, 0, 3, -5, 2, -1 };
       SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, (a, b) -> a + b);
//       System.out.println(segmentTree);
    }
}
