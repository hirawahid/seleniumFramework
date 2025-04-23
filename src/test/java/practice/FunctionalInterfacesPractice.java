package practice;
@FunctionalInterface
public interface FunctionalInterfacesPractice {
    public void testCall(int a);

    public static void main(String[] args){
    FunctionalInterfacesPractice functionalInterfacesPractice=(n)->{System.out.println(n);
    System.out
            .println(n+1);
    };
    functionalInterfacesPractice.testCall(3);
    }
}
