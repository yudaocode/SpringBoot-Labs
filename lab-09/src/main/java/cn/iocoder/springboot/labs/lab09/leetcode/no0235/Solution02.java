package cn.iocoder.springboot.labs.lab09.leetcode.no0235;

/**
 * 递归求解父节点
 */
public class Solution02 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val
            && root.val > q.val) { // 因为 root.val 大于 q.val ，说明 root 的值过大，需要往左走
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val // 因为 root.val 小于 p.val ，说明 root 的值过小，需要往右走
            && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else { // 其他情况，root 都是符合条件的，例如说 root 的泛微在 p and q 之间（或者 q and p 之间)。
                // 为什么直接返回 root 就可以，因为如果不是最接近的父节点，p 和 q 要么在其左边，要么在其右边。
            return root;
        }
    }

}
