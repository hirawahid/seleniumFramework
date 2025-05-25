package practice;

import java.util.Comparator;

public class sortPrices implements Comparator<Prices> {
    @Override
    public int compare(Prices p1, Prices p2){
        
        int comparecode=p1.code.compareTo(p2.code);
      if(comparecode!=0)
          return comparecode;

      return Double.compare(p1.price,p2.price);

    }
}
