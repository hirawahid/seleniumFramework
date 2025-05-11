package selenium.utils.listeners;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class myHook implements IHookable {
    @Override
    public void run(IHookCallBack callBack, ITestResult result){
        if(result.getName().contains("invalid")){
            result.setStatus(ITestResult.SKIP);
        }else{
            callBack.runTestMethod(result);
        }
    }
}
