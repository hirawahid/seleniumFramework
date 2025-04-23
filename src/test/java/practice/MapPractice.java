package practice;

import org.checkerframework.checker.units.qual.K;

import java.util.*;

public class MapPractice {
    int roll;
    String name;

    MapPractice(int r, String n){}


    public static <V> void main(String args[]){
        String str="i love the way you lie it the way";
        Map<String,Integer> occurances=new HashMap<>();
        //count the occurances of words
        String[] list=str.split(" ");
        for(String s: list){
           occurances.compute(s,(k,v)->(v==null)?1:v+1);
        }
        System.out.println(occurances);
        ArrayList<Map.Entry<String, Integer>> arr=new ArrayList<>(occurances.entrySet());
        arr.sort(Map.Entry.comparingByValue());

        System.out.println(arr);
//LESSON: cannot remove in for each gives concurrent modification exception
//        for(Map.Entry<String,Integer> entry: occurances.entrySet()){
//            occurances.remove(entry.getKey());
//        }

        Set<Map.Entry<String,Integer>> entries= occurances.entrySet();

        Iterator<Map.Entry<String,Integer>> iterator=entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Integer> temp=iterator.next();
            if(temp.getKey().equalsIgnoreCase("love")){
                iterator.remove();
            }

        }

        //try removing through keys and values as set view is returned so remove will have impact on occurances

        Set<String> keys=occurances.keySet();
        Iterator<String> iterator1= keys.iterator();
        while(iterator1.hasNext()){
           String temp=iterator1.next();
            if(temp.equalsIgnoreCase("the")){
                iterator1.remove();
            }

        }


        Collection<Integer> values=occurances.values();
        Iterator<Integer> iterator2= values.iterator();
        while(iterator2.hasNext()){
            Integer temp=iterator2.next();
            if(temp.equals(1)){
                iterator2.remove();
            }

        }

        System.out.println(occurances);


    }

}
