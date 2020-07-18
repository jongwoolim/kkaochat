package me.jdding.kkaochat.application.of.classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CourseGrade {

    private final int year;
    private final int month;
    private final int dayOfMonth;

    public CourseGrade(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public int getYear() {
        return year;
    }


    public int getMonth() {
        return month;
    }


    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public long getNumberOfDays(){
        LocalDate now = LocalDate.now();
        LocalDate of = LocalDate.of(year, month, dayOfMonth);
        long between = ChronoUnit.DAYS.between(now, of);
        return between;
    }

}
