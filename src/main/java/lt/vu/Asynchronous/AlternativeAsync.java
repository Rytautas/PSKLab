package lt.vu.Asynchronous;

import lt.vu.interceptors.Log;

import javax.enterprise.inject.Alternative;

@Alternative
public class AlternativeAsync implements AlternativeAsyncInterface {
    @Override
    @Log
    public void sleepForAsync() throws InterruptedException {
            Thread.sleep(2000);
    }
}
