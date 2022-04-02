import java.util.*;

class Huffman {
    private static ArrayList<String> res = new ArrayList<>();

    private static class Node {
        char c;
        int count;
        Node left;
        Node right;
    }

    public static void main (String[] args){
        String ex1 = "aabbbbbcDDD";
        HashMap<Character, Integer> res1 = countFreq(ex1);
        // printMap(res1);
        Node root = mapToTree(res1);
        System.out.println(root.c + " " + root.count);
        constructHuffman(root, "");
        for(String s : res){
            System.out.println(res);
        }

    }

    private static Node mapToTree(HashMap<Character, Integer> map){
        /*            
            #          (#, 3)
            #         /       \
            #      (a, 2)    (c,1)  
        */
        
        //Create minheap of smallest nodes
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> (a.count - b.count));
        for(char c : map.keySet()){
            Node cur = new Node();
            cur.count = map.get(c);
            cur.c = c;
            cur.left = null;
            cur.right = null;
            minHeap.add(cur);
        }

        Node root = null; // to return
        while(minHeap.size() > 1){
            Node temp1 = minHeap.poll();
            Node temp2 = minHeap.poll();
            Node parent = new Node();
            parent.count = temp1.count + temp2.count;
            parent.c = '#';
            parent.left = temp1;
            parent.right = temp2;
            root = parent;
            minHeap.add(parent);
        }
    
        return root;
    }

    private static HashMap<Character, Integer> countFreq(String str){
        if(str == null || str.length() == 0) return null;
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : str.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    private static void constructHuffman(Node root, String s){
        if(root == null) return;
        if(root.left == null && root.right == null && Character.isLetter(root.c)){
            res.add(s);
            return;
        }
        constructHuffman(root.left, s + "0");
        constructHuffman(root.right, s + "1");
    }

    private static void printMap(HashMap<Character, Integer> map){
        for(char c : map.keySet()){
            System.out.println(c + ":" + map.get(c));
        }
    }


}