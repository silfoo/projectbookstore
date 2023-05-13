package com.silvana.bookstore.controller;

import com.silvana.bookstore.domain.Book;
import com.silvana.bookstore.domain.Category;
import com.silvana.bookstore.domain.Currency;
import com.silvana.bookstore.domain.SortBy;
import com.silvana.bookstore.service.BookServiceImpl;
import com.silvana.bookstore.service.PdfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * class controller where load the data Model and invoke the views
 *
 * @author silvana
 * @version 0.0.2
 */
@Controller
@RequestMapping("/book")
public class BookStoreController {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(BookStoreController.class);

    private final BookServiceImpl bookService;
    private final PdfServiceImpl pdfService;

    /**
     * BookServiceImpl dependency injection by constructor
     */
    @Autowired
    public BookStoreController(BookServiceImpl bookService, PdfServiceImpl pdfService) {
        this.bookService = bookService;
        this.pdfService = pdfService;
    }

    /**
     * this model load all book categories
     *
     * @return category list
     */
    @ModelAttribute("allCategory")
    public List<Category> categories() {

        LOG.info("Show all categories");
        return Arrays.asList(Category.values());
    }

    /**
     * this model load all book currencies
     *
     * @return currency list
     */
    @ModelAttribute("allCurrency")
    public List<Currency> carrencies() {

        LOG.info("Show all currencies");
        return Arrays.asList(Currency.values());
    }

    /**
     * this model load all queries to sort the list of books
     *
     * @return queries to sort list
     */
    @ModelAttribute("sortBy")
    public List<SortBy> sort() {

        LOG.info("Show all field to get sort");
        return Arrays.asList(SortBy.values());
    }

    /**
     * method Get, the path will be "/" or "/list"
     * this is the principal view where show the book list
     *
     * @param model is the list book
     * @return book list
     */
    @GetMapping({"","/", "/list"})
    public String view(Model model) {

        LOG.info("[view] Requested list of books");
        model.addAttribute("allbooks", this.bookService.findAll());
        return "list";
    }

    /**
     * method Get with variable path (book id), show the selected book in the different view
     * where it can be modified.
     *
     * @param id    is the internal book identification number
     * @param model is the view where it can be modified
     * @return the detail view of the book selected by the id
     */
    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {

        LOG.info("[edit] Requested edit book. Id: {}", id.toString());
        model.addAttribute("book", bookService.findByID(id).orElse(new Book()));
        return "edit";
    }

    /**
     * method Post with variable path (book id), save the modification of the book, if you cannot be saved,
     * so, its show in the view which was the error.
     *
     * @param id            is the internal book identification number
     * @param book          object shown in the view within the model with validation by MessageValidatorConfig
     * @param bindingResult catch all errors to show in the view
     * @param model         is the view where it was modified
     * @return the book list with new book modification, but, if has error, return all errors in the same view
     */
    @PostMapping("/edit/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid Book book, BindingResult bindingResult, Model model) {

        LOG.info("[update] Requested save changes of book. Id: {}", id.toString());

        if (bindingResult.hasErrors()) {
            LOG.error("[update] Error occurred during saved changes of book. Id: {}", id);
            return "edit";
        }

        bookService.update(book);
        model.addAttribute("book", book);
        return "redirect:/book/list";
    }

    /**
     * method Get where show the new view where it can be created a new book
     *
     * @param book is the new object to will be created
     * @return the view create
     */
    @GetMapping("/create")
    public String create(final Book book) {

        LOG.info("[create] Requested create a new book. Title: {}", book.getTitle());
        book.setIsLowered(Boolean.TRUE);
        return "create";
    }

    /**
     * method Post with path variable (book id). By id, the book can be deleted and then,
     * show the new boo list without this book
     *
     * @param id    is the internal book identification number that will be used for the elimination of the book
     * @param model is the new views of th book list without the deleted book
     * @return updated book list
     */
    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {

        LOG.info("[delete] Requested delete book. Id: {}", id.toString());
        bookService.deleteById(id);
        model.addAttribute("book", bookService.findAll());
        return "redirect:/book/list";
    }

    /**
     * method Get with path variable (id), search selected book,
     * generated the pdf and show this in the browser
     *
     * @param id is the internal book identification number that will be used for the find of the book
     * @return the book selected in pdf format
     */
    @GetMapping(value = "/pdf/{id}", produces = "application/pdf")
    public ResponseEntity<byte[]> getPdf(@PathVariable("id") Long id) {

        LOG.info("[getPdf] Requested to create pdf of book id: {}", id.toString());
        Book book = bookService.findByID(id).orElse(new Book());

        return pdfService.generatePdf(book);
    }

    /**
     * method Post that save the new book in the database. But, if you cannot be saved,
     * its show in the view which was the error.
     *
     * @param book          with validations, is the new book
     * @param bindingResult catch all errors to show in the view
     * @param model         is the view where it was created
     * @return the book list with new book created, but, if has error, return all errors in the same view
     */
    @PostMapping("/save")
    public String addBook(@Valid Book book, BindingResult bindingResult, Model model) {

        LOG.info("[addBook] Requested save new book. Title: {}", book.getTitle());

        if (bindingResult.hasErrors()) {
            LOG.error("[addBook] Error occurred during saved new book. Tittle: {}", book.getTitle());
            return "create";
        }

        bookService.save(book);
        model.addAttribute("book", book);
        return "redirect:/book/list";
    }

    /**
     * method Post about sort the book list with different options,
     * this option are the different columns in the list
     *
     * @param sortby is a @Requestparam and the query about how the list will be ordered
     * @param model  is the new list with that new order
     * @return book list with the order that was selected in the param
     */
    @PostMapping(params = "sortby", path = "/sort")
    public String sort(@RequestParam String sortby, Model model) {

        LOG.info("[sort] Requested list sorter by {}", sortby);
        model.addAttribute("allbooks", this.bookService.findAllBooksOrderByField(sortby));

        return "list";
    }


}