package com.silvana.bookstore.service;

import com.silvana.bookstore.domain.Book;

import java.util.List;
import java.util.Optional;

/**
 * interface for books consultation
 *
 * @author Silvana
 * @version 0.0.2
 */
public interface BookService {

    /**
     * query about searching of all book
     *
     * @return book list
     */
     List<Book> findAll();

    /**
     * method about searching that all books sorted by amount
     *
     * @return book list sorted by amount
     */
    List<Book> findAllBooksOrderByField(String field);

    /**
     * save a new book in the data base
     *
     * @param book, is the new object that will be saved
     */
     void save(final Book book);

    /**
     * update a book selected
     *
     * @param book, that was modified
     */
     void update(final Book book);

    /**
     * searching method book by its id
     *
     * @param id is the internal book identification number
     * @return a book with the that id
     */
     Optional<Book> findByID(Long id);

    /**
     * deleting method by book id
     *
     * @param id the internal book identification number
     */
     void deleteById(Long id);

}
