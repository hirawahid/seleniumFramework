package practice;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPractice {
    public static void main(String[] args){
        String emailValidator="[a-z]{1}[a-z0-9._].*@[a-z].*(\\.[a-z].*)+";
        Pattern emailPattern=Pattern.compile(emailValidator);
        String testEmail="qabackend.almosafer@gmail.com";
        List<String> emails= List.of("qabackend.almosafer@gmail.com","user123@domain.co.in","@nope.com","missing@.com");

        emails.stream().map(n->n.matches(emailValidator)).forEach(System.out::println);
//
//
//        //extract all numbers
//        String numbers="I have 3 apples and 12 bananas. Also 404 errors.";
//        String numberRegex="(\\d+)";
//
//        Pattern number=Pattern.compile(numberRegex);
//        Matcher matcher=number.matcher(numbers);
//        while(matcher.find()){
//        System.out.println(matcher.group());
//        }
//
//        //split on words
//        String str="Hello, world! Let's code Java.";
//        String wordsRegex="\\W+";
//        Arrays.stream(str.split(wordsRegex)).forEach(System.out::println);
//
//
//        //string Password
//        String passwordRegex="(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*\\d).{8,}";
//        String pwdToMatch="Hira@gwat1";
//        System.out.println(pwdToMatch.matches(passwordRegex));
//
//        //pakistani phone number
//        String ph="+91334511094";
//
//        String phRegex="^\\+(92)\\d{10}";
//        System.out.println(ph.matches(phRegex));
//
//        //convert string to camel case
//
//        String str1="convert_to-camel case1true";
//        String reg="[^a-z]";
//        System.out.println(Arrays.toString(str1.split(reg)));
//        String arr[]=str1.split(reg);
//
//        StringBuilder stringBuilder=new StringBuilder();
//        stringBuilder.append(arr[0].toLowerCase());
//        for(int a=1;a<arr.length;a++)
//        {
//            StringBuilder temp=new StringBuilder(arr[a]);
//            System.out.println(arr[a].substring(0,1).toUpperCase());
//            temp.replace(0,1,arr[a].substring(0,1).toUpperCase());
//            stringBuilder.append(temp);
//        }
//System.out.println(stringBuilder);

    }
}
