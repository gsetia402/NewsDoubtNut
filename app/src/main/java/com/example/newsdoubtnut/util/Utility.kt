package com.example.newsdoubtnut.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun getFormatedDate(serverDate: String):String {

    var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    var postedDate = LocalDateTime.parse(serverDate, formatter)

    var currentYearDateFormat = DateTimeFormatter.ofPattern("d MMMM, HH:mm")
    var otherYearDateFormate = DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm")
    val today = Calendar.getInstance()
    val currentYear = today.get(Calendar.YEAR);

    if (currentYear == postedDate.year)
        return LocalDateTime.parse(serverDate, formatter).format(currentYearDateFormat);
    else
        return LocalDateTime.parse(serverDate, formatter).format(otherYearDateFormate);
}

