package com.my.binary_tree_paths;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abykovsky
 * @since 4/1/18
 */
public class Solution {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(root, "", result);
        return result;
    }

    private void dfs(TreeNode root, String path, List<String> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result.add(path + (path.isEmpty()? "": "->") + root.val);
            return;
        }
        dfs(root.left, path + (path.isEmpty()? "": "->") + root.val, result);
        dfs(root.right, path + (path.isEmpty()? "": "->") + root.val, result);
    }
}
