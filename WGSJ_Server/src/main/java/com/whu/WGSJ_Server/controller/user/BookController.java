package com.whu.WGSJ_Server.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.whu.WGSJ_Server.domain.Book;
import com.whu.WGSJ_Server.mapper.BookMapper;
import com.whu.WGSJ_Server.service.BookService;
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

    @PostMapping("/classification")
    public HashMap<String, Object> classification(@RequestBody String body){
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

}
