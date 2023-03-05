public class Staff {
    private String staffId;
    private StaffType job; //administrator, receptionist and housekeeper
    private String name;
    private String surname;
    private DeuDate birthDate;
    private String gender;
    private Address address;
    private PhoneNumber phoneNumber;
    private Double salary;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public StaffType getJob() {
        return job;
    }

    public void setJob(StaffType job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public DeuDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DeuDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void print() {
        String space = "%-10s";
        System.out.println("\tEmployee #" + staffId + " " +
                String.format(space, name) + " " + String.format(space, surname) + " " +
                String.format(space, gender) + " " +
                String.format(space, birthDate.toString()) + " " + String.format(space, job.toString().toLowerCase()));
    }
}
