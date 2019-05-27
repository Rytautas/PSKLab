package lt.vu.rest;

import lt.vu.entities.Shop;
import lt.vu.usecases.cdi.dao.ShopDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@ApplicationScoped
@Path("/shop")
@Produces(MediaType.APPLICATION_JSON)
public class ShopController {

    @Inject
    private ShopDAO shopDAO;

    @GET
    @Path("/{shopId}")
    public Shop find(@PathParam("shopId") Integer shopId){
        return shopDAO.getShopByID(shopId);
    }
    @GET
    public List<Shop> getAll(){
        return shopDAO.getAllShops();
    }

    @POST
    @Path("/create/{itemId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Shop create(Shop shop){
        return shopDAO.create(shop);
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Shop update(Shop shop, @PathParam("id") Integer id){
        Shop shopToUpdate = shopDAO.getShopByID(id);
        if (shopToUpdate == null) {
            return null;
        }
        shopToUpdate.setName(shop.getName());
        shopDAO.update(shopToUpdate);
        return shopToUpdate;
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        shopDAO.deleteShopById(id);
    }
}