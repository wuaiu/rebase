package leetcode.stack;


import java.util.*;


class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
        public static void main(String[] args) {
                String str1 = "bcabc";
                Solution solution = new Solution();
                solution.removeDuplicateLetters(str1);
        }

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
                Stack<ListNode> stack1 = new Stack<>();
                Stack<ListNode> stack2 = new Stack<>();
                ListNode cur1 = l1;
                ListNode cur2 = l2;
                while (cur1 != null) {
                        stack1.push(cur1);
                        cur1 = cur1.next;
                }
                while (cur2 != null) {
                        stack2.push(cur2);
                        cur2 = cur2.next;
                }
                boolean hasPlus = false;
                ListNode head = new ListNode(0);
                ListNode cur = head;
                while (!stack1.empty() || !stack2.empty()) {
                        int sum = 0;
                        if (!stack1.empty()) {
                                sum += stack1.pop().val;
                        }
                        if (!stack2.empty()) {
                                sum += stack2.pop().val;
                        }
                        if (hasPlus) {
                                sum += 1;
                        }
                        if (sum >= 10) {
                                sum -= 10;
                                hasPlus = true;
                        }else{
                                hasPlus = false;
                        }
                        cur.next = new ListNode(sum);
                        cur = cur.next;
                }
                if (hasPlus) {
                        cur.next = new ListNode(1);
                }
                cur = head.next;
                ListNode prev = null;
                while (cur != null ) {
                        ListNode next = cur.next;
                        cur.next = prev;
                        prev = cur;
                        cur = next;
                }
                return prev;
        }

        public int[] nextGreaterElements(int[] nums) {
                int[] result = new int[nums.length];
                Stack<Integer> stack = new Stack<>();
                for (int i = nums.length - 1; i >= 0; i--) {
                        int current = nums[i];
                        while (!stack.empty() && current >= stack.peek()) {
                                stack.pop();
                        }
                        result[i] = stack.empty() ? -1 : stack.peek();
                        stack.push(current);
                }
                return result;
        }
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
                Map<Integer, Integer> indexMap = new HashMap<>();
                Stack<Integer> stack = new Stack<>();
                for (int i = nums2.length - 1; i >= 0; i--) {
                        int current = nums2[i];
                        while (!stack.empty() && current >= stack.peek()) {
                                stack.pop();
                        }
                        indexMap.put(current, stack.empty() ? -1 : stack.peek());
                        stack.push(current);
                }
                int[] result = new int[nums1.length];
                for (int i = 0; i < nums1.length; ++i) {
                        result[i] = indexMap.get(nums1[i]);
                }
                return result;
        }
        public String removeDuplicateLetters(String s) {
                Stack<Character> stack = new Stack<>();
                Map<Character, Integer> indexMap = new HashMap<>();

                for (int i = 0; i < s.length(); i++) {
                        indexMap.put(s.charAt(i), i);
                }
                Set<Character> setMap = new HashSet<>();
                for (int i = 0; i < s.length(); i++) {
                        Character c = s.charAt(i);
                        if (!setMap.contains(c)) {
                                while (!stack.empty() && stack.peek() > c && indexMap.get(stack.peek()) > i) {
                                        Character pop = stack.pop();
                                        setMap.remove(pop);
                                }
                                stack.push(c);
                                setMap.add(c);
                        }
                }
                StringBuilder sb = new StringBuilder();
                while (!stack.empty()) {
                        sb.append(stack.pop());
                }
                return sb.reverse().toString();
        }
}
