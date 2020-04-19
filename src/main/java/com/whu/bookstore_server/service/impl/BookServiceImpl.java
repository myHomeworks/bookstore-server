package com.whu.bookstore_server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.whu.bookstore_server.domain.*;
import com.whu.bookstore_server.mapper.*;
import com.whu.bookstore_server.service.BookService;
import com.whu.bookstore_server.domain.Publisher;
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

    @Autowired
    private BookIntroMapper bookIntroMapper;

    @Autowired
    private BookDetailMapper bookDetailMapper;

    @Override
    public List<Book> getRecommend(Page<Book> page, String userId) {
        // 这个是首页每日推荐的书籍，没有用户数据，直接用乱序(userId没用到)
        List<Book> books = bookMapper.selectPage(page, new EntityWrapper<Book>()
                .orderBy("RAND()"));
        this.setPublisherForBook(books);
        this.setAuthorsForBook(books);
        this.setBookDetail(books);
        this.setBookIntro(books);

        return books;
    }

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
    public Integer addBookIntro(BookIntro bookIntro) {
        return bookIntroMapper.insert(bookIntro);
    }

    @Override
    public Integer addBookDetail(BookDetail bookDetail) {
        return bookDetailMapper.insert(bookDetail);
    }

    @Override
    public Integer delBookIntroById(String bookI) {
        return bookIntroMapper.deleteById(bookI);
    }

    @Override
    public Integer delBookDetailById(String bookD) {
        return bookDetailMapper.deleteById(bookD);
    }

    @Override
    public Book getBookById(String bookId) {
        Book book = bookMapper.selectById(bookId);
        this.setPublisherForBook(book);
        this.setAuthorsForBook(book);
        this.setBookDetail(book);
        this.setBookIntro(book);

        return book;
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
        List<Book> books = bookMapper.selectPage(page, new EntityWrapper<Book>()
                .in("book_id", bookIds)
                .like("name", bookName == null ? "" : bookName)
                .ge("time", startTime)
                .le("time", endTime)
                .ge("price_n", startPrice)
                .le("price_n", endPrice));
        this.setAuthorsForBook(books);
        this.setPublisherForBook(books);
        this.setBookDetail(books);
        this.setBookIntro(books);

        return books;
    }

    @Override
    public List<Book> getBookByClassId(Page<Book> page, String classId) {
        List<Book> books = bookMapper.selectPage(page, new EntityWrapper<Book>()
                .eq("class_id", classId)
                .orderBy("time", false));

        this.setAuthorsForBook(books);
        this.setPublisherForBook(books);
        this.setBookDetail(books);
        this.setBookIntro(books);

        return books;
    }

    private void setPublisherForBook(List<Book> books) {
        for (Book book : books) {
            this.setPublisherForBook(book);
        }
    }

    private void setPublisherForBook(Book book) {
        List<PublisherBook> publisherBooks = publisherBookMapper.selectList(new EntityWrapper<PublisherBook>()
                .eq("book_id", book.getBookId()));
        book.setPublisher(publisherMapper.selectById(publisherBooks.get(0).getPublisherId()).getName());
    }

    private void setAuthorsForBook(List<Book> books) {
        for (Book book : books) {
            this.setAuthorsForBook(book);
        }
    }

    private void setAuthorsForBook(Book book) {
        List<AuthorBook> authorBooks = authorBookMapper.selectList(new EntityWrapper<AuthorBook>()
                .eq("book_id", book.getBookId()));
        List<String> authorsName = new ArrayList<String>();
        for (AuthorBook authorBook : authorBooks) {
            authorsName.add(authorMapper.selectById(authorBook.getAuthorId()).getName());
        }
        book.setAuthorList(authorsName);
    }

    private void setBookIntro(List<Book> books) {
        for (Book book : books) {
            this.setBookIntro(book);
        }
    }

    private void setBookIntro(Book book) {
        List<BookIntro> bookIntros = bookIntroMapper.selectList(new EntityWrapper<BookIntro>()
                .eq("book_id", book.getBookId()));
        List<String> introUrls = new ArrayList<>();
        for (BookIntro bookIntro : bookIntros) {
            introUrls.add(bookIntro.getIntroUrl());
        }
        book.setIntroList(introUrls);
    }

    private void setBookDetail(List<Book> books) {
        for (Book book : books) {
            this.setBookDetail(book);
        }
    }

    private void setBookDetail(Book book) {
        List<BookDetail> bookDetails = bookDetailMapper.selectList(new EntityWrapper<BookDetail>()
                .eq("book_id", book.getBookId()));
        List<String> detailUrls = new ArrayList<>();
        for (BookDetail bookDetail : bookDetails) {
            detailUrls.add(bookDetail.getDetailUrl());
        }
        book.setDetailList(detailUrls);
    }

}
