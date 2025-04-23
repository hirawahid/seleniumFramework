package practice;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class setPractice {
    int roll;
    String name;

    setPractice(int r, String n){
        roll=r;
        name=n;
    }

    @Override
    public String toString(){
        return name+" "+roll;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(name+" "+roll);
    }

    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(o==null || o.getClass()!=this.getClass())
            return false;
        setPractice obj= (setPractice) o;
        return roll==obj.roll && name.equals(obj.name);
    }

    public int getRoll(){
        return roll;
    }

    public String getName(){
        return name;
    }






    public static void main(String[] args){
        List<setPractice> p=new ArrayList<>();
        p.add(new setPractice(5,"Hira"));
        p.add(new setPractice(5,"Hira"));

        List<setPractice> q=new ArrayList<>();
        q.add(new setPractice(2,"Hira1"));
        q.add(new setPractice(2,"Hira1"));

        //find common
        Set<setPractice> common=new HashSet<>(p);
        common.addAll(q);
        System.out.println(common);

        //intersection
        p.add(new setPractice(2,"Hira1"));
        Set<setPractice> intersection=new HashSet<>(p);
        intersection.retainAll(q);
        System.out.println(intersection);

        //difference
        p.add(new setPractice(2,"Hira1"));
        Set<setPractice> difference=new HashSet<>(p);
        difference.removeAll(q);
        System.out.println(difference);

        //sort with comparator modern
        System.out.println(common.stream().filter(obj->obj.name.contains("1")).collect(Collectors.toSet()));

        //Lesson: set does not provide sort support, to sort convert to list

        List<setPractice> sortable=new ArrayList<>(common);
        Collections.sort(sortable, Comparator.comparing(setPractice::getName).thenComparing(setPractice::getRoll));


        System.out.println(sortable);

        //CHECK FOR DUPLICATES
        ArrayList<Integer> a= new ArrayList<>( Arrays.asList(1, 2, 3, 4, 5, 3));
        Set<Integer> dups=new HashSet<>(a);
        System.out.println(a.size());
        System.out.println(dups.size());

        //check if string has unique characters
        String str="hiraiii";
        Set<Character> characters=new HashSet<>();

        char[] chars=str.toCharArray();
        for(char c: chars) {
            characters.add(c);
        }
        if(characters.size()==str.length()){
            System.out.println("uniqie");
        }
        else {
            System.out.println("not uniqie");
        }
        //first non repeating
        ArrayList<Integer> ab= new ArrayList<>( Arrays.asList(10, 5, 3, 4, 3, 5, 6));
        List<Integer> nodups=new ArrayList<>();
        Set<Integer> dups1=new HashSet<>();
        Iterator<Integer> iterator=ab.iterator();
        while(iterator.hasNext())
        {
            int temp= iterator.next();
            int result=nodups.indexOf(temp);
            if(result==-1){
                nodups.add(temp);
            }
            else{
                dups.add(result);
            }
        }

        System.out.println(nodups.get(Collections.min(dups)));

        }


}
