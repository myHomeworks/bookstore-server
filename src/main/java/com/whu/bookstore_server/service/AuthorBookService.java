package com.whu.bookstore_server.service;

import com.whu.bookstore_server.domain.Author;
import com.whu.bookstore_server.domain.AuthorBook;
import com.whu.bookstore_server.domain.Book;

import java.util.List;

public interface AuthorBookService {
    Integer addAuthorBook(AuthorBook authorBook);

    Integer deleteAuthorBookByBookId(String bookId);

    Integer deleteAuthorBookByAuthorId(String authorId);

    List<Author> getAuthorsByBookId(String bookId);

    List<Book>  getBooksByAuthorId(String authorId);
}
