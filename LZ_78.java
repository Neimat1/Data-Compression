import java.util.ArrayList;
import java.util.Scanner;
public class LZ_78 {
    public static int indexOfStringInDic(String word, ArrayList<String> Dictionary) {
        return Dictionary.indexOf(word);
    }
    public static boolean StringNotFound(String word, ArrayList<String> arraySearch) {
        for(int i=0;i<arraySearch.size();i++){
            if(word.equals(arraySearch.get(i)))
                return false;
        }
        return true ;
    }
    public static void Decompress(ArrayList<String>Dictionary, ArrayList<Integer>positionTag, ArrayList<String>nextCharTag){
        String decompress="";
        for(int i=0;i<positionTag.size();i++){
            decompress=decompress+Dictionary.get(positionTag.get(i))+nextCharTag.get(i);
        }
        if(nextCharTag.get(positionTag.size()-1).equals("null"))
            decompress=decompress.substring(0,decompress.length()-4);
        System.out.println(decompress);
    }
    public static void main(String[] args) {
        ArrayList<String> Dictionary    = new ArrayList<>();
        ArrayList<Integer> positionTag  = new ArrayList<>();
        ArrayList<String> nextCharTag   = new ArrayList<>();
        Dictionary.add("");
        String inputWillCompressed,charInputAsString = null;
        Scanner scannerInput=new Scanner(System.in);
        inputWillCompressed=scannerInput.nextLine();
        for (int i = 0;i< inputWillCompressed.length();i++ ) {
            charInputAsString = Character.toString(inputWillCompressed.charAt(i));
            if (StringNotFound(charInputAsString,Dictionary)) {
                Dictionary.add(charInputAsString);
                positionTag.add(0);
                nextCharTag.add(charInputAsString);
            }
            else {
               while (!StringNotFound(charInputAsString,Dictionary)) {
                   i++;
                   if(i==inputWillCompressed.length()){
                       charInputAsString = charInputAsString;
                       positionTag.add(indexOfStringInDic(charInputAsString, Dictionary));
                       nextCharTag.add("null");
                       break;
                   }
                   charInputAsString = charInputAsString + Character.toString(inputWillCompressed.charAt(i));
                }
                if(i!=inputWillCompressed.length()) {
                    Dictionary.add(charInputAsString);
                    positionTag.add(indexOfStringInDic(charInputAsString.substring(0, charInputAsString.length() - 1), Dictionary));
                    nextCharTag.add(Character.toString(inputWillCompressed.charAt(i)));
                }
            }
        }
        for(int i=0;i<positionTag.size();i++){
            System.out.println("< "+positionTag.get(i)+" ,"+nextCharTag.get(i)+" >");
        }
        Decompress(Dictionary, positionTag, nextCharTag);
        for(int i=0;i<Dictionary.size();i++){
            System.out.println(Dictionary.get(i));
        }
    }
}
//abaababaababbbbbbbbbb