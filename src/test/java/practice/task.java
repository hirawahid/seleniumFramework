package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class task {
    int salary;
    String name;

    task(int s, String n) {
        this.salary = s;
        this.name = n;
    }

    @Override
    public String toString(){

        return this.name + this.salary;
    }

    public int getSalary(){
        return this.salary;
    }

    public String getName(){
        return this.name;
    }

    public static void main(String args[]){
        List<task> a=new ArrayList<>();
        task b=new task(1,"hira");
        task c=new task(3,"Ali");

        a.add(b);
        a.add(c);
        a.stream().forEach(System.out::println);
        //Collections.sort(a,new compareTask());
        a.sort(Comparator.comparingInt(task::getSalary).reversed().thenComparing(task::getName));
        a.stream().forEach(System.out::println);
    }
}

