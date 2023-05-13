package com.silvana.bookstore.service;

import com.silvana.bookstore.domain.Book;
import org.springframework.http.ResponseEntity;

/**
 * @author silvana
 * @version 0.0.2
 */
public interface PdfService {


    /**
     * Generate pdf and add header to show it in the browser
     *
     * @param book book to show in pdf
     * @return pdf resultant
     */
    ResponseEntity<byte[]> generatePdf(Book book);
}
