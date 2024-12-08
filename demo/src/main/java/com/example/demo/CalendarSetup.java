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
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);

        int startDayOfWeek = (firstDayOfMonth.getDayOfWeek().getValue() % 7);
        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        int dayCounter = 1;

        for (int row = 0; row < Calendar.length; row++) {
            for (int col = 0; col < Calendar[row].length; col++) {

                if (row == 0 && col < startDayOfWeek) {
                    Calendar[row][col] = " ";
                    continue;
                }

                if (dayCounter <= daysInMonth) {
                    Calendar[row][col] = dayCounter++;
                } else {
                    Calendar[row][col] = " ";
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

    public void printCalendarMatrix() {
        System.out.println("Calendar Matrix:");
        for (Object[] row : Calendar) {
            for (Object cell : row) {
                System.out.print((cell == null ? "null" : cell.toString()) + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CalendarSetup today = new CalendarSetup();
        today.calendarMonth();
        today.printCalendarMatrix();
        today.printMatrix();
        today.changeMonth(true);
        today.printMatrix();
        today.changeYear(false);
        today.printMatrix();
        today.setDate(2, 2025);
        today.printMatrix();
    }
}


