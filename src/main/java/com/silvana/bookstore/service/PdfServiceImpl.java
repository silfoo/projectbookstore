package com.silvana.bookstore.service;

import com.silvana.bookstore.domain.Book;
import com.silvana.bookstore.utils.GeneratePDF;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author silvana
 * @version 0.0.2
 */
@Service("pdfService")
public class PdfServiceImpl implements PdfService{

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(PdfServiceImpl.class);


    @Override
    public ResponseEntity<byte[]> generatePdf(Book book) {

        String namePdf= book.getTitle()+".pdf";

        //create header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("content-disposition", "inline;filename=" + namePdf);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        LOG.debug("Create header of {}", namePdf);

        byte[] pdfArray = GeneratePDF.createPDF(book);
        return new ResponseEntity<>(pdfArray, headers, HttpStatus.OK);

    }
}
