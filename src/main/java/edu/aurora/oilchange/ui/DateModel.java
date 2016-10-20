package edu.aurora.oilchange.ui;

import edu.aurora.oilchange.Date;
import edu.aurora.oilchange.InvalidDateException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DateModel {
    private Date date;

    private IntegerProperty month;
    private IntegerProperty day;
    private IntegerProperty year;

    public DateModel() {
        date = new Date();

        month = new SimpleIntegerProperty(this, "month", date.getMonth());
        day = new SimpleIntegerProperty(this, "day", date.getDay());
        year = new SimpleIntegerProperty(this, "year", date.getYear());

        month.addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            if (value <= 0 || value > Date.MAX_MONTH) {
                throw new InvalidDateException("Month must be less than 0 or greater than 12");
            }
            date.setMonth(value);
        });

        day.addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            if (!validDay(value, date.getMonth(), date.getYear())) {
                throw new InvalidDateException("Day " + value + " invalid for month " + date.getMonth());
            }
            date.setDay(value);
        });

        year.addListener((observable, oldValue, newValue) -> date.setYear(newValue.intValue()));
    }

    private boolean validDay(int day, int month, int year) {
        // date 1-30 (april, june, september, november)
        // date 1-28 (february, non-leap year)
        // date 1-29 (february, leap year)
        // date 1-31 (all others)
        if (month == 2) {
            int maxDay = 28;
            if (isLeapYear(year)) {
                maxDay = 29;
            }
            if (day <= 0 || day > maxDay) {
                return false;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day <= 0 || day > 30) {
                return false;
            }
        } else {
            if (day <= 0 || day > Date.MAX_DAY) {
                return false;
            }
        }
        return true;
    }

    private boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else return year % 4 == 0;
    }

    public int getMonth() {
        return month.get();
    }

    public IntegerProperty monthProperty() {
        return month;
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public int getDay() {
        return day.get();
    }

    public IntegerProperty dayProperty() {
        return day;
    }

    public void setDay(int day) {
        this.day.set(day);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;

        month.set(date.getMonth());
        day.set(date.getDay());
        year.set(date.getYear());
    }

    @Override
    public String toString() {
        return String.format("Date: %d/%d/%d\n", month.get(), day.get(), year.get());
    }
}
