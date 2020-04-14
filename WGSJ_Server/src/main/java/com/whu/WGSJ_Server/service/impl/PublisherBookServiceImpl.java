package com.whu.WGSJ_Server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whu.WGSJ_Server.domain.Book;
import com.whu.WGSJ_Server.domain.Publisher;
import com.whu.WGSJ_Server.domain.PublisherBook;
import com.whu.WGSJ_Server.mapper.BookMapper;
import com.whu.WGSJ_Server.mapper.PublisherBookMapper;
import com.whu.WGSJ_Server.mapper.PublisherMapper;
import com.whu.WGSJ_Server.service.PublisherBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("PublisherBookService")
public class PublisherBookServiceImpl implements PublisherBookService {
    @Autowired
    private PublisherBookMapper publisherBookMapper;

    @Autowired
    private PublisherMapper publisherMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Integer addPublisherBook(PublisherBook publisherBook) {
        return publisherBookMapper.insert(publisherBook);
    }

    @Override
    public Integer deleteByPublisherId(String publisherId) {
        return publisherBookMapper.delete(new EntityWrapper<PublisherBook>()
                .eq("publisher_id", publisherId));
    }

    @Override
    public Integer deleteByBookId(String bookId) {
        return publisherBookMapper.delete(new EntityWrapper<PublisherBook>()
                .eq("book_id", bookId));
    }

    @Override
    public Publisher getPublisherByBookId(String bookId) {
        List<PublisherBook> publisherBooks = publisherBookMapper.selectList(new EntityWrapper<PublisherBook>()
                .eq("book_id", bookId));
        return publisherMapper.selectById(publisherBooks.get(0).getPublisherId());
    }

    @Override
    public List<Book> getBooksByPublisherId(String publisherId) {
        List<PublisherBook> publisherBooks = publisherBookMapper.selectList(new EntityWrapper<PublisherBook>()
                .eq("publisher_id", publisherId));
        List<Book> books = new ArrayList<>();
        for (PublisherBook publisherBook : publisherBooks)
            books.add(bookMapper.selectById(publisherBook.getBookId()));
        return books;
    }
}
