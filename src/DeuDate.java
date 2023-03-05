public class DeuDate {

    private static int[] monthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int day;
    private int month;
    private int year;

    public DeuDate() {
    }

    public DeuDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static int[] getMonthDays() {
        return monthDays;
    }

    public static void setMonthDays(int[] monthDays) {
        DeuDate.monthDays = monthDays;
    }

    public static DeuDate convertStringDateToDeuDate(String stringDate) {
        String[] date = stringDate.split("\\.");
        DeuDate deuDate = new DeuDate();
        deuDate.day = Integer.parseInt(date[0]);
        deuDate.month = Integer.parseInt(date[1]);
        deuDate.year = Integer.parseInt(date[2]);
        return deuDate;
    }

    public static int convertDeuDateToDayCount(DeuDate deuDate) {
        //#ref:https://www.geeksforgeeks.org/find-number-of-days-between-two-given-dates/
        int dayCount = deuDate.day;
        for (int i = 1; i < deuDate.month; i++) {
            dayCount += monthDays[i];
        }
        if (deuDate.year % 4 == 0 && deuDate.month > 2) {//leap year
            dayCount++;
        }
        dayCount += deuDate.year * 365;
        return dayCount;
    }

    public static int getNumberOfDaysOfMonth(int month, int year) {
        if (month == 2) {
            if (year % 4 == 0) {
                return 29;
            } else {
                return 28;
            }
        } else {
            return monthDays[month];
        }
    }

    public static DeuDate[] getDaysBetweenDates(DeuDate startDate, DeuDate endDate) {
        DeuDate[] days = new DeuDate[endDate.getGapBetweenDates(startDate) + 1];
        int currentYear = startDate.year;
        int currentMonth = startDate.month;
        int currentDay = startDate.day;
        for (int i = 0; i < days.length; i++) {
            int dayCountOfTheMonth = DeuDate.getNumberOfDaysOfMonth(currentMonth, startDate.year);
            if (currentDay > dayCountOfTheMonth) {
                currentMonth++;
                if (currentMonth > 12) {
                    currentYear++;
                    currentMonth = 1;
                }
                currentDay = 1;
            }
            days[i] = new DeuDate(currentDay, currentMonth, currentYear);
            currentDay++;
        }
        return days;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return day + "." + month + "." + year;
    }

    public boolean isBetweenDates(DeuDate startDate, DeuDate endDate) {
        if (this.year >= startDate.year && this.year <= endDate.year) {
            return this.month < startDate.month || this.month > endDate.month || (this.day < startDate.day || this.day > endDate.day);
        }
        return true;
    }

    private int getGapBetweenDates(DeuDate startDate) {
        int startDateDayCount = DeuDate.convertDeuDateToDayCount(startDate);
        int endDateDayCount = DeuDate.convertDeuDateToDayCount(this);
        return endDateDayCount - startDateDayCount;
    }

    /**
     * @param targetDate target date to compare
     * @return 1 if this is greater than targetDate, -1 if this is less than targetDate, 0 if this is equal to targetDate
     */
    public int compareTo(DeuDate targetDate) {
        if (this.year > targetDate.year) {
            return 1;
        } else if (this.year < targetDate.year) {
            return -1;
        } else {
            if (this.month > targetDate.month) {
                return 1;
            } else if (this.month < targetDate.month) {
                return -1;
            } else {
                return Integer.compare(this.day, targetDate.day);
            }
        }
    }
}
