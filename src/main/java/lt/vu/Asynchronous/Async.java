package lt.vu.Asynchronous;

import lombok.Getter;
import lombok.Setter;
import lt.vu.Asynchronous.AsyncCalculations;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class Async implements Serializable {

    @Inject @Getter
    AsyncCalculations asyncCalculations;

    @Getter
    private Future<String> result = null;

    public String isResultReady() throws ExecutionException, InterruptedException {
        if (result == null) {
            System.out.println("User hasn't pressed a button!");
            return "Press button Calculate, to start asynchronous process!";
        } else {
            if (result.isDone()) {
                String callResult = result.get();
                result = null;
                System.out.println("Calculations finished, result is ready! It's " + callResult);
                return "Result is ready: " + callResult;
            } else {
                System.out.println("Please wait, while calculations are being processed...");
                return "Please wait, calculations are being processed...";
            }
        }
    }

    public boolean areCalculationsDone() {
        return result != null && !result.isDone();
    }

    public String runCalculations()
    {
        if (result == null) {
            result = asyncCalculations.bigCalculationsOfHell();
            System.out.println("Just called to start calculations! Has it finished?: " + result.isDone());
        }
        else{
            System.out.println("Already initialized!");
        }
        return "index?faces-redirect=true";
    }
}