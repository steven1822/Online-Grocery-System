public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
    public int getYear(){
        return year;
    }

    public boolean isGreaterThan(Date date){
        if (this.year < date.year){
            return false;
        }
        if (this.month < date.month){
            return false;
        }
        if ( this.day < date.day){
            return false;
        }
        if (this.day == date.day){
            return false;
        }
        return true;


    }
    @Override
    public String toString(){
        String day = String.valueOf(this.day);
        String month = String.valueOf(this.month);
        String year = String.valueOf(this.year);
        if (this.day < 10 && this.month < 10){
            return "0" + day + "/0" + month + "/" + year;
        }
        if (this.day < 10){
            return "0" + day + "/" + month + "/" + year;
        }
        if (this.month < 10){
            return day + "/0" + month + "/" + year;
        }
        return day + "/" + month + "/" + year;
    }
}
