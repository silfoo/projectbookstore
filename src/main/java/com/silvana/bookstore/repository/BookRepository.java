package com.silvana.bookstore.repository;

import com.silvana.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * interface using JPARepository for consulting on the book in the data base
 *
 * @author Silvana
 * @version 1.0
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

        /**
         * query about sort by Author
         *
         * @return book list sorted by author
         */
        List<Book> findAllByOrderByAuthor();

        /**
         * query about sort by Amount
         *
         * @return book list sorted by amount
         */
        List<Book> findAllByOrderByAmount();

        /**
         * query about sort by Categories
         *
         * @return book list sorted by category
         */
        List<Book> findAllByOrderByCategory();

        /**
         * query about sort by Currency
         *
         * @return book list sorted by currency
         */
        List<Book> findAllByOrderByCurrency();

        /**
         * query about sort by lowered
         *
         * @return book list sorted by lowered
         */
        List<Book> findAllByOrderByIsLoweredDesc();

        /**
         * query about sort by Title
         *
         * @return book list sorted by lowered
         */
        List<Book> findAllByOrderByTitle();

        /**
         * query about sort by Editorial
         *
         * @return book list sorted by editorial
         */
        List<Book> findAllByOrderByEditorial();

        /**
         * query about sort by published date
         *
         * @return book list sorted by published date
         */
        List<Book> findAllByOrderByPublishedDate();

        /**
         * query about sort by Isbn
         *
         * @return book list sorted by Isbn
         */
        List<Book> findAllByOrderByIsbn();


}
