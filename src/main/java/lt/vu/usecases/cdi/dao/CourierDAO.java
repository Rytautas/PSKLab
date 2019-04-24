package lt.vu.usecases.cdi.dao;

import lt.vu.entities.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CourierDAO {
    @Inject
    private EntityManager em;

    public void create(Courier courier) {
        em.persist(courier);
    }
    public void updateAndFlush(Courier courier) {
        em.merge(courier);
        em.flush();
    }

    public List<Courier> getAllCouriers() {
        return em.createNamedQuery("Courier.findAll", Courier.class).getResultList();
    }
}
