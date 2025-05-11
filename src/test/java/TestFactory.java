import org.testng.annotations.Factory;
import selenium.FactoryTest;

public class TestFactory {
    @Factory
    public Object[] createInstances() {
        return new Object[] {
                new FactoryTest("Chrome"),
                new FactoryTest("Firefox"),
                new FactoryTest("Edge")
        };
    }
}
