package dataStructures.tree;

public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<Integer>();
        tree.insert(19);
        tree.insert(5);
        tree.insert(15);
        tree.insert(7);
        tree.insert(48);
        tree.insert(4);
        tree.insert(34);
        tree.insert(24);
        tree.insert(35);
        tree.insert(52);
        tree.insert(-5);
        tree.insert(1);
        tree.levelOrderTraversal();
    }
}
