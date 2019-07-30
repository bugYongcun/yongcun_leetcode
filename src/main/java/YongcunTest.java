import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YongcunTest {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
            val = 0;
        }

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;

        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = head;
        ListNode temp;
        while (node != null) {
            temp = node.next;
            node.next = pre;
            pre = node;
            node = temp;
        }

        return pre;
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode result = new ListNode();
        result.next = head;
        ListNode pre = result;

        int i = 1;
        for (; i < m; i++)
            pre = pre.next;

        // 用双指针,进行链表翻转
        ListNode node = null;
        ListNode cur = pre.next;

        for (; i <= n; i++) {
            ListNode tmp = cur.next;
            cur.next = node;
            node = cur;
            cur = tmp;
        }

        // 将翻转部分 和 原链表拼接
        pre.next.next = cur;
        pre.next = node;
        return result.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // 打印反转前的链表
        ListNode h = head;
        while (null != h) {
            System.out.print(h.val + " ");
            h = h.next;
        }
        // 调用反转方法
        head = reverseBetween(head, 2, 4);

        System.out.println("\n**************************");
        // 打印反转后的结果
        while (null != head) {
            System.out.print(head.val + " ");
            head = head.next;
        }

        System.out.println();
    }
}
