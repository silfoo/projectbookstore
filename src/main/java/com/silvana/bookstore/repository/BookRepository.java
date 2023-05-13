package com.silvana.bookstore.repository;

import com.silvana.bookstore.domain.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * interface using JPARepository for consulting on the book in the data base
 *
 * @author Silvana
 * @version 0.0.2
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


        /**
         * query about sort by lowered
         *
         * @return book list sorted by lowered
         */
        List<Book> findAllByOrderByIsLoweredDesc();

        /**
         * query about sort by published date
         *
         * @return book list sorted by published date
         */
        List<Book> findAllByOrderByPublishedDate();


        /**
         * query to get all books order by field selected
         *
         * @param sort  Sort.by(field name)
         * @return sorter book list
         */
        @Query(value = "SELECT b FROM Book b")
        List<Book> findAllBooks(Sort sort);

}
