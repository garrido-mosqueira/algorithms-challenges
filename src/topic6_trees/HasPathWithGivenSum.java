package topic6_trees;

import topic6_trees.app.BinaryTree;
import topic6_trees.app.Node;
import topic6_trees.app.Tree;

/**
 * https://app.codesignal.com/interview-practice/task/TG4tEMPnAc3PnzRCs
 */
public class HasPathWithGivenSum {

    static boolean hasPathWithGivenSum(Node<Integer> t, int s) {
        //If just one of left or right was null, then it was not a child node and false can be returned safely
        if(t == null) return false;

        //If this is a child AND sum is input, then we have a path
        if(t.getLeft() == null && t.getRight() == null) {
            return (s == t.getData());
        }

        return hasPathWithGivenSum(t.getLeft(), s-t.getData()) || hasPathWithGivenSum(t.getRight(), s-t.getData());
    }

    public static void main(String[] args) {

        Tree<Integer> tree = new BinaryTree<>();

        Node<Integer> treeRoot = tree.getRoot();

        boolean givenSum = hasPathWithGivenSum(treeRoot, 7);

        System.out.println(givenSum);

    }


}
