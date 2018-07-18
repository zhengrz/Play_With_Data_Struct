/**
 * @author zhengrz
 * @date 2018/7/16 17:25
 */
public class Solution3 {



    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(new int[] {  1, 2, 3, 4, 5, 6 });
        System.out.println(new Solution3().removeElements(head, 6));
    }

}
