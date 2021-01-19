/*
 * Name : Neimat Soliman Ismail
 * ID   : 20180315
 * Group: CS-S2
 * Task : Huffman
 * CS-S2_20180315_NeimatSolimanIsmail_Huffman
 * */
import java.util.*;
class Huffman {
    static class HuffmanNode {

        Integer data;
        char characters;
        HuffmanNode left;
        HuffmanNode right;
    }
    static class MyComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode x, HuffmanNode y)
        {
            return  x.data - y.data;
        }
    }
    public static HashMap<Character, String> map = new HashMap<>();
    public static void CodeForCharacter(HuffmanNode root, String inputWillCompress) {
        if (root.left == null && root.right == null && Character.isLetter(root.characters)) {
            map.put(root.characters,inputWillCompress);
            return;
        }
        CodeForCharacter(root.left, inputWillCompress + "0");
        CodeForCharacter(root.right, inputWillCompress + "1");
    }
    public static HashMap<Character, Integer> FrequencyForCharInString(String string){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < string.length(); i++)
        {
            if(map.containsKey(string.charAt(i))) {
                map.put(string.charAt(i), map.get(string.charAt(i)) + 1);
            }
            else {
                map.put(string.charAt(i), 1);
            }
        }
        return map;
    }
    // main function
    public static void main(String[] args)
    {
        Scanner input=new Scanner(System.in);
        String inputTextWillCompress=input.nextLine();
        int sizeOfInputTextWillCompress=inputTextWillCompress.length();
        HashMap<Character, Integer> mapCharAndFrequency  = new HashMap<>();
        mapCharAndFrequency=FrequencyForCharInString(inputTextWillCompress);
        System.out.println( "Frequency for each Character : "+mapCharAndFrequency);
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(mapCharAndFrequency.size(), new MyComparator());
        for(Map.Entry<Character, Integer> counter: mapCharAndFrequency.entrySet()) {
            HuffmanNode cuurent = new HuffmanNode();

            cuurent.characters = counter.getKey();
            cuurent.data = counter.getValue();

            cuurent.left = null;
            cuurent.right = null;
            queue.add(cuurent);
        }
        HuffmanNode root = null;
        while (queue.size() > 1) {
            HuffmanNode x = queue.peek();
            queue.poll();
            HuffmanNode y = queue.peek();
            queue.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.characters = '-';
            f.left = x;
            f.right = y;
            root = f;
            queue.add(f);
        }
        CodeForCharacter(root, "");
        System.out.println("Code for each Character : "+map);
        String Compressed="";
        for(int i=0;i<inputTextWillCompress.length();i++){
            for(Map.Entry<Character, String> counter: map.entrySet()){
                if(inputTextWillCompress.charAt(i)==counter.getKey()) {
                    Compressed=Compressed+counter.getValue();
                }
            }
        }
        System.out.println("Compressed String is : "+Compressed);
        String Decompress="";
        String temp="";
        for(int i=0;i<Compressed.length();i++){
            temp=temp+ Character.toString(Compressed.charAt(i));
            for(Map.Entry<Character, String> counter: map.entrySet()){
                if(temp.equals(counter.getValue())) {
                    Decompress=Decompress+counter.getKey();
                    temp="";
                    break;
                }
            }
        }
        System.out.println("Decompress String is : "+Decompress);

    }
}
/*test case */
//aaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbcccccccccccccccddddddddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeff