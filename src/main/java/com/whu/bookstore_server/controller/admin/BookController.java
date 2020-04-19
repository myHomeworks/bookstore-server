package com.whu.bookstore_server.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.plugins.Page;
import com.whu.bookstore_server.config.UploadConfig;
import com.whu.bookstore_server.domain.*;
import com.whu.bookstore_server.service.*;
import com.whu.bookstore_server.utils.File;
import com.whu.bookstore_server.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private UploadConfig uploadConfig;

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    //    @PostMapping("/add")
//    public HashMap<String, Object> add(@RequestBody String body){
//        HashMap<String, Object> ret = new HashMap<>();
//        JSONObject object = JSONObject.parseObject(body);
//
//        ret.put("state", "ok");
//        return ret;
//    }
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/detail")
    public HashMap<String, Object> detail(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String bookId = object.getString("bookId");
        Book book = bookService.getBookById(bookId);
        ret.put("book", book);
        ret.put("state", "ok");
        return ret;
    }

    // 接口写好了，但没啥好查询的。。。
    @PostMapping("/search")
    public HashMap<String, Object> search(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        Integer pageSize = object.getInteger("pageSize");
        Integer pageRequest = object.getInteger("pageRequest");
        String key = object.getString("key");

        Page<Book> page = new Page<>(pageRequest, pageSize);
//        (Page<Book> page, String bookName, String authorName, String publisherName,
//                String startTime, String endTime, Double startPrice, Double endPrice);
        List<Book> books = bookService.getBookByComplexConditions(page, key, key, key,
                "1970-01-01", "2020-12-31", 0.0, 9999.0);

        ret.put("bookList", books);
        ret.put("state", "ok");
        return ret;
    }

    @PostMapping("/searchByClassId")
    public HashMap<String, Object> searchByClassId(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        Integer pageSize = object.getInteger("pageSize");
        Integer pageRequest = object.getInteger("pageRequest");
        String classId = object.getString("classId");
        Page<Book> page = new Page<>(pageRequest, pageSize);

        List<Book> books = bookService.getBookByClassId(page, classId);
        ret.put("bookList", books);
        ret.put("state", "ok");
        return ret;
    }

    @PostMapping("/add")
    public HashMap<String, Object> add(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        Book book = JSONObject.parseObject(body, new TypeReference<Book>() {
        });
        if (book.getBookId() == null || book.getBookId().equals("")) {
            book.setBookId(UUID.randomUUID().toString());
        }

        this.handleBookIntro(book);
        this.handleBookDetail(book);
        this.handleBookAuthor(book);
        this.handleBookPublisher(book);

        book.setCharge(Util.round(book.getPriceN() / book.getPriceT(), 2, BigDecimal.ROUND_HALF_UP));
        book.setTime(dateFormat.format(new Date()));
        book.setFullName(book.getName());
        bookService.addBook(book);
        ret.put("state", "ok");
        return ret;
    }

    @PostMapping("/upload")
    public HashMap<String, Object> uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile[] files) {
        HashMap<String, Object> ret = new HashMap<>();
        if (files != null && files.length >= 1) {
            try {
                for (MultipartFile file : files) {
//                    String xx = request.getParameter("xx");
                    String type = File.getFileTypeByName(Objects.requireNonNull(file.getOriginalFilename())); // 文件后缀
                    String fileName = UUID.randomUUID().toString() + type;
                    String relativePath = "images/books/" +
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";
                    File.writeFile(file.getBytes(), uploadConfig.getUploadPath() + relativePath, fileName);
                    ret.put("state", "ok");
                    ret.put("path", relativePath + fileName);
                }
            } catch (Exception ex) {
                log.error("/admin/book/upload" + ex.getMessage());
                ret.put("state", "failUpload");
            }
        }
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
