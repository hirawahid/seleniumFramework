package selenium.utils.listeners;

import org.testng.IExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutionListener implements IExecutionListener {

    @Override
    public void onExecutionStart() {
        System.setProperty("log.name", "application.testng");
        Logger logger = LoggerFactory.getLogger("ExecutionLogger");
        logger.info("=== TestNG Execution Started ===");
    }

    @Override
    public void onExecutionFinish() {
        Logger logger = LoggerFactory.getLogger("ExecutionLogger");
        logger.info("=== TestNG Execution Finished ===");
    }
}
