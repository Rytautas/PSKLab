package lt.vu.Asynchronous;
                                                                                                                                                                                                                                                                                                                                                                                                                         import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;

@Specializes                                                                                                                                                                                                                                                                                                                                                                                                         @Alternative
public class AsyncSpecializes extends AlternativeAsync {
    @Override
    public void sleepForAsync() throws InterruptedException {
        System.out.println("Specialization of Hell");
        super.sleepForAsync();
    }
}