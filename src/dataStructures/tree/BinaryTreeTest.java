package dataStructures.tree;

public class BinaryTreeTest {
    public static void main(String[] args) {
        BinarySearchTreeNode< Integer > root = new BinarySearchTreeNode(19);
        root.add(5);
        root.add(15);
        root.add(7);
        root.add(48);
        root.add(4);
        root.add(34);
        root.add(24);

        root.preOrderTraversal(root);
        System.out.println();

        root.remove(root, 19);
        root.add(35);
        root.add(52);
        root.add(-5);
        root.add(1);
        root.preOrderTraversal(root);
        System.out.println();

        Integer height = root.getHeight(root);
        System.out.println(height);


        root.remove(root, -5);
        root.levelOrderTraversal(root);
        System.out.println();

        System.out.println(root.search(root, 19));
    }

}
