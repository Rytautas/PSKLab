package lt.vu.decorators;

import lt.vu.Asynchronous.AlternativeAsyncInterface;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class AsyncDecorator implements AlternativeAsyncInterface {
    @Inject
    @Delegate
    @Any
    AlternativeAsyncInterface async;

    public void sleepForAsync() throws InterruptedException {
        async.sleepForAsync();
        System.out.println("This is the Decoration of Hell");
        async.sleepForAsync();
    }
}
