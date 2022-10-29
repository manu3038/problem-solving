package dataStructures.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.baeldung.com/java-avl-trees -> refer this link for detailed explanation.
 *
 * AVL Tree is also known as Height balanced Binary Search Tree.
 * After every manipulation(insert || delete) of the tree we re-balance the height of the sides(right and left)
 *
 * @param <T>
 */
public class AVLTree<T extends Comparable> {
    private Node root = null;

    public Node getRoot() {
        return root;
    }

    public int getHeight(Node node) {
        return node != null ? node.height : -1;
    }

    public void insert(T value) {
        root = add(root, value);
    }

    public void updateHeight(Node node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public int getBalanceFactor(Node a) {
        return a != null ? getHeight(a.right) - getHeight(a.left) : 0;
    }

    public void levelOrderTraversal() {
        if (root != null) {
            Queue<Node> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);
            while (!nodeQueue.isEmpty()) {
                Node temp = nodeQueue.poll();
                visit(temp);
                if (temp.left != null) nodeQueue.offer(temp.left);
                if (temp.right != null) nodeQueue.offer(temp.right);
            }
        }
    }
    private Node rightRotation(Node currParent) {

        Node leftChild = currParent.left;
        Node leftRightSubChild = leftChild.right;

        leftChild.right = currParent;
        currParent.left = leftRightSubChild;
        updateHeight(currParent);
        updateHeight(leftChild);
        return leftChild; // this is now the parent node after right to right rotation
    }

    private Node leftRotation(Node currParent) {
        Node rightChild = currParent.right;
        Node rightLeftSubChild = rightChild.left;

        rightChild.left = currParent;
        currParent.right = rightLeftSubChild;

        updateHeight(currParent);
        updateHeight(rightChild);
        return rightChild; // this is now the parent node after left to left rotation
    }

    private Node add(Node root, T value) {
        if (root == null) return new Node(value);
        if (root.value.compareTo(value) > 0) {
            root.left = add(root.left, value);
        } else if (root.value.compareTo(value) < 0) {
            root.right = add(root.right, value);
        } else {
            throw new RuntimeException("Duplicate element is not allowed in AVL Tree");
        }
        return rebalance(root);
    }

    private Node rebalance(Node root) {
        updateHeight(root);
        int balance = getBalanceFactor(root);
        if (balance > 1) {
            if (root.left == null || (getHeight(root.right.right) > getHeight(root.right.left))) {
                root = leftRotation(root);
            } else {
                root.right = rightRotation(root.right);
                root = leftRotation(root);
            }
        } else if (balance < -1) {
            if (root.right == null || (getHeight(root.left.left) > getHeight(root.right.left))) {
                root = rightRotation(root);
            } else {
                root.left = leftRotation(root);
                root = rightRotation(root);
            }
        }
        return root;
    }

    private void visit(Node node) {
        System.out.print(node.value + " ");
    }

    private class Node {
        T value;
        int height;
        Node left, right = null;

        Node(T value) {
            this.value = value;
        }
    }

}
