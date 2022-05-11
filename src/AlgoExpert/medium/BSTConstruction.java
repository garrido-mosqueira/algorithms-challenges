package AlgoExpert.medium;

/*
BS Construction:

Write a BST class for a Binary Search Tree. The class should support:
- Inserting values with the insert method.
- Removing values with the remove method; this method should only remove the first instance of a given value.
- Searching for values with the contains method.

Note that you can't remove values from a single-node tree. In other words, calling the remove method on a single-node tree should simply not do anything.
Each BST node has an integer value, a left child node, and a right child node. A node is said to be a valid BST node if and only if it satisfies the BST
property: its value is strictly greater than the values of every node to its left; its value is less than or equal to the values of every node to its right; and its
children nodes are either valid BST nodes themselves or None / null

Sample Usage
// Assume the following BST has already been created:
            10
          /    \
         5      15
        / \    /  \
       2   5  13   22
      /         \
     1           14

// All operations below are performed sequentially.
insert(12):
            10
          /    \
         5      15
        / \    /  \
       2   5  13   22
      /      /  \
     1      12   14

remove(10):
            12
          /    \
         5      15
        / \    /  \
       2   5  13   22
      /         \
     1          14

contains (15): true

 */
public class BSTConstruction {

    public static void main(String[] args) {
        BST binarySearchTree = new BST(10);

        binarySearchTree.insert(5);
        binarySearchTree.insert(15);
        binarySearchTree.insert(2);
        binarySearchTree.insert(5);
        binarySearchTree.insert(13);
        binarySearchTree.insert(22);
        binarySearchTree.insert(1);
        binarySearchTree.insert(14);

        System.out.println("debug until here");

        binarySearchTree.insert(12);
        System.out.println("now should have a 12");

        binarySearchTree.remove(10);
        System.out.println("now the root should be 12");

        System.out.println("contains 15: " + binarySearchTree.contains(15));

    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }

        public BST insert(int value) {
            BST currentNode = this;
            while (true) {
                if (value < currentNode.value) {
                    if (currentNode.left != null) {
                        currentNode = currentNode.left;
                    } else {
                        currentNode.left = new BST(value);
                        break;
                    }
                } else {
                    if (currentNode.right != null) {
                        currentNode = currentNode.right;
                    } else {
                        currentNode.right = new BST(value);
                        break;
                    }
                }
            }
            return this;
        }

        public boolean contains(int value) {
            BST currentNode = this;
            while (currentNode != null) {
                if (value < currentNode.value) {
                    currentNode = currentNode.left;
                } else if (value > currentNode.value) {
                    currentNode = currentNode.right;
                } else {
                    return true;
                }
            }
            return false;
        }

        public BST remove(int value) {

            return this;
        }
    }

}
