package leetcode.tree;

import java.util.Stack;

public class BSTIterator {
    private Stack<TreeNode> stack;
    private TreeNode cur;
    public BSTIterator(TreeNode root) {
        stack = new Stack();
        cur = root;

    }

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        TreeNode node = stack.pop();
        cur = cur.right;
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty() && cur != null;
    }

}
