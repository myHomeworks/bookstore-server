package com.whu.WGSJ_Server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.whu.WGSJ_Server.domain.*;
import com.whu.WGSJ_Server.mapper.*;
import com.whu.WGSJ_Server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("BookService")
public class BookServiceImpl implements BookService {
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

    @Override
    public Integer updateBook(Book book) {
        return bookMapper.updateById(book);
    }

    @Override
    public Integer addBook(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public Integer deleteBookById(String bookId) {
        return bookMapper.deleteById(bookId);
    }

    @Override
    public Book getBookById(String bookId) {

        return bookMapper.selectById(bookId);
    }

    @Override
    public List<Book> getBookByComplexConditions(Page<Book> page, String bookName, String authorName,
                                                 String publisherName, String startTime, String endTime,
                                                 Double startPrice, Double endPrice) {
        // pre-handle the date
//        bookName = bookName == null ? "" : bookName;
//        authorName = authorName == null ? "" : authorName;
//        publisherName = publisherName == null ? "" : publisherName;

        // search author & publisher to get authorId & publisherId
        List<Author> authors = authorName != null ? authorMapper.selectList(new EntityWrapper<Author>()
                .like("name", authorName)) : new ArrayList<Author>();
        List<Publisher> publishers = publisherName == null ? publisherMapper.selectList(new EntityWrapper<Publisher>()
                .like("name", publisherName)) : new ArrayList<Publisher>();
        Set<String> bookIds = new HashSet<String>();
        List<String> authorIds = new ArrayList<String>();
        List<String> publisherIds = new ArrayList<String>();

        for (Author author : authors) {
            authorIds.add(author.getAuthorId());
        }
        authors.clear();
        for (Publisher publisher : publishers) {
            publisherIds.add(publisher.getPublisherId());
        }
        publishers.clear();

        // search authorId & publisherId to get bookId
        // here is something to improve
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

        // return
        return bookMapper.selectPage(page, new EntityWrapper<Book>()
                .in("book_id", bookIds)
                .like("name", bookName == null ? "" : bookName)
                .ge("time", startTime)
                .le("time", endTime)
                .le("price_n", startPrice)
                .ge("price_n", endPrice));
    }

}
