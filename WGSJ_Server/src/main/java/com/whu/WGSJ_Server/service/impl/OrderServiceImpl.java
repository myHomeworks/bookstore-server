package com.whu.WGSJ_Server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.whu.WGSJ_Server.domain.*;
import com.whu.WGSJ_Server.mapper.*;
import com.whu.WGSJ_Server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private PublisherMapper publisherMapper;

    @Autowired
    private AuthorBookMapper authorBookMapper;

    @Autowired
    private PublisherBookMapper publisherBookMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Integer updateOrder(Order order) {
        return orderMapper.updateById(order);
    }

    @Override
    public Integer addOrder(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderMapper.selectById(orderId);
    }

    @Override
    public Integer deleteOrderById(String orderId) {
        return orderMapper.deleteById(orderId);
    }

    @Override
    public List<Order> getOrdersByComplexConditions(Page<Order> page, String bookName, String authorName,
                                                    String publisherName, String startTime, String endTime,
                                                    Double startPrice, Double endPrice, String addressName,
                                                    String consignee, String mobile) {
        // pre-handle the date
        addressName = addressName == null ? "" : addressName;
        consignee = consignee == null ? "" : consignee;
        mobile = mobile == null ? "" : mobile;

        // search author & publisher to get authorId & publisherId
        List<Author> authors = authorName != null ? authorMapper.selectList(new EntityWrapper<Author>()
                .like("name", authorName)) : new ArrayList<Author>();
        List<Publisher> publishers = publisherName == null ? publisherMapper.selectList(new EntityWrapper<Publisher>()
                .like("name", publisherName)) : new ArrayList<Publisher>();
        Set<String> bookIds = new HashSet<String>();
        Set<String> addressIds = new HashSet<String>();
        List<String> authorIds = new ArrayList<String>();
        List<String> publisherIds = new ArrayList<String>();

        // search bookName to get bookId
        List<Book> books = bookMapper.selectPage(page, new EntityWrapper<Book>()
                .like("name", bookName == null ? "" : bookName));
        for (Book book : books) {
            bookIds.add(book.getBookId());
        }

        for (Author author : authors) {
            authorIds.add(author.getAuthorId());
        }
        authors.clear();
        for (Publisher publisher : publishers) {
            publisherIds.add(publisher.getPublisherId());
        }
        publishers.clear();

        // search authorId & publisherId to get bookId
        if (authorIds.size() > 0) {
            List<AuthorBook> authorBooks = authorBookMapper.selectList(new EntityWrapper<AuthorBook>()
                    .in("author_id", authorIds));
            for (AuthorBook authorBook : authorBooks) {
                bookIds.add(authorBook.getBookId());
            }
            authorBooks.clear();
        }
        if (publisherIds.size() > 0) {
            List<PublisherBook> publisherBooks = publisherBookMapper.selectList(new EntityWrapper<PublisherBook>()
                    .in("publish_id", publisherIds));
            for (PublisherBook publisherBook : publisherBooks) {
                bookIds.add(publisherBook.getBookId());
            }
            publisherBooks.clear();
        }

        // search consignee & address & mobile get addressId
        List<Address> addresses = addressMapper.selectList(new EntityWrapper<Address>()
                .like("consignee", consignee)
                .or()
                .like("address", addressName)
                .or()
                .like("mobile", mobile));
        for(Address address: addresses){
            addressIds.add(address.getAddressId());
        }

        // return
        return orderMapper.selectPage(page, new EntityWrapper<Order>()
                .in("book_id", bookIds)
                .in("address_id", addressIds)
                .ge("time", startTime)
                .le("time", endTime)
                .le("payed_money", startPrice)
                .ge("payed_money", endPrice));
    }
}
