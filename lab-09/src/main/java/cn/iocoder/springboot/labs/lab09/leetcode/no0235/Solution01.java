package cn.iocoder.springboot.labs.lab09.leetcode.no0235;

import java.util.ArrayList;
import java.util.List;

/**
 * 寻找路径，然后匹配
 */
public class Solution01 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // p 节点的路径
        List<TreeNode> pNodes = new ArrayList<>();
        binarySearch(root, p, pNodes);
        // q 节点的路径
        List<TreeNode> qNodes = new ArrayList<>();
        binarySearch(root, q, qNodes);

        // 倒序，对比
        for (int i = pNodes.size() - 1; i >= 0; i--) {
            TreeNode node = pNodes.get(i);
            for (int j = qNodes.size() - 1; j >= 0; j--) {
                TreeNode node2 = qNodes.get(j);
                if (node.val == node2.val) {
                    return node;
                }
            }
        }
        return null;
    }

    private void binarySearch(TreeNode root, TreeNode target, List<TreeNode> nodes) {
        if (root == null) { // 理论不存在，防御性
            return;
        }

        // 添加到路径
        nodes.add(root);

        if (root.val == target.val) {
            return;
        }
        if (root.val > target.val) {
            binarySearch(root.left, target, nodes);
        } else {
            binarySearch(root.right, target, nodes);
        }
    }

}
