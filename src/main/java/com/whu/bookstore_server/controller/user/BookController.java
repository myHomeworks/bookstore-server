package com.whu.bookstore_server.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.whu.bookstore_server.domain.Book;
import com.whu.bookstore_server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user/book")
@Component("BookController")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/recommend")
    public HashMap<String, Object> recommend(@RequestBody String body){
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String userId = object.getString("userId");
        Integer pageRequest = object.getInteger("pageRequest");
        Integer pageSize = object.getInteger("pageSize");
        Page<Book> page = new Page<>(pageRequest, pageSize);
        List<Book> books = bookService.getRecommend(page, userId);

        ret.put("state", "ok");
        ret.put("books", books);
        return ret;
    }

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


}
