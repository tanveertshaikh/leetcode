package validbst;

/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTUtil(TreeNode root, long min, long max) {
        if(root == null)
            return true;

        if(root.val <= min || root.val >= max)
            return false;

        return isValidBSTUtil(root.left, min, root.val) &&
                isValidBSTUtil(root.right, root.val, max);
    }
}
