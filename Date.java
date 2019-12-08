package PersonData;

public class Date {
	private int Year;
	private int month;
	private int day;

	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}

	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

	public void displayDate() { //ex) display like "1900-1-1"
		System.out.println("\"" + getYear() + "-" + getMonth() + "-" + getDay() + "\"");
	}

}
