/**
 *
 * 删除链表中的节点
 *
 *  删除链表中等于给定值 val 的所有节点。
 *
 *  示例:
 *      输入: 1->2->6->3->4->5->6, val = 6
 *      输出: 1->2->3->4->5
 *
 * @author zhengrz
 * @date 2018/7/16 11:25
 */

public class Solution {

    public ListNode removeElements(ListNode head, int val) {
        while(head != null && head.val == val) {
            ListNode delNote = head;
            head = delNote.next;
            delNote.next = null;
        }

        if (head == null) return head;

        for (ListNode c = head; c.next != null;) {
            if (c.next.val == val) {
                ListNode delNote = c.next;
                c.next = delNote.next;
                delNote.next = null;
            } else
                c = c.next;
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 10; i++)
            list.addFirst(i);

        System.out.println(list);

    }


}