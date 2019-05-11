package com.jay.java.算法.LeetCode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
 * 并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class TwoListNodeSum {
    //Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 伪代码如下：
     * <p>
     * 将当前结点初始化为返回列表的哑结点。
     * 将进位 carrycarry 初始化为 00。
     * 将 pp 和 qq 分别初始化为列表 l1l1 和 l2l2 的头部。
     * 遍历列表 l1l1 和 l2l2 直至到达它们的尾端。
     * 将 xx 设为结点 pp 的值。如果 pp 已经到达 l1l1 的末尾，则将其值设置为 00。
     * 将 yy 设为结点 qq 的值。如果 qq 已经到达 l2l2 的末尾，则将其值设置为 00。
     * 设定 sum = x + y + carrysum=x+y+carry。
     * 更新进位的值，carry = sum / 10carry=sum/10。
     * 创建一个数值为 (sum \bmod 10)(summod10) 的新结点，并将其设置为当前结点的下一个结点，然后将当前结点前进到下一个结点。
     * 同时，将 pp 和 qq 前进到下一个结点。
     * 检查 carry = 1carry=1 是否成立，如果成立，则向返回列表追加一个含有数字 11 的新结点。
     * 返回哑结点的下一个结点。
     * 请注意，我们使用哑结点来简化代码。如果没有哑结点，则必须编写额外的条件语句来初始化表头的值。
     * <p>
     * 请特别注意以下情况
     * 测试用例	说明
     * l1=[0,1]l1=[0,1]
     * l2=[0,1,2]l2=[0,1,2]	当一个列表比另一个列表长时。
     * l1=[]l1=[]
     * l2=[0,1]l2=[0,1]	当一个列表为空时，即出现空列表。
     * l1=[9,9]l1=[9,9]
     * l2=[1]l2=[1]	求和运算最后可能出现额外的进位，这一点很容易被遗忘
     * <p>
     *
     * 穷举实现
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        //加到最后还有进位的话
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    static int carry = 0;

    /**
     * 递归实现
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null && carry == 0)
            return null;
        int x = (l1 != null) ? l1.val : 0;
        int y = (l2 != null) ? l2.val : 0;
        int sum = x + y + carry;
        ListNode result = new ListNode(sum % 10);
        carry = sum / 10;
        result.next = addTwoNumbers2((l1 != null) ? l1.next : null, (l2 != null) ? l2.next : null);
        return result;
    }

    public static void main(String[] args) {
        /*
         输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
         输出：7 -> 0 -> 8
         原因：342 + 465 = 807
         */
        ListNode listNodeA1 = new ListNode(2);
        ListNode listNodeA2 = new ListNode(4);
        ListNode listNodeA3 = new ListNode(3);
        listNodeA1.next = listNodeA2;
        listNodeA2.next = listNodeA3;

        ListNode listNodeB1 = new ListNode(5);
        ListNode listNodeB2 = new ListNode(6);
        ListNode listNodeB3 = new ListNode(4);
        listNodeB1.next = listNodeB2;
        listNodeB2.next = listNodeB3;

        ListNode result = TwoListNodeSum.addTwoNumbers2(listNodeA1, listNodeB1);
        while (result != null && result.next != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
