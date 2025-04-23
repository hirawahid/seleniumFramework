package selenium.utils.listeners;

import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import selenium.base.DriverManager;
import selenium.utils.testNgUtils.retryAnalyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        System.out.println("Retry listener invoked");
        System.out.println("annotation.getRetryAnalyzerClass is null hence setting up");
        annotation.setRetryAnalyzer(retryAnalyzer.class); // Apply retry logic
    }
}
