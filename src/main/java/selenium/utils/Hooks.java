package selenium.utils;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import org.testng.annotations.BeforeMethod;

import static org.testng.TestRunner.PriorityWeight.priority;

public class Hooks {

    @Before("not @chrome")
    public void setUp() {
        System.out.println("✅ Setup2 before scenario");
        // Initialize WebDriver, DB, etc.
    }
    @Before(order=1)
    public void setUp1() {
        System.out.println("✅ Setup1 before scenario");
        // Initialize WebDriver, DB, etc.
    }
    @BeforeStep()
    public void epsetUp1() {
        System.out.println("✅ before step");
        // Initialize WebDriver, DB, etc.
    }
    @BeforeMethod()
    public void beforemethod() {
        System.out.println("✅ before method");
        // Initialize WebDriver, DB, etc.
    }
    @BeforeAll
    public static void before_all() {
        System.out.println("✅ before All");
        // Initialize WebDriver, DB, etc.
    }

    @After
    public void tearDown() {
        System.out.println("🔻 Cleanup after scenario");
        // Quit driver, clear data, etc.
    }
}

