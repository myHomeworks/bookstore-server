package com.whu.bookstore_server.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.whu.bookstore_server.domain.Book;
import com.whu.bookstore_server.domain.BookDetail;
import com.whu.bookstore_server.domain.BookIntro;

import java.util.List;

public interface BookService {
    // 普通用户的
    List<Book> getRecommend(Page<Book> page, String userId);

    // 管理员的
    Integer updateBook(Book book);

    Integer addBook(Book book);

    Integer deleteBookById(String bookId); // 假删除（订单外键）

    Integer addBookIntro(BookIntro bookIntro);

    Integer addBookDetail(BookDetail bookDetail);

    Integer delBookIntroById(String bookI);

    Integer delBookDetailById(String bookD);

    // 公共的
    Book getBookById(String bookId);

    // 调用规则 bookName & authorName & publisherName实参null表不使用该条件;
    List<Book> getBookByComplexConditions(Page<Book> page, String bookName, String authorName, String publisherName,
                                          String startTime, String endTime, Double startPrice, Double endPrice);

}
