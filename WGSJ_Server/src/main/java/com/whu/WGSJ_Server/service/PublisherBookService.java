package com.whu.WGSJ_Server.service;

import com.whu.WGSJ_Server.domain.Book;
import com.whu.WGSJ_Server.domain.Publisher;
import com.whu.WGSJ_Server.domain.PublisherBook;

import java.util.List;

public interface PublisherBookService {
    Integer addPublisherBook(PublisherBook publisherBook);

    Integer deleteByPublisherId(String publisherId);

    Integer deleteByBookId(String bookId);

    Publisher getPublisherByBookId(String bookId);

    List<Book> getBooksByPublisherId(String publisherId);
}
