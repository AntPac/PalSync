package com.example.demo;
import java.time.*;

/**
 *
 */
public class CalendarSetup {
    LocalDate currentDate;
    Object[][] Calendar;

    public CalendarSetup() {
        currentDate = LocalDate.now();
        Calendar = new Object[6][7];
    }

    public void blank() {
        for (int j = 0; j < Calendar.length; j++) {
            for (int i = 0; i < Calendar[j].length; i++) {
                Calendar[j][i] = " ";
            }
        }
    }

    public void calendarMonth() {
        blank();
        LocalDate ld = currentDate.withDayOfMonth(1);
        int day = ld.getDayOfWeek().getValue() % 7;
        int days = ld.lengthOfMonth();
        int k = 1;
        for (int j = 0; j < Calendar.length; j++) {
            for (int l = 0; l < Calendar[j].length && k <= days; l++) {
                if (j == 0 && l < day) {
                    continue;
                }
                if (k <= days) {
                    Calendar[j][l] = k++;
                }
            }
        }
    }

    public void changeMonth(boolean change) {
        if (change) {
            currentDate = currentDate.plusMonths(1);
            calendarMonth();
        } else {
            currentDate = currentDate.minusMonths(1);
            calendarMonth();
        }
    }

    public void changeYear(boolean change) {
        if (change) {
            currentDate = currentDate.plusYears(1);
            calendarMonth();
        } else {
            currentDate = currentDate.minusYears(1);
            calendarMonth();
        }

    }

    public void setDate(int m, int y) {
        currentDate = currentDate.withMonth(m).withYear(y);
        calendarMonth();
    }

    public Object[][] getCalendar() {
        return Calendar;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void printMatrix() {
        for (int j = 0; j < Calendar.length; j++) {
            for (int i = 0; i < Calendar[j].length; i++) {
                System.out.print(Calendar[j][i] + "\t");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        CalendarSetup today = new CalendarSetup();
        today.calendarMonth();
        today.printMatrix();
        today.changeMonth(true);
        today.printMatrix();
        today.changeYear(false);
        today.printMatrix();
        today.setDate(2, 2025);
        today.printMatrix();
    }
}


