import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();

        int[] nums = new int[] { 5, 3, 2, 8, 6, 9, 4 };

        for (int num : nums)
            bst.add(num);

        //////////    5     ///////////
        /////     /     \       //////
        /////    3       8      //////
        /////  /  \    /  \     //////
        ////  2    4   6   9   ///////


        // 测试二分搜索树的size()
        System.out.println("size: " + bst.getSize());
        System.out.println();

        // 测试二分搜索树的前序遍历 preOrder
        System.out.println("preOrder: ");
        bst.preOrder();
        System.out.println();

        // 测试二分搜索树的中序遍历 inOrder 中序排序可用于排序(升序)
        System.out.println("inOrder: ");
        bst.inOrder();
        System.out.println();

        // 测试二分搜索树的后序遍历 postOrder  后序遍历可用于释放二叉搜索树
        System.out.println("postOrder: ");
        bst.postOrder();
        System.out.println();

        // 层序遍历
        System.out.println("levelOrder: ");
        bst.levelOrder();
        System.out.println();

        // 测试 removeMin
        // 输出的元素应该是从小到大排列的
        System.out.println("Test removeMin: ");
        while (!bst.isEmpty()) {
            System.out.print("min: " + bst.mininum() + " , ");
            bst.removeMin();
            System.out.println("After removeMin, size = " + bst.getSize());
        }
        System.out.println();

        for (int num : nums)
            bst.add(num);

        //////////    5     ///////////
        /////     /     \       //////
        /////    3       8      //////
        /////  /  \    /  \     //////
        ////  2    4   6   9   ///////

        // 测试 removeMax
        // 输出的元素应该是从大到小排列的
        System.out.println("Test removeMax: ");
        while (!bst.isEmpty()) {
            System.out.print("max: " + bst.maxnum() + " , ");
            bst.removeMax();
            System.out.println("After removeMax, size = " + bst.getSize());
        }

    }
}
