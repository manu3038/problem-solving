package dataStructures.tree;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/ -> refer this link for understanding the huffman algorithm to build a tree
 * @param <T>
 */
class HuffmanTreeNode<T> implements Comparable<HuffmanTreeNode> {
    Integer count;
    T data;

    HuffmanTreeNode left;
    HuffmanTreeNode right;
    private HuffmanTreeNode root = null;

    public HuffmanTreeNode() {
    }

    private HuffmanTreeNode(Integer count, T data) {
        this.count = count;
        this.data = data;
        this.left = this.right = null;
    }

    private HuffmanTreeNode(Integer count, T data, HuffmanTreeNode left, HuffmanTreeNode right) {
        this.count = count;
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public HuffmanTreeNode getRoot() {
        return root;
    }

    public HuffmanTreeNode createHuffmanTree(List<T> dataList, List<Integer> weights, Integer noOfNodes) {
        PriorityQueue<HuffmanTreeNode> queue = new PriorityQueue(noOfNodes);

        //create min heap tree for dataList and weights.
        for (int i = 0; i < noOfNodes; i++) {
            HuffmanTreeNode weightedNode = new HuffmanTreeNode(weights.get(i), dataList.get(i));
            queue.add(weightedNode);
        }

        // while queue doesn't have only one node keep building huffman tree.
        while (queue.size() != 1) {
            HuffmanTreeNode firstMin = queue.poll();
            HuffmanTreeNode secondMin = queue.poll();

            Integer newNodeCount = secondMin.count + firstMin.count;
            HuffmanTreeNode internalNode = new HuffmanTreeNode(newNodeCount, "*", firstMin, secondMin);
            root = internalNode;
            queue.add(internalNode); // add internal node back to queue so that it can have a parent built if necessary
        }
        root = queue.poll(); // last remaining node is the root node for the entire Huffman tree
        return root;
    }

    public void levelOrderTraversal(HuffmanTreeNode root) {
        if (root != null) {
            Queue<HuffmanTreeNode> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);
            while (!nodeQueue.isEmpty()) {
                HuffmanTreeNode temp = nodeQueue.poll();
                visit(temp);
                if (temp.left != null) nodeQueue.offer(temp.left);
                if (temp.right != null) nodeQueue.offer(temp.right);
            }
        }
    }

    public void populateHuffmanMap(HuffmanTreeNode node, String code, Map<T, String> codeMap) {
        if (node.left == null && node.right == null) {
            // this the leaf node or external node print the code created for it
            visit(node);
            System.out.println("code => " + code);
            codeMap.put((T) node.data,code);
            return;
        }
            populateHuffmanMap(node.left, code + "0", codeMap); // Add '0' when going to left
            populateHuffmanMap(node.right, code + "1", codeMap);

    }

    public String decodeHuffmanString(String encodedString, HuffmanTreeNode rootNode){
        char[] encodedStringArr = encodedString.toCharArray();
        StringBuilder strbuild = new StringBuilder();
        HuffmanTreeNode nextNode = rootNode;
        for (int i = 0; i < encodedStringArr.length; i++) {
            if(encodedStringArr[i] == '0'){ // if zero move to left of the tree
                nextNode = nextNode.left;
            } else if (encodedStringArr[i] == '1'){ // if one move to right of the tree
                nextNode = nextNode.right;
            }
            if(nextNode.left ==null && nextNode.right ==null & nextNode !=null){
                // if the leaf/ external node add to string and reset the nextnode back to root
//                visit(nextNode);
                strbuild.append(nextNode.data);
                nextNode = rootNode;
            }
        }
        return strbuild.toString();
    }

    public String strHuffmanEncoding(String input, Map<T, String> huffmanCodeMap){
        StringBuffer encodedString = new StringBuffer();
        char[] inputCharArray = input.toCharArray();
        for(char c : inputCharArray){
            String code = huffmanCodeMap.get((Character) c);
            if(code == null){
                return null;
            }
            encodedString.append(code);

        }
        System.out.println(input +" encoded string is " + encodedString);
        return encodedString.toString();
    }
    private void visit(HuffmanTreeNode node) {
        System.out.print(node.data + " -> " + node.count + "\n");
    }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        return this.count - o.count;
    }
}

