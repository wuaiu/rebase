package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorDfs(root, p, q);
        return ans;
    }

    public boolean lowestCommonAncestorDfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean left = lowestCommonAncestorDfs(root.left, p, q);
        boolean right = lowestCommonAncestorDfs(root.right, p, q);
        if ((left && right) || (((root == p) || (root == q)) && (left || right))) {
            ans = root;
        }
        return left || right;
    }

    public void flatten(TreeNode root) {
        if (root != null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.empty()) {
            TreeNode cur = stack.peek();
            if (pre != null) {
                pre.left = null;
                pre.right = cur;
            }
            pre = cur;
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        while (slow != null && fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (pre != null) {
            pre.next = null;
        }
        TreeNode cur = new TreeNode(slow.val);
        cur.left = sortedListToBST(head);
        cur.right = sortedListToBST(slow.next);
        return cur;
    }

    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode old = null;
        List<TreeNode> errors = new ArrayList<>();
        while (!stack.empty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            stack.pop();
            if (old != null) {
                if (cur.val > old.val) {
                    errors.add(old);
                    errors.add(cur);
                }
            }
            old = cur;
            cur = cur.right;
        }
        if (errors.size() != 0) {
            int temp = errors.get(0).val;
            errors.get(0).val = errors.get(errors.size() - 1).val;
            errors.get(errors.size() - 1).val = temp;
        }
        return;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBSTCheck(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTCheck(TreeNode root, long left, long right) {
        if (root == null) {
            return true;
        }
        if (root.val <= left || root.val >= right) {
            return false;
        }
        return isValidBSTCheck(root.left, left, root.val) && isValidBSTCheck(root.right, root.val, right);
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTreesDfs(1, n);
    }

    public List<TreeNode> generateTreesDfs(int left, int right) {
        List<TreeNode> ans = new ArrayList<>();
        if (left > right) {
            ans.add(null);
            return ans;
        }
        for (int i = left; i <= right; ++i) {
            List<TreeNode> lefts = generateTreesDfs(left, i - 1);
            List<TreeNode> rights = generateTreesDfs(i + 1, right);
            for (TreeNode leftNode : lefts) {
                for (TreeNode rightNode : rights) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = leftNode;
                    cur.right = rightNode;
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(list, root);
        return list;
    }

    public void helper(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            helper(list, node.left);
        }
        list.add(node.val);
        if (node.right != null) {
            helper(list, node.right);
        }
    }
}
