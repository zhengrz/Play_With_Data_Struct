import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * 给定两个数组，写一个方法来计算它们的交集。
 * 例如:
 * 给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].

 * 注意：
 * 1. 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 2. 我们可以不考虑输出结果的顺序。
 *
 * 跟进:

 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，你不能一次加载所有的元素到内存中，你该怎么办？

 *
 * @author zhengrz
 * @date 2018/7/19 17:59
 */
public class Solution2 {

    public int[] intersect(int[] nums1, int[] nums2) {

        TreeMap<Integer, Integer> tree = new TreeMap<>();

        for (int num : nums1) {
            if (tree.containsKey(num)) tree.put(num, 1);
            else tree.put(num, tree.get(num) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (tree.containsKey(num)) {
                list.add(tree.get(num));
                tree.remove(num);
            }
        }

        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            ret[i] = list.get(i);
        return ret;
    }

}
