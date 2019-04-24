package lt.vu.usecases.mybatis.dao;

import java.util.List;
import lt.vu.usecases.mybatis.model.Shop;
import org.mybatis.cdi.Mapper;

@Mapper
public interface ShopMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SHOP
     *
     * @mbg.generated Mon Apr 22 19:48:17 EEST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SHOP
     *
     * @mbg.generated Mon Apr 22 19:48:17 EEST 2019
     */
    int insert(Shop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SHOP
     *
     * @mbg.generated Mon Apr 22 19:48:17 EEST 2019
     */
    Shop selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SHOP
     *
     * @mbg.generated Mon Apr 22 19:48:17 EEST 2019
     */
    List<Shop> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SHOP
     *
     * @mbg.generated Mon Apr 22 19:48:17 EEST 2019
     */
    int updateByPrimaryKey(Shop record);
}