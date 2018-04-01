package com.my.sbt_traversal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author abykovsky
 * @since 11/5/17
 */
public class ConstructBTInorderPostorderTraversal {

    public static void main(String[] args) {                                           //A,  B,  C,  D,  E,  F,  G,  H,  I.
        TreeNode root = new ConstructBTInorderPostorderTraversal().buildTree(new int [] {11, 22, 33, 44, 55, 66, 77, 88, 99},
                new int[] {11, 33, 55, 44, 22, 88, 99, 77, 66});
        System.out.println(root);
        /*TreeNode root = new ConstructBTInorderPostorderTraversal().buildTree(new int [] {11, 22, 33, 44},
                new int[] {33, 22, 44, 11});
        System.out.println(root);*/
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        if(inorder.length == 0) return null;
        if(inorder.length == 1) return new TreeNode(inorder[0]);

        Map<Integer, Integer> inorderMap = new HashMap<>();

        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Integer rootIndex = inorderMap.get(root.val);

        root.left = processSubTree(0, rootIndex - 1, inorderMap, inorder, postorder, rootIndex - 1);
        root.right = processSubTree(rootIndex + 1, inorder.length - 1, inorderMap, inorder, postorder, inorder.length - 2);

        return root;
    }

    private TreeNode processSubTree(int leftBound, int rightBound, Map<Integer, Integer> inorderMap, int[] inorder, int[] postorder, int postorderIndex) {

        if(rightBound - leftBound == 0) return new TreeNode(inorder[leftBound]);
        if(rightBound - leftBound < 0) return null;

        TreeNode parent = new TreeNode(postorder[postorderIndex]);

        Integer parentIndex = inorderMap.get(parent.val);

        parent.left = processSubTree(leftBound, parentIndex - 1, inorderMap, inorder, postorder, calculateIndex(createSet(leftBound, parentIndex - 1, inorder), postorder, parentIndex));
        parent.right = processSubTree(parentIndex + 1, rightBound , inorderMap, inorder, postorder, postorderIndex - 1);

        return parent;
    }

    private Set<Integer> createSet(int leftBound, int rightBound, int[] inorder) {
        Set<Integer> result = new HashSet<>();
        for(int i = leftBound; i <= rightBound; i++) {
            result.add(inorder[i]);
        }
        return result;
    }

    private int calculateIndex(Set<Integer> set, int[] postorder, int postOrderBound) {
        for (int i = postOrderBound; i >= 0; i--) {
            if(set.contains(postorder[i])) {
                return i;
            }
        }
        return 0;
    }
}
