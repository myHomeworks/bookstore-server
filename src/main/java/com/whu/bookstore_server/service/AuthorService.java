package com.whu.bookstore_server.service;

import com.whu.bookstore_server.domain.Author;
import com.whu.bookstore_server.domain.AuthorBook;
import com.whu.bookstore_server.domain.Book;

import java.util.List;

public interface AuthorService {

    Integer addAuthor(Author author);

    Integer deleteAuthorById(String authorId);

    Integer updateAuthor(Author author);

    Author getAuthorById(String authorId);

    Author getAuthorByName(String name);

}
