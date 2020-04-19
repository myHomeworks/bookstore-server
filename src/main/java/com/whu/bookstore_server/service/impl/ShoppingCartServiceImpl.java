package com.whu.bookstore_server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whu.bookstore_server.domain.Book;
import com.whu.bookstore_server.domain.ShoppingCart;
import com.whu.bookstore_server.domain.User;
import com.whu.bookstore_server.mapper.BookMapper;
import com.whu.bookstore_server.mapper.ShoppingCartMapper;
import com.whu.bookstore_server.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ShoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<ShoppingCart> getCartsByUserId(String userId) {
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectList(new EntityWrapper<ShoppingCart>()
                .eq("user_id", userId));
        for (ShoppingCart shoppingCart : shoppingCarts) {
            Book book = bookMapper.selectById(shoppingCart.getBookId());
            shoppingCart.setName(book.getName());
            shoppingCart.setPreviewUrl(book.getPreviewUrl());
            shoppingCart.setPrice(book.getPriceN());
        }
        return shoppingCarts;
    }

    @Override
    public Integer getNumByBookId(String bookId) {
        return shoppingCartMapper.selectCount(new EntityWrapper<ShoppingCart>()
                .eq("book_id", bookId));
    }

    @Override
    public Integer addCart(ShoppingCart shoppingCart) {
        if (shoppingCartMapper.selectCount(new EntityWrapper<ShoppingCart>()
                .eq("user_id", shoppingCart.getUserId())
                .eq("book_id", shoppingCart.getBookId())) == 0)
            return shoppingCartMapper.insert(shoppingCart);
        else {
            return shoppingCartMapper.update(shoppingCart, new EntityWrapper<ShoppingCart>()
                    .eq("user_id", shoppingCart.getUserId())
                    .eq("book_id", shoppingCart.getBookId()));
        }
    }

    @Override
    public Integer delCartById(String cartId) {
        return shoppingCartMapper.deleteById(cartId);
    }

    @Override
    public Integer updateCart(ShoppingCart shoppingCart) {
        return shoppingCartMapper.updateById(shoppingCart);
    }
}
