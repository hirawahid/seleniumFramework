package selenium.utils.listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

public class ConditionalMethodInterceptor implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

        List<IMethodInstance> result = new ArrayList<>();

        for (IMethodInstance method : methods) {
            String methodName = method.getMethod().getMethodName();

            // Only run certain tests in prod
            if (methodName.contains("forgotLogin"))
                    result.add(method); // allow these methods to run
        }

        return result;
    }
}
