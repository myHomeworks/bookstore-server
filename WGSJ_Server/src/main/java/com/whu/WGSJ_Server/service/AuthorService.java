package com.whu.WGSJ_Server.service;

import com.whu.WGSJ_Server.domain.Author;
import com.whu.WGSJ_Server.domain.AuthorBook;
import com.whu.WGSJ_Server.domain.Book;

import java.util.List;

public interface AuthorService {

    Integer addAuthor(Author author);

    Integer deleteAuthorById(String authorId);

    Integer updateAuthor(Author author);

    Author getAuthorById(String authorId);

}
