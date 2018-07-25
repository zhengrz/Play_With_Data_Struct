import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * 给定两个数组，写一个函数来计算它们的交集。
 * 例子:
 *  给定 num1= [1, 2, 2, 1], nums2 = [2, 2], 返回 [2].
 *
 * 提示:
 *  1. 每个在结果中的元素必定是唯一的。
 *  2. 我们可以不考虑输出结果的顺序。
 *
 * @author zhengrz
 * @date 2018/7/19 17:26
 */
public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {
        // 1. 每个在结果中的元素必定是唯一的。
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer num : nums1)
            set.add(num);

        ArrayList<Integer> list = new ArrayList<>();
        for (Integer num : nums2) {
            if (set.contains(num)) {
                list.add(num);
                set.remove(num); // 删除第一个数组容器交集值，便于即使第二个数组重复出现元素也找不到交集数值，即不会添加到list
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i ++)
            ret[i] = list.get(i);

        return ret;
    }


}
