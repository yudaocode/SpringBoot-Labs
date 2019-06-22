package cn.iocoder.springboot.labs.lab09.leetcode.no0236;

public class Solution02 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果递归，当前节点 root 就是 p 或者 q ，则直接返回
        if (root == null || root == p || root == q) {
            return root;
        }

        // 遍历左子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 遍历右子节点
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 上述，因为是先递归，后判断，所以一定会先招到最接近的。然后，就不断向上，返回最接近的了。

        // 判断父节点
        if (left == null) { // 左子树没找到，那就选择右子树。
            return right;
        }
        if (right == null) { // 右子树没找到，那就选择左子树。
            return left;
        }
        return root; // 如果左右子树都找到，说明 root 是它们的父节点
    }

}
