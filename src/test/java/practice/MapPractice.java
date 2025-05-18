package practice;

import org.checkerframework.checker.units.qual.A;
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

        anagrams();

    }

    public static void firstnonRepeatingcharacter(){
        String s="automation";

        char list[]=s.toCharArray();

        LinkedHashMap<Character, Integer> map=new LinkedHashMap<>();
        for(char b:list){
            if(map.containsKey(b)){
                int temp=map.get(b);
                temp=temp+1;
                map.put(b,temp);
            }
            else {
                map.put(b,1);
            }
        }

        for(Character r: map.keySet()){
            if(map.get(r)==1)
            {
                System.out.println("first non repeating character is: "+ r);
                break;
            }        }

    }

    public static void anagrams(){
        String a="sillent";

        char alist[]=a.toCharArray();

        String b="listen";

        char blist[]=b.toCharArray();

        HashMap<Character,Integer> amap=new HashMap<>();
        for(char c:alist){
            if(amap.containsKey(c)){
                int temp=amap.get(c);
                temp=temp+1;
                amap.put(c,temp);
            }
            else {
                amap.put(c,1);
            }
        }
        HashMap<Character,Integer> bmap=new HashMap<>();
        for(char d:blist){
            if(bmap.containsKey(d)){
                int temp=bmap.get(d);
                temp=temp+1;
                bmap.put(d,temp);
            }
            else {
                bmap.put(d,1);
            }
        }

        if(amap.equals(bmap)){
            System.out.println("anagrams");
        }else{
            System.out.println("not anagrams");
        }

        ArrayList<Map.Entry<Character,Integer>> list=new ArrayList<>();
        for(Map.Entry<Character,Integer> entry: bmap.entrySet()){
            list.add(entry);
        }
        list.sort(Map.Entry.comparingByValue());
    }



}
