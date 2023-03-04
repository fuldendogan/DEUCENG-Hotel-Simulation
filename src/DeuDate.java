public class DeuDate {
    int day;
    int month;
    int year;

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
}
