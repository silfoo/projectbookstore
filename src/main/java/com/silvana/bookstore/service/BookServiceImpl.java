package com.silvana.bookstore.service;

import com.silvana.bookstore.domain.Book;
import com.silvana.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * service for books consultation using BookRepository,
 *
 * @author Silvana
 * @version 0.0.2
 */
@Service
public class BookServiceImpl implements BookService {

    /**
     * field log, pdf by console different activity in the project
     */
    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    /**
     * BookRepository dependency injection by constructor
     */
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findByID(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void save(final Book book) {
        bookRepository.save(book);
        LOG.info("Book saved: " + book);
    }

    @Override
    public void update(final Book book) {
        bookRepository.save(book);
        LOG.info("Book updated: " + book);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Book> book = findByID(id);
        bookRepository.deleteById(id);
        LOG.info("Book deleted: " + book);
    }

    @Override
    public List<Book> findAllBooksOrderByField(String field) {

        List<Book> bookList;

        if (field.equalsIgnoreCase("IS_LOWERED")) {

            bookList = bookRepository.findAllByOrderByIsLoweredDesc();
        } else if (field.equalsIgnoreCase("PUBLISHED_DATE")) {

            bookList = bookRepository.findAllByOrderByPublishedDate();
        } else {

            String sort = (field.equalsIgnoreCase("Price")) ? "amount" : field.toLowerCase();
            bookList = bookRepository.findAllBooks(Sort.by(sort));
        }

        LOG.info("Show books list order by {}", field);
        return bookList;
    }


}
