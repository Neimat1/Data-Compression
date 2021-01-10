import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArithmeticCodingFloatCompress {
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
    public static HashMap<Character, Float> ProbablityForCharInString(String string){
        HashMap<Character, Integer> map = new HashMap<>();
        map=FrequencyForCharInString(string);
        HashMap<Character, Float> ProbMap = new HashMap<>();
        for(Map.Entry<Character, Integer> counter: map.entrySet()){
            ProbMap.put( counter.getKey() ,  ((float)counter.getValue()/(float)string.length()));
        }
        return ProbMap;
    }
    public static ArrayList<Float> prob(HashMap<Character, Float> map){
        ArrayList<Float> Low=new ArrayList<>();
        int i=0;
        for(Map.Entry<Character, Float> counter: map.entrySet()){
            Low.add(counter.getValue());
        }
        return Low;
    }
    public static HashMap<Character, Float> LoweMap( HashMap<Character, Float> map){
        HashMap<Character, Float> mapLow = new HashMap<>();
        ArrayList<Float> basicBrobablity=prob( map);
        ArrayList<Float> lowRange =new ArrayList<>();
        ArrayList<Float> highRange=new ArrayList<>();
        int i=0;
        for(Map.Entry<Character, Float> counter: map.entrySet()){
            if(i==0) {
                lowRange.add((float) 0);
                highRange.add(counter.getValue());
                mapLow.put( counter.getKey() , (float) i);
            }
            else{
                lowRange.add(highRange.get(i-1));
                highRange.add(counter.getValue()+lowRange.get(i));
                mapLow.put( counter.getKey() , lowRange.get(i));

            }
            i++;
        }
        return mapLow;
    }
    public static HashMap<Character, Float> HigheMap(HashMap<Character, Float> map){
        HashMap<Character, Float> mapHigh = new HashMap<>();
        ArrayList<Float> highRange=new ArrayList<>();
        int i=0;
        for(Map.Entry<Character, Float> counter: map.entrySet()){
            if(i==0) {
                highRange.add(counter.getValue());
                mapHigh.put( counter.getKey() , highRange.get(i));
            }
            else{
                highRange.add(counter.getValue()+highRange.get(i-1));
            }
            i++;
        }
        int j=0;
        for(Map.Entry<Character, Float> counter: map.entrySet()){
            highRange.add(counter.getValue());
            mapHigh.put( counter.getKey() , highRange.get(j));
            j++;
        }
        return mapHigh;
    }
    public static HashMap<Character, Float> RangeMap( HashMap<Character, Float> map){
        HashMap<Character, Float> mapRangeLow       = new HashMap<>();
        HashMap<Character, Float> mapRangeUpper     = new HashMap<>();
        HashMap<Character, Float> mapRange          = new HashMap<>();

        mapRangeLow=LoweMap(map);
        mapRangeUpper=HigheMap(map);
        int j=0;
        for(Map.Entry<Character, Float> counter: map.entrySet()){
            mapRange.put( counter.getKey() ,counter.setValue(mapRangeUpper.get(counter.getKey())-mapRangeLow.get(counter.getKey())));
            j++;
        }
        return mapRange;
    }

    public static void main(String[] args)
    {
        Scanner input=new Scanner(System.in);
        String inputTextWillCompress=input.nextLine();

        HashMap<Character, Float> mapCharAndProbab  = new HashMap<>();
        HashMap<Character, Float> mapRangeLow       = new HashMap<>();
        HashMap<Character, Float> mapRangeUpper     = new HashMap<>();
        HashMap<Character, Float> mapRange          = new HashMap<>();

        mapCharAndProbab=ProbablityForCharInString(inputTextWillCompress);
        mapRangeLow=LoweMap(mapCharAndProbab);
        mapRangeUpper=HigheMap(mapCharAndProbab);
        mapRange=RangeMap(mapCharAndProbab);

        System.out.println("High Range : "+mapRangeUpper);
        System.out.println("Low Range : "+ mapRangeLow);
        System.out.println("Range : "+ mapRange);

        Character  tempraryChar=inputTextWillCompress.charAt(0);
        for(int i=1;i<inputTextWillCompress.length();i++){
            /*
             * Lower(symbol)=lower(الحرف اللي قبلي)+Range(الحرف اللي قبلي )*low(symbol)
             * Upper(symbol)=upper(الحرف اللي قبلي)+Range(الحرف اللي قبلي )*upper(symbol)
             * */
            mapRangeLow.put(inputTextWillCompress.charAt(i),
                    (mapRangeLow.get(tempraryChar)+(mapRangeUpper.get(tempraryChar)-mapRangeLow.get(tempraryChar))*mapRangeLow.get(inputTextWillCompress.charAt(i))));

            mapRangeUpper.put(inputTextWillCompress.charAt(i),
                    (mapRangeLow.get(tempraryChar)+(mapRangeUpper.get(tempraryChar)-mapRangeLow.get(tempraryChar))*mapRangeUpper.get(inputTextWillCompress.charAt(i))));

            tempraryChar=inputTextWillCompress.charAt(i);
            /*
            System.out.println(" Character is : "+tempraryChar);
            System.out.println("the lower range of  Charcter is : "+mapRangeLow.get(tempraryChar));
            System.out.println("the Upper range of  Charcter is : "+mapRangeUpper.get(tempraryChar));
            System.out.println("------------------------------------------------------------------------ ");
            System.out.println("------------------------------------------------------------------------ ");
            System.out.println("------------------------------------------------------------------------ ");


             */
        }

        System.out.println("Float for compress data is  : "+((mapRangeLow.get(tempraryChar)+mapRangeUpper.get(tempraryChar))/2));

    }
}
