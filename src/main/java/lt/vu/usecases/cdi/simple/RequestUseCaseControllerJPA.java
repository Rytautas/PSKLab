package lt.vu.usecases.cdi.simple;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.entities.*;
import lt.vu.usecases.cdi.dao.*;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import javax.persistence.OptimisticLockException;

@Model
@Slf4j
public class RequestUseCaseControllerJPA {

    @Getter
    private Shop shop = new Shop();
    @Getter
    private Item item = new Item();
    @Getter
    private List<Shop> allShops;
    @Getter
    private Shop conflictingShop;
    @Getter
    private Shop selectedShop;
    @Getter
    private List<Item> allItems;

    @PostConstruct
    public void init() {
        loadAllShops();
        loadAllItems();
    }

    @Inject
    private ShopDAO shopDAO;
    @Inject
    private ItemDAO itemDAO;

    @Transactional
    public void createShopItem() {
        shop.getItemList().add(item);
        item.getShopList().add(shop);
        itemDAO.create(item);
        shopDAO.create(shop);
        log.info("Maybe OK...");
        refresh();
    }

    @Transactional
    public void createShop() {
        shopDAO.create(shop);
    }

    @Transactional
    public void createItem() {
        itemDAO.create(item);
    }

    public void prepEdit(Shop shop) {
        conflictingShop = null;
        selectedShop = shop;
        System.out.println(selectedShop.toString());
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Transactional
    public void updateShop() {
        System.out.println("k");
        System.out.println(selectedShop.toString());
        System.out.println("k");
        try {
            System.out.println("asdfsdgasrfghjnrfsdgh");
            System.out.println(selectedShop.toString());
            shopDAO.update(selectedShop);
        }
        catch (OptimisticLockException ole) {
            conflictingShop = shopDAO.getShopByID(selectedShop.getId());
            RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
        }

    }

    public void refresh() {
        loadAllShops();
    }

    @Transactional
    public void overwriteShop() {
        selectedShop.setOptLockVersion(conflictingShop.getOptLockVersion());
        updateShop();
    }

    private void loadAllShops() {
        allShops = shopDAO.getAllShops();
    }

    private void loadAllItems() {
        allItems = itemDAO.getAllItems();
    }
}
