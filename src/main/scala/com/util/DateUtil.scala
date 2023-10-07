package com.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

object DateUtil {
  // set date pattern
  val DATE_PATTERN = "dd.MM.yyyy"
  // set date formatter
  val DATE_FORMATTER =  DateTimeFormatter.ofPattern(DATE_PATTERN)
  
  // convert localDate into String
  implicit class DateFormater (val date : LocalDate){
     def asString : String = {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }
  }

  // convert string into localDate
  implicit class StringFormater (val data : String) {
    def parseLocalDate : LocalDate = {
        try {
          LocalDate.parse(data, DATE_FORMATTER)
        } catch {
          case  e: DateTimeParseException => null
        }
    }
    def isValid : Boolean = {
      data.parseLocalDate != null
    }
  }
}
