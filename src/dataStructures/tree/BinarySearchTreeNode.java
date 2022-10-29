package dataStructures.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Search Tree implementation
 *
 * @param <T>
 */
public class BinarySearchTreeNode<T extends Comparable<T>> {
    T value;
    private BinarySearchTreeNode left, right;

    public BinarySearchTreeNode(T value) {
        this.value = value;
        this.left = this.right = null;
    }


    public void add(T value) {
        if (this.value.compareTo(value) > 0) {
            if (left == null) {
                left = new BinarySearchTreeNode(value);
            } else {
                left.add(value);
            }
        } else if (this.value.compareTo(value) < 0) {
            if (right == null) {
                right = new BinarySearchTreeNode(value);
            } else {
                right.add(value);
            }
        }
    }

    public BinarySearchTreeNode remove(BinarySearchTreeNode root, T removeValue) {
        if (root == null) return root;
        if (root.value.compareTo(removeValue) == 0) {
//            node to be deleted is found. there are 3 possible cases
//            1. the node has no child
//            2. the node has one child (left || right)
//            3. the node has both left and right child
            /*if (root.left == null && root.right == null)
                return null; // no child found for the element so replace the value with null;
*/
            // when there is only 1 child left || right
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // when the node has both the children
            root.value = minVal(root.right); // replace the current value with the next highest value, so go right and then left
            root.right = remove(root.right, this.value); // remove the replaced element

        } else if (root.value.compareTo(removeValue) > 0) {
            root.left = remove(root.left, removeValue);
        } else {
            root.right = remove(root.right, removeValue);
        }
        return root;
    }

    private T minVal(BinarySearchTreeNode root) {
        T min = value;
        while (root.left != null) { // traversing left of the right subtree to find the next value for replacement
            min = (T) root.left.value;
            root = root.left;
        }
        return min;
    }

    // Traversal of the tree
    public void preOrderTraversal(BinarySearchTreeNode root) {
        if (root != null) {
            visit(root);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public void postOrderTraversal(BinarySearchTreeNode root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            visit(root);
        }
    }

    public void inOrderTraversal(BinarySearchTreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            visit(root);
            inOrderTraversal(root.right);
        }
    }

    public void levelOrderTraversal(BinarySearchTreeNode root) {
        if (root != null) {
            Queue<BinarySearchTreeNode> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);
            while (!nodeQueue.isEmpty()) {
                BinarySearchTreeNode temp = nodeQueue.poll();
                visit(temp);
                if (temp.left != null) nodeQueue.offer(temp.left);
                if (temp.right != null) nodeQueue.offer(temp.right);
            }
        }
    }

    private void visit(BinarySearchTreeNode node) {
        System.out.print(node.value + " ");
    }

    public Integer getHeight(BinarySearchTreeNode root) {
        if (root == null) {
            return -1;
        } else {
            Integer leftHeight = getHeight(root.left);
            Integer rightHeight = getHeight(root.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public Boolean search(BinarySearchTreeNode root, T valueForSearch) {
        if (root == null) return false;
        if (root.value.compareTo(valueForSearch) > 0) {
            return search(root.left, valueForSearch);
        } else if (root.value.compareTo(valueForSearch) < 0) {
            return search(root.right, valueForSearch);
        } else {
            return true;
        }
    }

}
