package leetcode.listnode;

public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode firstEnd = slow;
        ListNode secondStart = reverseList(firstEnd.next);

        ListNode cur1 = head;
        ListNode cur2 = secondStart;
        while (cur1 != null && cur2 != null) {
            if (cur1.val != cur2.val) {
                return false;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        firstEnd.next = reverseList(secondStart);
        return true;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int length = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (left <= right && sum >= target) {
                if (length == 0) {
                    length = right - left + 1;
                } else {
                    length = Math.min(right - left + 1, length);
                }
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return length;
    }
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    public ListNode removeElements(ListNode head, int val) {
        ListNode beforeHead = new ListNode(0);
        beforeHead.next = head;
        ListNode cur = head;
        ListNode pre = beforeHead;
        while (cur != null) {
            ListNode next = cur = cur.next;
            if (cur.val == val) {
                pre.next = next;
                cur.next = null;
                cur = next;
            }else{
                pre = cur;
                cur = next;
            }
        }
        return beforeHead.next;
    }
}
