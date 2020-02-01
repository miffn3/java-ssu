public class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
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

    public void addYear() {
        this.setYear(this.year + 1);
    }

    public void decreaseTwoDays() {
        if (this.day > 2) {
            this.setDay(this.day - 2);
        } else {
            if (this.month == 1) {
                this.setYear(this.year - 1);
                this.setMonth(12);
                this.setDay(30);
            } else {
                this.setMonth(this.month - 1);
                if (this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7
                        || this.month == 8 || this.month == 10 || this.month == 12) {
                    this.setDay(30);
                } else if (this.month == 2) {
                    if (this.year% 4 == 0 && this.year % 100 != 0 || this.year % 400 == 0) {
                        this.setDay(28);
                    } else {
                        this.setDay(27);
                    }
                } else {
                    this.setDay(29);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
