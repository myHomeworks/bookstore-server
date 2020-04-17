package com.whu.bookstore_server.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.plugins.Page;
import com.whu.bookstore_server.domain.*;
import com.whu.bookstore_server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/book")
@Component("AdminBookController")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorBookService authorBookService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PublisherBookService publisherBookService;

    @Autowired
    private ClassificationService classService;

    //    @PostMapping("/add")
//    public HashMap<String, Object> add(@RequestBody String body){
//        HashMap<String, Object> ret = new HashMap<>();
//        JSONObject object = JSONObject.parseObject(body);
//
//        ret.put("state", "ok");
//        return ret;
//    }
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/add")
    public HashMap<String, Object> add(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        Book book = JSONObject.parseObject(body, new TypeReference<Book>() {
        });
        if (book.getBookId() == null || book.getBookId().equals("")) {
            book.setBookId(UUID.randomUUID().toString());
        }

        this.handleBookIntro(book);
        this.handleBookDetail(book);
        this.handleBookAuthor(book);
        this.handleBookPublisher(book);

        book.setTime(dateFormat.format(new Date()));
        bookService.addBook(book);
        ret.put("state", "ok");
        return ret;
    }

    @PostMapping("/all")
    public HashMap<String, Object> all(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        Integer pageSize = object.getInteger("pageSize");
        Integer pageRequest = object.getInteger("pageRequest");
        String userId = object.getString("userId");

        Page<Book> page = new Page<>(pageRequest, pageSize);
        List<Book> books = bookService.getRecommend(page, userId);

        ret.put("bookList", books);
        ret.put("state", "ok");
        return ret;
    }

    private void handleBookIntro(Book book) {
        BookIntro bookIntro = new BookIntro();
        bookIntro.setBookId(book.getBookId());
        for (String introUrl : book.getIntroList()) {
            bookIntro.setBookI(UUID.randomUUID().toString());
            bookIntro.setIntroUrl(introUrl);
            bookService.addBookIntro(bookIntro);
        }
    }

    private void handleBookDetail(Book book) {
        BookDetail bookDetail = new BookDetail();
        bookDetail.setBookId(book.getBookId());
        for (String detailUrl : book.getDetailList()) {
            bookDetail.setBookD(UUID.randomUUID().toString());
            bookDetail.setDetailUrl(detailUrl);
            bookService.addBookDetail(bookDetail);
        }
    }

    private void handleBookAuthor(Book book) {
        for (String name : book.getAuthorList()) {
            Author author = authorService.getAuthorByName(name);
            if (author == null) {
                author = new Author();
                author.setAuthorId(UUID.randomUUID().toString());
                author.setName(name);
                authorService.addAuthor(author);
            }
            AuthorBook authorBook = new AuthorBook();
            authorBook.setAbId(UUID.randomUUID().toString());
            authorBook.setAuthorId(author.getAuthorId());
            authorBook.setBookId(book.getBookId());
            authorBookService.addAuthorBook(authorBook);
        }
    }

    private void handleBookPublisher(Book book) {
        Publisher publisher = publisherService.getPublisherByName(book.getPublisher());
        if (publisher == null) {
            publisher = new Publisher();
            publisher.setName(book.getPublisher());
            publisher.setPublisherId(UUID.randomUUID().toString());
            publisherService.addPublisher(publisher);
        }
        PublisherBook publisherBook = new PublisherBook();
        publisherBook.setPbId(UUID.randomUUID().toString());
        publisherBook.setBookId(book.getBookId());
        publisherBook.setPublisherId(publisher.getPublisherId());
        publisherBookService.addPublisherBook(publisherBook);
    }
}
