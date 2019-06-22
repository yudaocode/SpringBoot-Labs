package cn.iocoder.springboot.labs.lab09.leetcode.no0098;

public class Solution02 {

    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode node, Integer min, Integer max) {
        // 节点为空，说明以它为基础的子树，是二叉搜索树
        if (node == null) {
            return true;
        }

        if (min != null && node.val <= min) {
            return false;
        }
        if (max != null && node.val >= max) {
            return false;
        }

        return isValid(node.left, min, node.val) // 左子树
                && isValid(node.right, node.val, max); // 右子树
    }

    public static void main(String[] args) {
//        if (true) {
//            Solution02 solution = new Solution02();
//            TreeNode root = new TreeNode(5);
//            root.left = new TreeNode(1);
//            root.right = new TreeNode(4);
//            root.right.left = new TreeNode(3);
//            root.right.right = new TreeNode(6);
//            System.out.println(solution.isValidBST(root));
//        }
        if (true) {
            Solution02 solution = new Solution02();
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(1);
//            root.right = new TreeNode(4);
//            root.right.left = new TreeNode(3);
//            root.right.right = new TreeNode(6);
            System.out.println(solution.isValidBST(root));
        }
    }

}
