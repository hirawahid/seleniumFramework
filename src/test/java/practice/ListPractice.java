package practice;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListPractice {
    int statusCode;
    String name;

    ListPractice(int s, String n){
        this.statusCode=s;
        this.name=n;
    }

    @Override
    public String toString(){
        return this.name + this.statusCode;
    }

    public int getCode(){
        return this.statusCode;
    }

    public String getName(){
        return this.name;
    }


    public static void main(String args[]) {
        List<ListPractice> list = new ArrayList<>();
        ListPractice lp1 = new ListPractice(200, "search");
        ListPractice lp2 = new ListPractice(100, "price");
        ListPractice lp3 = new ListPractice(500, "brice");
        list.add(lp1);
        list.add(lp2);
        list.add(lp3);
        list.add(lp3);
        //list.remove(lp3);
        //list.removeIf(n->n.statusCode!=200);
        //list.set(0,lp3);
        //List<ListPractice> filtered=list.stream().filter(n->!n.name.equalsIgnoreCase("search")).collect(Collectors.toList());

       // list.sort(Comparator.comparing(ListPractice::getCode).thenComparing(ListPractice::getName));

        System.out.println(list);


//        List<Integer> a=List.of(5,2,3,4);
//        list.sort(new sortPractice());
 //       Collections.sort(list, new sortPractice());
     //   Collections.sort(list,(o1, o2) -> o1.statusCode-o2.statusCode);
      //  Collections.sort(list,Comparator.comparingInt(o->o.statusCode));

        System.out.println(list.stream().map((n)->n.statusCode+1).collect(Collectors.toList()));

        System.out.println(list.stream().sorted(Comparator.comparingInt(o->o.statusCode)).collect(Collectors.toList()));
        list.stream().sorted(Comparator.comparingInt(o->o.statusCode)).forEach(System.out::println);

        System.out.println(list.stream().map(n->n.name.length()).reduce(0,Integer::sum));

       // System.out.println(list.stream().count());


        //Collections.reverse(list);
       // System.out.println(list);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> num = numbers.stream().filter(n -> !(n % 2 == 0)).collect(Collectors.toList());

        // numbers.removeIf(n->(n%2==0)); not work as numbers is created from Arrays so add remove operations are not supported change to arraylIST
        Collections.reverse(list);

        // System.out.println(list);

        List<String> list1 = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        List<String> list2 = new ArrayList<>(Arrays.asList("C", "D", "E", "F"));

        list1.addAll(list2);
        Set<String> noDups = new HashSet<>(list1);
        //    System.out.println(noDups);
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 3, 2, 6, 7, 8, 1));

        Set<Integer> uniqueElements = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        numbers1.forEach(integer -> {
            if (uniqueElements.contains(integer)) {
                dups.add(integer);
            } else {
                uniqueElements.add(integer);
            }
        });

        //System.out.println(dups);
        List<String> words = new ArrayList<>(List.of("apple", "banana", "cherry", "apricot", "blueberry", "avocado"));
        Map<Character, List<String>> groups = new HashMap<>();
        for (String word : words) {
            Character firstLetter = word.charAt(0);
            groups.computeIfAbsent(firstLetter,k->new ArrayList<>()).add(word);

//            if (groups.containsKey(firstLetter)) {
//                groups.get(firstLetter).add(word);
//            } else {
//                List<String> toAdd = new ArrayList<>();
//                toAdd.add(word);
//                groups.put(firstLetter, toAdd);
//
//            }
        }

        List<String> words1 = new ArrayList<>(Arrays.asList("java", "python", "c++", "javascript"));

      //  System.out.println(words1.stream().map(String::toUpperCase).collect(Collectors.toList()));
        //sort and ignore the case
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "bob", "Charlie", "david", "Eve"));
        //ignored the case
        //LESSON: ARRAYS.asList and  List.of resutn immutable list where sorting is also not allowed
        List<String> lowered= names.stream().map(String::toLowerCase).collect(Collectors.toList());
      //  lowered.sort(new sortByStringLength());

    //    lowered.sort(Comparator.comparing(String::length).reversed()); //compare by length

        lowered.sort(Comparator.comparing(String::toString));
       // System.out.println(lowered);
        Integer[] a = {1, 2};
        Integer[] b = {3, 4};

        List<String> result = Arrays.stream(a)
                .flatMap(x -> Arrays.stream(b).map(y -> "[" + x + "," + y + "]")).toList();

        result.forEach(System.out::println);
        ArrayList<String> abd=new ArrayList<>();
        abd.add("1=\"1\"");
        abd.add("2=\"2\"");
        abd.add("3=\"3\"");
        HashMap<String, String> map=new HashMap<>();
        abd.forEach(n->map.put(n.split("=")[0],n.split("=")[1]));
        System.out.println(map);

        String str="geeksf5";
        System.out.println(str.chars().allMatch(Character::isLetter));
        char c='a';
        if(List.of('a', 'e', 'i', 'o', 'u').contains(c)){
            System.out.println("vowels");
        }



    }
}
class sortByStringLength implements Comparator<String>{
    @Override
    public int compare(String a, String b){
        return a.length()-b.length();
    }
}
class sortNames implements Comparator<String>{
    //LESSON: always pass element to comparator otherwise it will implement object compare function

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

class sortPractice implements Comparator<ListPractice> {
    @Override
    public int compare(ListPractice o1, ListPractice o2) {
        return o1.statusCode-o2.statusCode;
    }
}
