package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {


    List<ShoppingCart> list(ShoppingCart shoppingCart);


    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart cart);

    @Insert("insert into shopping_cart set name = #{name}, image = #{image}, user_id = #{userId}, dish_id = #{dishId}, setmeal_id = #{setmealId}, dish_flavor = #{dishFlavor}, number = #{number}, amount = #{amount}, create_time = #{createTime}")
    void insert(ShoppingCart shoppingCart);

    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long userId);
}
