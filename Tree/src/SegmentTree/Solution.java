package SegmentTree;

/**
 * https://leetcode-cn.com/problems/range-sum-query-immutable/description/
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * 示例：
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 *
 * @author zhengrz
 * @date 2018/7/26 11:19
 */
public class Solution {

    public static class NumArray {

        private SegmentTree<Integer> segmentTree;

        public NumArray(int[] nums) {
            if (nums.length > 0) {
                Integer[] numbers = new Integer[nums.length];
                for (int i = 0; i < nums.length; i++) numbers[i] = nums[i];
                segmentTree = new SegmentTree<Integer>(numbers, (a, b) -> a + b);
            }
        }

        public int sumRange(int i, int j) {
            if (segmentTree == null) throw new IllegalArgumentException("Segment Tree is null");
            return segmentTree.query(i, j);
        }
    }

    public static class NumArray2 {

        private int[] sum;

        public NumArray2(int[] nums) {
            if (nums.length > 0) {
                sum = new int[nums.length];
                sum[0] = nums[0];
                for (int i = 1; i < sum.length; i++) {
                    sum[i] = sum[i - 1] + nums[i];
                }
            }
        }

        /**
         * 复杂度： O(n)
         * @param i
         * @param val
         */
        public void update(int i, int val) {
//            int v = sum[i];
//            for (int index = i; index < sum.length; index ++) {
//                if (index == 0) {
//                    sum[i] = val;
//                    continue;
//                }
//                if (index == i) {
//                    sum[i] = sum[index - 1] + val;
//                    continue;
//                }
//                int c = sum[index];
//                sum[index] = c - v + sum[index -1];
//                v = c;
//            }

            int v = sum[i];  // 临时保存原来值
            sum[i] = i == 0 ? val : sum[i - 1] + val;
            i++;
            for (int index = i; index < sum.length; index ++) {
                int c = sum[index];
                sum[index] = c - v + sum[index -1];
                v = c;
            }
        }


        public int sumRange(int i, int j) {
            if (sum.length <= 0) throw new IllegalArgumentException("Empty Array");
            return i == 0 ? sum[j] : sum[j] - sum[i - 1];
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < sum.length; i ++) {
                s.append(sum[i]);
                if (i != sum.length - 1)
                    s.append(" ");
            }
            return s.toString();
        }
    }

    public static class NumArray3{

        private SegmentTree<Integer> segmentTree;

        public NumArray3(int[] nums) {
            if (nums.length > 0) {
                Integer[] numbers = new Integer[nums.length];
                for (int i = 0; i < nums.length; i ++) numbers[i] = nums[i];
                segmentTree = new SegmentTree<Integer>(numbers, (a, b) -> a + b);
            }
        }

        public void update(int i, int val) {
            if (segmentTree == null) throw new IllegalArgumentException("Segment Tree is Null");
            segmentTree.set(i, val);
        }

        public int sumRange(int i, int j) {
            if (segmentTree == null) throw new IllegalArgumentException("Segment Tree is Null");
            return segmentTree.query(i, j);
        }

    }

    public static void main(String[] args) {
//        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
//        System.out.println(new Solution.NumArray(nums).sumRange(0, 2));
//        System.out.println(new Solution.NumArray(nums).sumRange(2, 5));
//        System.out.println(new Solution.NumArray(nums).sumRange(0, 5));
//
//        System.out.println(new Solution.NumArray2(nums));
//
//        System.out.println(new Solution.NumArray2(nums).sumRange(0, 2));
//        System.out.println(new Solution.NumArray2(nums).sumRange(2, 5));
//        System.out.println(new Solution.NumArray2(nums).sumRange(0, 5));

        int[] nums2 = new int[]{1, 3, 5};
//        NumArray2 n = new Solution.NumArray2(nums2); // 1 4 9 17
//        System.out.println(n);
//        System.out.println(n.sumRange(0, 2));
////        n.update(1, 2); // 1 3 8 16
////        System.out.println(n);
////        System.out.println(n.sumRange(0, 3));
//        n.update(1, 2); // 2 5 10 18
//        System.out.println(n);
//        System.out.println(n.sumRange(0, 2));

        NumArray3 n = new Solution.NumArray3(nums2);
        System.out.println(n.sumRange(0, 2));
        n.update(1, 2);
        System.out.println(n.sumRange(0, 2));


    }


}
