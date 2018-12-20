### 题目

相关链接:[两数相加--力扣](https://leetcode-cn.com/problems/add-two-numbers/submissions/)

给出两个 **非空** 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 **逆序** 的方式存储的，并且它们的每个节点只能存储 **一位** 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

**示例：**

```
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```

代码框架：

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        
    }
```



第一版代码：

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //是否有进位
        int pre=0;
        ListNode node=null;
        ListNode head=null;
        while (l1!=null || l2!=null){

            int val1=l1==null?0:l1.val;
            int val2=l2==null?0:l2.val;
            int sum= val1+val2+pre;
            
            if (sum>=10){
                pre=1;
                sum=sum%10;
            }else{
                pre=0;
            }
            //其他答案中，都先直接定义了一个head节点,避免了每次都进行判断
            if (node==null){
                node= new ListNode(sum);
                head=node;
            }else{
                node.next=new ListNode(sum);
                node=node.next;
            }
            //这里直接移动了l1，l2，最好拷贝一个引用，不修改参数
            if (l1!=null)l1=l1.next;
            if (l2!=null)l2=l2.next;
        }

        if (pre!=0){
            node.next=new ListNode(pre);
        }

        return  head;
    }
}

```



提交后，效率是**53ms**,50%的水平。

### 思考

这道题其实不难，由于数都是**逆序**排列，因此降低了一定的难度。但是在编写这道题的答案的时候，还是遇到了一个问题，总结如下：

- 题想清楚了再写代码
- 对于**链表**的处理,需要分清楚到底是判断`n==null`还是`n.next=null`，在这上面浪费了很大一部分时间
- 这道题有一个点比较容易错，就在于处理进位的时候，在最后应该再判断一次是否还有进位，官方链接给的挺不错的一个测试用例：输入[5],[5]，输出[0],[1]。如果不考虑好，[1]这个进位就会被丢掉。
- 在其他人提交的答案中，都是先拷贝参数`l1`,`l2`，而我却是直接移动了`l1`，`l2`，应该改正，尽量保持方法没有副作用
- 在其他人答案中，使用了一个`head=new ListNode(0)`节点作为头节点，然后返回`head.next`，这样省略了每次判断是不是`null`，提升了效率。
- 在效率最高的人的代码中，是直接将判断条件修改为了`while(p!=null && q!=null)`,当循环结束后，再依次处理比较多的那位数，这样再次节省了多次判断`if(p!=null)p=p.next`，但是代码比较长，如果对效率要求特别高，可以这样优化。

带上优化后的代码：

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
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
}
```

优化后的代码执行是**28ms**,不过应该有误差



















