package probems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SwapNodes {
}

class Node {
    Integer data;
    Node left;
    Node right;
    Integer height;

    public Node(Integer data) {
        this.data = data;
        this.left = this.right = null;
    }
}

class Result {

    public static void inOrder(Node root, List<Integer> lst) {
        if (root == null) return;

        inOrder(root.left, lst);
        lst.add(root.data);
        inOrder(root.right, lst);
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> q) {

        /* Building tree using level order as the input is provided*/
        LinkedList<Node> qu = new LinkedList<>();
        Node root = new Node(1);
        root.height = 1;
        qu.offer(root);

        while (!qu.isEmpty()) {
            Node parent = qu.poll();
            List<Integer> childs = indexes.remove(0);
            Integer leftChildData = childs.get(0);
            Integer rightChildData = childs.get(1);

            if (leftChildData != -1) {
                Node leftChild = new Node(leftChildData);
                leftChild.height = parent.height + 1;
                parent.left = leftChild;
                qu.offer(parent.left);
            }
            if (rightChildData != -1) {
                Node rightChild = new Node(rightChildData);
                rightChild.height = parent.height + 1;
                parent.right = rightChild;
                qu.offer(parent.right);
            }
        }

        /* iterate through the k levels to find all levels mutiple to k and swap the left and right nodes of those levels*/
        List<List<Integer>> returnLst = new LinkedList<>();
        for (Integer k : q) {
            List<Integer> swappedInorderLst = new ArrayList<>();
            LinkedList<Node> levelOrderQ = new LinkedList<>();

            levelOrderQ.offer(root);
            while (!levelOrderQ.isEmpty()) {
                Node parent = levelOrderQ.poll();
                if (parent.left != null) {
                    levelOrderQ.offer(parent.left);
                }
                if (parent.right != null) {
                    levelOrderQ.offer(parent.right);
                }
                if (parent.height % k == 0) {
                    // we have hit the level we want. swap the left and right childs
                    Node temp = parent.left;
                    parent.left = parent.right;
                    parent.right = temp;
                }
            }

            inOrder(root, swappedInorderLst);
            returnLst.add(swappedInorderLst);
        }

        return returnLst;
    }

}