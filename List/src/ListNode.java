/**
 * @author zhengrz
 * @date 2018/7/16 11:50
 */
public class ListNode {

   public int val;
   public ListNode next;
   public ListNode(int x) { val = x; }

   public ListNode(int[] arr) {
       if (arr.length == 0)
           throw new IllegalArgumentException("this arr is empty.");

       ListNode cur = this;
       cur.val = arr[0];
       for (int i = 1; i < arr.length; i++) {
           cur.next = new ListNode(arr[i]);
           cur = cur.next;

       }

   }

    // 以当前节点为头结点的链表信息字符串
    @Override
    public String toString(){

        StringBuilder s = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            s.append(cur.val + "->");
            cur = cur.next;
        }
        s.append("NULL");
        return s.toString();
    }

}
