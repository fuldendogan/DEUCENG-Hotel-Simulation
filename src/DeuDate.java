public class DeuDate {

    static int monthDays[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int day;
    int month;
    int year;

    public DeuDate() {
    }

    public DeuDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static DeuDate convertStringDateToDeuDate(String stringDate) {
        String[] date = stringDate.split("\\.");
        DeuDate deuDate = new DeuDate();
        deuDate.day = Integer.valueOf(date[0]);
        deuDate.month = Integer.valueOf(date[1]);
        deuDate.year = Integer.valueOf(date[2]);
        return deuDate;
    }

    public String toString() {
        return day + "." + month + "." + year;
    }

    public boolean isBetweenDates(DeuDate startDate, DeuDate endDate) {
        if (this.year >= startDate.year && this.year <= endDate.year) {
            if (this.month >= startDate.month && this.month <= endDate.month) {
                if (this.day >= startDate.day && this.day <= endDate.day) {
                    return true;
                }
            }
        }
        return false;
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

    private int getGapBetweenDates(DeuDate startDate) {
        int startDateDayCount = DeuDate.convertDeuDateToDayCount(startDate);
        int endDateDayCount = DeuDate.convertDeuDateToDayCount(this);
        return endDateDayCount - startDateDayCount;
    }

    /**
     * @param targetDate
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
                if (this.day > targetDate.day) {
                    return 1;
                } else if (this.day < targetDate.day) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
