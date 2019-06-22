package cn.iocoder.springboot.labs.lab09.leetcode.no0236;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Solution01 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // p 节点的路径
        List<TreeNode> pNodes = new ArrayList<>();
        search(root, p, pNodes, new AtomicBoolean());
        // q 节点的路径
        List<TreeNode> qNodes = new ArrayList<>();
        search(root, q, qNodes, new AtomicBoolean());

        // 倒序，对比
        for (int i = 0; i < pNodes.size(); i++) {
            TreeNode node = pNodes.get(i);
            for (int j = 0; j < qNodes.size(); j++) {
                TreeNode node2 = qNodes.get(j);
                if (node.val == node2.val) {
                    return node;
                }
            }
        }
        return null;
    }

    private void search(TreeNode root, TreeNode target, List<TreeNode> nodes, AtomicBoolean found) {
        if (root == null) { // 理论不存在，防御性
            return;
        }

        // 如果当前节点，就是要找的，就添加到 nodes 中
        if (root.val == target.val) {
            found.set(true);
            nodes.add(root);
            return;
        }

        // 如果不是，递归子节点
        search(root.left, target, nodes, found);
        if (!found.get()) {
            search(root.right, target, nodes, found);
        }

        // 如果子节点找到，则添加到 nodes 中
        if (found.get()) {
            nodes.add(root);
        }
    }

}
