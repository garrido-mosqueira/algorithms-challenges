package LeetCode.easy;

/*
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree 'tree' is a tree that consists of a node in tree and all of this node's descendants. The tree 'tree' could also be considered as a subtree of itself.

Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false

Constraints:
The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-104 <= root.val <= 104
-104 <= subRoot.val <= 104

LeetCode: https://leetcode.com/problems/subtree-of-another-tree/
Explained solution: https://youtu.be/HdMs2Fl_I-Q

 */

import CodeSignal.topic6_trees.app.Node;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class SubtreeOfAnotherTree {

    public static void main(String[] args) {
//        Input: root = [3,4,5,1,2], subRoot = [4,1,2]

//        [1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2]
//        [1,null,1,null,1,null,1,null,1,null,1,2]

        Node<Integer> one = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> three = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        Node<Integer> five = new Node<>(5);

        three.setLeft(four);
        three.setRight(five);
        four.setLeft(one);
        four.setRight(two);

        System.out.println(isSubtree(three, four));

    }

    public static boolean isSubtree(Node<Integer> root, Node<Integer> subRoot) {
        if(root == null ){
            return false;
        } else if (isSameTree(root, subRoot)){
            return true;
        } else {
            return isSameTree(root.getLeft(), subRoot) || isSameTree(root.getRight(), subRoot);
        }
    }

    private static boolean isSameTree(Node<Integer> root, Node<Integer> subRoot) {
        if (root == null || subRoot == null) {
            return root == subRoot;
        } else if (root.getData().equals(subRoot.getData())) {
            return isSameTree(root.getLeft(), subRoot.getLeft()) && isSameTree(root.getRight(), subRoot.getRight());
        } else {
            return false;
        }
    }

}
