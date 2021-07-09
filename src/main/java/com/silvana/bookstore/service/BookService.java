package com.silvana.bookstore.service;

import com.silvana.bookstore.domain.Book;
import com.silvana.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * service for books consultation using BookRepository,
 *
 * @author Silvana
 * @version 1.0
 */
@Service
public class BookService  {

    /**
     * field log, pdf by console different activity in the project
     */
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookService.class);

    /**
     * BookRepository dependency injection
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * query about searching of all book
     *
     * @return book list
     */
    public List<Book> findAll(){
        return this.bookRepository.findAll();
    }

    /**
     * save a new book in the data base
     *
     * @param book, is the new object that will be saved
     */
    public void save(final Book book) {
        this.bookRepository.save(book);
        log.info("Book saved: " + book);
    }

    /**
     * update a book selected
     *
     * @param book, that was modified
     */
    public void update(final Book book) {
        this.bookRepository.save(book);
        log.info("Book updated: " + book);
    }

    /**
     * searching method book by its id
     *
     * @param id is the internal book identification number
     * @return a book with the that id
     */
    public Optional<Book> findByID(Long id){
        return this.bookRepository.findById(id);
    }

    /**
     * deleting method by book id
     *
     * @param id the internal book identification number
     */
    public void deleteById(Long id){
        Optional<Book> book = findByID(id);
        this.bookRepository.deleteById(id);
        log.info("Book deleted: " + book);
    }

    /**
     * method about searching that all books sorted by amount
     *
     * @return book list sorted by amount
     */
    public List<Book> findAllOrderByAmount(){
       return this.bookRepository.findAllByOrderByAmount();
    }

    /**
     * method about searching that all books sorted by author
     *
     * @return book list sorted by author
     */
    public List<Book> findAllOrderByAuthor(){
    return this.bookRepository.findAllByOrderByAuthor();
    }

    /**
     * method about searching that all books sorted by category
     *
     * @return book list sorted by category
     */
    public List<Book> findAllOrderByCategory(){
        return this.bookRepository.findAllByOrderByCategory();
    }

    /**
     * method about searching that all books sorted by ISBN
     *
     * @return book list sorted by ISBN
     */
    public List<Book> findAllOrderByIsbn(){
        return this.bookRepository.findAllByOrderByIsbn();
    }

    /**
     * method about searching that all books sorted by currency
     *
     * @return book list sorted by currency
     */
    public List<Book> findAllOrderByCurrency(){
        return this.bookRepository.findAllByOrderByCurrency();
    }

    /**
     * method about searching that all books sorted by lowered
     *
     * @return book list sorted by lowered
     */
    public List<Book> findAllOrderByIsLowered(){
        return this.bookRepository.findAllByOrderByIsLoweredDesc();
    }

    /**
     * method about searching that all books sorted by title
     *
     * @return book list sorted by title
     */
    public List<Book> findAllOrderByTitle(){
        return this.bookRepository.findAllByOrderByTitle();
    }

    /**
     * method about searching that all books sorted by editorial
     *
     * @return book list sorted by editorial
     */
    public List<Book> findAllOrderByEditorial(){
        return this.bookRepository.findAllByOrderByEditorial();
    }

    /**
     *method about searching that all books sorted by published date
     *
     * @return book list sorted by published date
     */
    public List<Book> findAllOrderByPublishedDate(){
        return this.bookRepository.findAllByOrderByPublishedDate();
    }


}
