
/**
 * @author dengchengchao
 * @date 2018/12/19 22:26
 */
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //是否有进位
        int pre=0;
        ListNode head=new ListNode(0);
        ListNode p=l1,q=l2,node=head;

        while (p!=null || q!=null){

            int val1=p==null?0:p.val;
            int val2=q==null?0:q.val;
            int sum= val1+val2+pre;

            if (sum>=10){
                pre=1;
                sum=sum%10;
            }else{
                pre=0;
            }

            node.next=new ListNode(sum);
            node=node.next;

            if (p!=null)p=p.next;
            if (q!=null)q=q.next;
        }

        if (pre!=0){
            node.next=new ListNode(pre);
        }

        return  head.next;
    }

    public static void main(String[] args) {
        Solution solution =new Solution();
        ListNode listNode2=new ListNode(2);
        ListNode listNode4=new ListNode(4);
        ListNode listNode3=new ListNode(3);
        listNode2.next=listNode4;
        listNode4.next=listNode3;

        ListNode listNode5=new ListNode(5);
        ListNode listNode6=new ListNode(6);
        ListNode listNode41=new ListNode(4);
        listNode5.next=listNode6;
        listNode6.next=listNode41;

        ListNode node= solution.addTwoNumbers(listNode2,listNode5);
        while (node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }
}
