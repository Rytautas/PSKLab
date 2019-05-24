package lt.vu.usecases.cdi.dao;

import lt.vu.entities.Shop;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ShopDAO {
    @Inject
    private EntityManager em;

    public void create(Shop shop) {
        em.persist(shop);
    }

    public List<Shop> getAllShops() {
        return em.createNamedQuery("Shop.findAll", Shop.class).getResultList();
    }
    public Shop getShopByID(int ID) {
        return em.find(Shop.class, ID);
    }

    public void update(Shop shop) {
        em.merge(shop);
        em.flush();
    }
}