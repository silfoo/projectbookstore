package com.silvana.bookstore.utils;

import com.silvana.bookstore.domain.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

/**
 * create pdf format with book detail
 *
 * @author silvana
 * @version 1.0
 */
public class GeneratePDF {

    /**
     * field log, pdf by console different activity in the project
     */
    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(GeneratePDF.class);

     /**
     * create pdf and convert it to byte for show it in the browser
     *
     * @param book is the selected object the type Book
     * @return pdf created in bytes
     */
    public static byte[] createPDF(Book book) {

        LOG.info("Creating PDF of book: {}", book.toString());

        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

                String imgFileName = "src/main/resources/static/images/logo.png";
                PDImageXObject pdImage = PDImageXObject.createFromFile(imgFileName, doc);
                cont.drawImage(pdImage, 70, 600, 150, 150);
                cont.beginText();
                cont.setNonStrokingColor(Color.DARK_GRAY);
                cont.setLeading(18.5f);
                cont.newLineAtOffset(200, 500);
                cont.setFont(PDType1Font.HELVETICA_BOLD, 16);
                String isbn = "Book ISBN: " + book.getIsbn();
                cont.showText(isbn);
                cont.newLine(); cont.newLine(); cont.newLine(); cont.newLine();
                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                String author = "Author: " + book.getAuthor();
                cont.showText(author);
                cont.newLine();
                String title = "Title: " + book.getTitle();
                cont.showText(title);
                cont.newLine();
                String editorial = "Editorial: " + book.getEditorial();
                cont.showText(editorial);
                cont.newLine();
                String category = "Category: " + book.getCategory();
                cont.showText(category);
                cont.newLine();
                String priceCurrency = "Price: " + book.getAmount() + " " + book.getCurrency();
                cont.showText(priceCurrency);
                cont.newLine(); cont.newLine(); cont.newLine(); cont.newLine();
                cont.newLine(); cont.newLine(); cont.newLine(); cont.newLine();
                cont.newLineAtOffset(10, 10);
                cont.setFont(PDType1Font.HELVETICA, 10);
                cont.showText("print date: " + LocalDate.now());
                cont.endText();
                String imgFileName2 = "src/main/resources/static/images/logo2.png";
                PDImageXObject pdImage2 = PDImageXObject.createFromFile(imgFileName2, doc);
                cont.drawImage(pdImage2, 300, 50, 200, 100);

            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            doc.save(out);
            return out.toByteArray();

        } catch (IOException e) {
            LOG.error("Pdf of book {} could not be created. {}", book.getTitle(), e.getMessage());
            return null;
        }

    }
}