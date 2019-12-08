package PersonData;

public abstract class Employee {
	private int EmployeeID;
	private String Name;
	private Date Birthdate;
	private String Department;
	private String PhoneNum;
	private String Address;
	private String Mail;
	private double Salary;
	private String Position;
	private double Scores;

	public Employee(int EmployeeID, String Name, Date Birthdate, String Department, String PhoneNum, String Address,
			String Mail, double Salary, String Position, double Scores) {
		this.EmployeeID = EmployeeID;
		this.Name = Name;
		this.Birthdate = Birthdate;
		this.Department = Department;
		this.PhoneNum = PhoneNum;
		this.Address = Address;
		this.Mail = Mail;
		this.Salary = Salary;
		this.Position = Position;
		this.Scores = Scores;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}

	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	
	public Date getBirthdate() {
		return Birthdate;
	}
	public void setBirthdate(Date birthdate) {
		Birthdate = birthdate;
	}

	
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}

	
	public String getPhoneNum() {
		return PhoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}

	
	public String getMail() {
		return Mail;
	}
	public void setMail(String mail) {
		Mail = mail;
	}

	
	public double getSalary() {
		return Salary;
	}
	public void setSalary(double salary) {
		Salary = salary;
	}

	
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}

	
	public double getScores() {
		return Scores;
	}
	public void setScores(double scores) {
		Scores = scores;
	}

}
