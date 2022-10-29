package dataStructures.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanTreeTesting {
    public static void main(String[] args) {
        HuffmanTreeNode<Character> tree = new HuffmanTreeNode<>();
        List<Character> charArray = Arrays.asList( 'a', 'b', 'c', 'd', 'e', 'f' );
        List<Integer> weights = Arrays.asList(5, 9, 12, 13, 16, 45);
        Integer noOfNodes = charArray.size();

        HuffmanTreeNode root = tree.createHuffmanTree(charArray,weights,noOfNodes);

        Map<Character,String> huffmanCodeMap = new HashMap<>();
        tree.populateHuffmanMap(root,"", huffmanCodeMap);

//        bad, cab, fab, dab, cafe, bed, fed.
        String valToEncode = "cafeddd";
        String encodedString = tree.strHuffmanEncoding(valToEncode,huffmanCodeMap);
        if(encodedString != null){
            String decodedString = tree.decodeHuffmanString(encodedString,root);
            System.out.println(encodedString+" is decoded to "+decodedString);
        } else {
            System.out.println("Unknown character present in the input string");
        }
    }
}
