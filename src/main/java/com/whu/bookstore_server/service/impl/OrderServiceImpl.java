package com.whu.bookstore_server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.whu.bookstore_server.domain.*;
import com.whu.bookstore_server.mapper.*;
import com.whu.bookstore_server.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private MyOrderMapper myOrderMapper;

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

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public List<MyOrder> getOrdersByUserId(String userId) {
        return myOrderMapper.selectList(new EntityWrapper<MyOrder>()
                .eq("user_id", userId)
                .eq("if_delete", 0));
    }

    @Override
    public Integer updateOrder(MyOrder myOrder) {
        return myOrderMapper.updateById(myOrder);
    }

    @Override
    public Boolean getOrderMoneyGraph(HashMap<String, Object> ret, Integer year) {
        List<MyOrder> myOrders = myOrderMapper.selectList(new EntityWrapper<MyOrder>()
                .ge("time", year + "-01-01")
                .le("time", year + "-12-31"));
        List<Double> totalIn = new ArrayList<>();
        List<Double> totalEarning = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            totalIn.add(0.0);
            totalEarning.add(0.0);
        }
        for (MyOrder myOrder: myOrders) {
            try {
                int month = Integer.parseInt(myOrder.getTime().substring(5, 7)) - 1;
                totalIn.set(month, totalIn.get(month) + myOrder.getPayedMoney());
                totalEarning.set(month, totalEarning.get(month) + myOrder.getPayedMoney() * 0.2);
            } catch (Exception e) {
                log.error("Fail when calculate order money\n"+e.getMessage());
                return false;
            }
        }
        ret.put("totalIn", totalIn);
        ret.put("totalEarning", totalEarning);
        return true;
    }

    @Override
    public Integer addOrder(MyOrder myOrder) {
        return myOrderMapper.insert(myOrder);
    }

    @Override
    public MyOrder getOrderById(String orderId) {
        return myOrderMapper.selectById(orderId);
    }

    @Override
    public Integer deleteOrderById(String orderId) {
        MyOrder myOrder = myOrderMapper.selectById(orderId);
        myOrder.setIsDeleted(1);
        return myOrderMapper.updateById(myOrder);
    }

    @Override
    public List<MyOrder> getOrdersByComplexConditions(Page<MyOrder> page, String bookName, String authorName,
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
        for (Address address : addresses) {
            addressIds.add(address.getAddressId());
        }

        // return
        return myOrderMapper.selectPage(page, new EntityWrapper<MyOrder>()
                .in("book_id", bookIds)
                .in("address_id", addressIds)
                .ge("time", startTime)
                .le("time", endTime)
                .ge("payed_money", startPrice)
                .le("payed_money", endPrice)
                .eq("if_delete", 0));
    }
}
