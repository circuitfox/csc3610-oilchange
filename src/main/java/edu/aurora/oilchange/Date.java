package edu.aurora.oilchange;

import java.time.LocalDate;

/**
 * A date with month, day, and year.
 */
public class Date implements Comparable<Date> {
    public static final int MAX_MONTH = 12;
    public static final int MAX_DAY = 31;

    /**
     * The month of the year, from 1 (January) to 12 (December)
     */
    private int month;

    /**
     * The day of the month, from 1 to, maximally, 31
     */
    private int day;

    /**
     * The year
     */
    private int year;

    /**
     * Creates a new date from today's date.
     */
    public Date() {
        LocalDate today = LocalDate.now();
        this.month = today.getMonthValue();
        this.day = today.getDayOfMonth();
        this.year = today.getYear();
    }

    /**
     * Creates a new date from the supplied fields.
     *
     * @param month the month of the year
     * @param day   the day of the month
     * @param year  the year
     */
    public Date(int month, int day, int year) {
        // these are already validated before they get to us,
        // but just to be safe we'll do some basic bounds checking
        if (month < 0 || month > MAX_MONTH) {
            throw new InvalidDateException("The month must be between 1 and 12");
        } else if (day < 0 || day > MAX_DAY) {
            throw new InvalidDateException("The day must be between 1 and 31");
        }
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month < 0 || month > MAX_MONTH) {
            throw new InvalidDateException("The month must be between 1 and 12");
        }
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        // again, minimal bounds checking (this should be validated already)
        if (day < 0 || day > MAX_DAY) {
            throw new InvalidDateException("The day must be between 1 and 31");
        }
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns a string representation of the date, in the format yyyy-mm-dd
     *
     * @return the date in the format yyyy-mm-dd
     */
    @Override
    public String toString() {
        return String.format("%d-%02d-%02d", year, month, day);
    }

    // returns date in ascending order starting with year, then month, then the day
    @Override
    public int compareTo(Date date) {
        if (this.year > date.year)
            return 1;
        else if (this.year < date.year)
            return -1;
        else if (this.month > date.month)
            return 1;
        else if (this.month < date.month)
            return -1;
        else if (this.day > date.day)
            return 1;
        else if (this.day < date.day)
            return -1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date = (Date)o;

        if (month != date.month) return false;
        if (day != date.day) return false;
        return year == date.year;

    }

    @Override
    public int hashCode() {
        int result = month;
        result = 31 * result + day;
        result = 31 * result + year;
        return result;
    }
}
