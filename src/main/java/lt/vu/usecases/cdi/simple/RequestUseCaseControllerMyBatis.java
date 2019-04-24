package lt.vu.usecases.cdi.simple;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.usecases.mybatis.dao.*;
import lt.vu.usecases.mybatis.model.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model // tas pats kaip: @Named ir @RequestScoped
@Slf4j
public class RequestUseCaseControllerMyBatis {

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
    private ShopMapper shopMapper;
    @Inject
    private ItemMapper itemMapper;
    @Inject
    private ShopItemMapper shopItemMapper;

    @Transactional
    public void createShopItem() {
        itemMapper.insert(item);
        shopMapper.insert(shop);
        ShopItem shopItem = new ShopItem();
        shopItem.setItemId(item.getId());
        shopItem.setShopId(shop.getId());
        shopItemMapper.insert(shopItem);
        log.info("Maybe OK...");
    }

    private void loadAllShops() {
        allShops = shopMapper.selectAll();
    }
}
