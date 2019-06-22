package cn.iocoder.springboot.labs.lab09.leetcode.no0235;

/**
 * {@link Solution02} 的改进，非递归方式。
 */
public class Solution03 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (p.val < root.val
                    && root.val > q.val) { // 因为 root.val 大于 q.val ，说明 root 的值过大，需要往左走
                root = root.left;
            } else if (p.val > root.val // 因为 root.val 小于 p.val ，说明 root 的值过小，需要往右走
                    && root.val < q.val) {
                root = root.right;
            } else { // 其他情况，root 都是符合条件的，例如说 root 的泛微在 p and q 之间（或者 q and p 之间)。
                return root;
            }
        }
    }

}
