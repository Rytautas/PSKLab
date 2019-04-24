package lt.vu.usecases.cdi.simple;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.entities.*;
import lt.vu.usecases.cdi.dao.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model // tas pats kaip: @Named ir @RequestScoped
@Slf4j
public class RequestUseCaseControllerJPA {

    @Getter
    private Shop shop = new Shop();
    @Getter
    private Item item = new Item();
    @Getter
    private List<Shop> allShops;

    @PostConstruct
    public void init() {
        loadAllShops();
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
    }

    @Transactional
    public void createShop() {
        shopDAO.create(shop);
    }

    @Transactional
    public void createItem() {
        itemDAO.create(item);
    }

    private void loadAllShops() {
        allShops = shopDAO.getAllShops();
    }
}
