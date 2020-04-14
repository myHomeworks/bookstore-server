package com.whu.WGSJ_Server.service;

import com.whu.WGSJ_Server.domain.Author;
import com.whu.WGSJ_Server.domain.AuthorBook;
import com.whu.WGSJ_Server.domain.Book;

import java.util.List;

public interface AuthorBookService {
    Integer addAuthorBook(AuthorBook authorBook);

    Integer deleteAuthorBookByBookId(String bookId);

    Integer deleteAuthorBookByAuthorId(String authorId);

    List<Author> getAuthorsByBookId(String bookId);

    List<Book>  getBooksByAuthorId(String authorId);
}
