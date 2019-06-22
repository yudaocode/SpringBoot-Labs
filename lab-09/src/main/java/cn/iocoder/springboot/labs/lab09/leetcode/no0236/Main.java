package cn.iocoder.springboot.labs.lab09.leetcode.no0236;

public class Main {

    public static void main(String[] args) {
        if (false) {
            TreeNode node3 = new TreeNode(3);
            TreeNode node5 = new TreeNode(5);
            TreeNode node6 = new TreeNode(6);
            TreeNode node7 = new TreeNode(7);
            TreeNode node4 = new TreeNode(4);
            TreeNode node2 = new TreeNode(2);
            TreeNode node1 = new TreeNode(1);
            node3.left = node5;
            node3.right = node1;
            node5.left = node6;
            node5.right = node2;
            node6.left = node7;
            node6.right = node4;
            TreeNode result = new Solution01().lowestCommonAncestor(node3, node3, node5);
            System.out.println(result.val);
        }
        if (true) {
            TreeNode node3 = new TreeNode(3);
            TreeNode node5 = new TreeNode(5);
            TreeNode node1 = new TreeNode(1);
            TreeNode node6 = new TreeNode(6);
            TreeNode node2 = new TreeNode(2);
            TreeNode node0 = new TreeNode(0);
            TreeNode node8 = new TreeNode(8);
            TreeNode node7 = new TreeNode(7);
            TreeNode node4 = new TreeNode(4);
            node3.left = node5;
            node3.right = node1;
            node5.left = node6;
            node5.right = node2;
            node1.left = node0;
            node1.right = node8;
            node2.left = node7;
            node2.right = node4;
            TreeNode result = new Solution01().lowestCommonAncestor(node3, node5, node4);
            System.out.println(result.val);
        }
    }

}
