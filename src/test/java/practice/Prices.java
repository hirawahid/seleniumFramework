package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Prices implements Comparable<Prices>{

    double price;
    String code;

    public Prices(double p, String c )
    {
        this.price=p;
        this.code=c;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCode(){
        return this.code;
    }
    @Override
    public String toString(){

        return this.code+" "+this.price;
    }
    @Override
    public int compareTo(Prices p){

        return Double.compare(this.price,p.price);

    }

    public static void main(String args[]){
        List<Prices> prices=new ArrayList<>();
        Prices prices1=new Prices(10.12,"F3");
        Prices prices2=new Prices(5.12,"G3");

        prices.add(prices1);
        prices.add(prices2);

        prices.stream().forEach(System.out::println);

       // Collections.sort(prices,new sortPrices());

     //   prices.stream().forEach(System.out::println);

        //prices.sort(new sortPrices());

      //  prices.sort(Comparator.comparing(Prices::getPrice).thenComparing(Prices::getCode));


        Collections.sort(prices);

        prices.stream().forEach(System.out::println);



    }
}

