public class Staff {
    String staffId;
    StaffType job; //administrator, receptionist and housekeeper
    String name;
    String surname;
    DeuDate birthDate;
    String gender;
    Address address;
    PhoneNumber phoneNumber;
    Double salary;

    public void print() {
        String space = "%-10s";
        System.out.println("\tEmployee #" + staffId + " " +
                String.format(space, name) + " " + String.format(space, surname) + " " +
                String.format(space, gender) + " " +
                String.format(space, birthDate.toString()) + " " + String.format(space, job.toString().toLowerCase()));
    }
}
