package practice;

import java.util.Comparator;

public class compareTask implements Comparator<task> {
    @Override
    public int compare(task o1, task o2){
        //Sort a List<Employee> by salary descending, then name ascending.
        int compareSalary=0;
        if(o1.salary<o2.salary)
            compareSalary=1;
        if(compareSalary==0)
            return o1.name.compareTo(o2.name);
        else 
            return compareSalary;
    }
}
