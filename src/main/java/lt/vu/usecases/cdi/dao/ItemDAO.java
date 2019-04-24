package lt.vu.usecases.cdi.dao;

import lt.vu.entities.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ItemDAO {
    @Inject
    private EntityManager em;

    public void create(Item item) {
        em.persist(item);
    }

    public List<Item> getAllItems() {
        return em.createNamedQuery("Item.findAll", Item.class).getResultList();
    }
}
