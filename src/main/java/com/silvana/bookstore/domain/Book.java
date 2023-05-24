package com.silvana.bookstore.domain;

import com.silvana.bookstore.utils.LocalDateConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * persistence with @annotation.
 * class detailing the book.
 *
 * @author Silvana
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table
public class Book implements Serializable {

    /**
     * product id
     * primary key, identifier in the book table
     * is the internal book identification number
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * book name with validations: Cannot be Empty, blank and null, and only 100 lenght
     * this message is manager by messages.properties (i18n)
     *
     */
    @NotEmpty(message = "{notempty.book.title}")
    @NotBlank(message = "{notblank.book.title}")
    @NonNull
    @Length(max=100)
    private String title;

    /**
     * International Standard Book Number with validations:
     * Cannot be Empty, blank and null.
     * this message is manager by messages.properties (i18n)
     */
    @Column(unique = true)
    @NotEmpty (message = "{notempty.book.isbn}")
    @NotBlank (message = "{notblank.book.isbn}")
    @NonNull
    private String isbn;

    /**
     * name of person who written that book with validations: Cannot be null and 100 length
     * this message is manager by messages.properties (i18n)
     *
     */
    @Column(length = 100)
    @NotEmpty(message = "{notempty.book.author}")
    private String author;

    /**
     * published date using validations: local converter and not null.
     * this message is manager by messages.properties (i18n)
     */
    @Convert(converter = LocalDateConverter.class)
    @NotNull(message = "{notnull.book.publishedDate}")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate publishedDate;

    /**
     * company that published the book with validations: 100 length and not empty.
     * this message is manager by messages.properties (i18n)
     */
    @Column(length = 100)
    @NotEmpty (message = "{notempty.book.editorial}")
    private String editorial;

    /**
     * if the value is lowered it's true, if not, it is false
     */
    private Boolean isLowered;

    /**
     * book category, it's an Enum Class
     */
    @Enumerated(EnumType.STRING)
    private Category category;

    /**
     * book Currency, its a Enum.class
     */
    @Enumerated(EnumType.STRING)
    private Currency currency;

    /**
     * Price of the book the type BigDecimal using validations:
     * Cannot be null, digits max 5 and the fraction 2, the min value is 0.0
     * this message is manager by messages.properties (i18n)
     */
    @NotNull(message = "{notnull.book.amount}")
    @DecimalMin(value = "0.0" , message = "{decimalmin.bok.amount}")
    @Digits(integer = 5, fraction = 2, message = "{digits.book.amount}")
    private BigDecimal amount;


}
