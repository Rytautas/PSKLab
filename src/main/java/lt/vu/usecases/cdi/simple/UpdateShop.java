        package lt.vu.usecases.cdi.simple;

        import lombok.Getter;
        import lt.vu.entities.Item;
        import lt.vu.entities.Shop;
        import lt.vu.interceptors.Log;
        import lt.vu.usecases.cdi.dao.ItemDAO;
        import lt.vu.usecases.cdi.dao.ShopDAO;
        import org.primefaces.context.RequestContext;
        import org.omnifaces.cdi.ViewScoped;

        import javax.annotation.PostConstruct;
        import javax.inject.Inject;
        import javax.inject.Named;
        import javax.persistence.OptimisticLockException;
        import javax.transaction.Transactional;
        import java.io.Serializable;
        import java.util.List;

@Named
@ViewScoped
public class UpdateShop implements Serializable {

    @Inject
    ShopDAO shopDAO;

    @Getter
    private Shop selectedShop;

    @Getter
    private Shop conflictingShop;

    @Getter
    List<Shop> allShops;

    @Getter
    List<Item> allItems;

    @Inject
    private ItemDAO itemDAO;

    @PostConstruct
    public void init() {
        loadAllShops();
        loadAllItems();
    }

    private void loadAllShops(){
        allShops = shopDAO.getAllShops();
    }

    public void reloadShops(){
        loadAllShops();
    }

    public void prepEdit(Shop shop) {
        selectedShop = shop;
        conflictingShop = null;
    }
    @Transactional
    public void overwriteShop() {
        selectedShop.setOptLockVersion(conflictingShop.getOptLockVersion());
        updateSelectedShop();
    }

    @Transactional
    public void updateSelectedShop() {
        try {
            shopDAO.update(selectedShop);
            reloadShops();
        } catch (OptimisticLockException ole) {
            conflictingShop = shopDAO.getShopByID(selectedShop.getId());
            RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
        }
    }

    private void loadAllItems() {
        allItems = itemDAO.getAllItems();
    }
}