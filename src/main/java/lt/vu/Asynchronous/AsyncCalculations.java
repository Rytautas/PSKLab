package lt.vu.Asynchronous;

import lt.vu.usecases.cdi.RescueOrAsync;
import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.concurrent.Future;

@ApplicationScoped
@Named
public class AsyncCalculations implements Serializable {

    @Inject
    @RescueOrAsync
    private EntityManager em;

    @Inject
    AlternativeAsyncInterface asyncSleep;

    @Futureable
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Future<String> bigCalculationsOfHell() {
        System.out.println("transaction " + em.getDelegate());
        System.out.println("Starting Big calculations of Hell");
        try {
            asyncSleep.sleepForAsync(); // Simulate intensive calculations of Hell
        } catch (InterruptedException e) {
            System.out.println("Exception raised while executing bigCalculationsOfHell: " + e.toString());
        }
        System.out.println("Finished the Big calculations of Hell");
        return new AsyncResult<>("Big result of Hell");
    }
}