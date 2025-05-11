package selenium;

import org.testng.annotations.Test;

import java.util.ArrayList;

public class FactoryTest {
    private String browser;

    public FactoryTest(String browser) {
        this.browser = browser;
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testSomething() {
        ArrayList<Integer> a=new ArrayList<>();
        a.get(0);
        System.out.println("Running on browser: " + browser);
    }
}

