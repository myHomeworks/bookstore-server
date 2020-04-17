package com.whu.bookstore_server.service;

import com.whu.bookstore_server.domain.Book;
import com.whu.bookstore_server.domain.Publisher;
import com.whu.bookstore_server.domain.PublisherBook;

import java.util.List;

public interface PublisherBookService {
    Integer addPublisherBook(PublisherBook publisherBook);

    Integer deleteByPublisherId(String publisherId);

    Integer deleteByBookId(String bookId);

    Publisher getPublisherByBookId(String bookId);

    List<Book> getBooksByPublisherId(String publisherId);
}
