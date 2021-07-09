package com.silvana.bookstore.utils;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;


/**
 * convert the java date to sql date to save it in the database and
 * convert the sql date to the java date to display it.
 *
 * @author Silvana
 * @version 1.0
 */
@Component
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {


    /**
     *  convert the localDate (java) to Date (sql)
     *
     * @param attribute of type LocalDate
     * @return this LocalDate parsed to Date
     */
    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        return attribute == null ? null : Date.valueOf(attribute);
    }

    /**
     * convert the Date (sql) to LocalDate (java)
     *
     * @param dbData of type Date
     * @return this Date parsed to LocalDate
     */
    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        return dbData == null ? null : dbData.toLocalDate();
    }

}
