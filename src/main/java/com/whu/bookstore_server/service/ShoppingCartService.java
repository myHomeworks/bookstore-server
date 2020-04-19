package com.whu.bookstore_server.service;

import com.whu.bookstore_server.domain.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getCartsByUserId(String userId);

    Integer getNumByBookId(String bookId);

    Integer addCart(ShoppingCart shoppingCart);

    Integer delCartById(String cartId);

    Integer updateCart(ShoppingCart shoppingCart);
}
