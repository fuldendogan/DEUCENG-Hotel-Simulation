public class Customer {
    private String customerId;
    private String name;
    private String surname;
    private Address contactAddress;
    private PhoneNumber phoneNumber;
    private DeuDate birthDate;
    private String gender;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public Address getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(Address contactAddress) {
        this.contactAddress = contactAddress;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public void print() {
        String space = "%-10s";
        System.out.println("\tCustomer #" + customerId + " " + String.format(space, name) + " " +
                String.format(space, surname) + " " + String.format(space, gender) + " " +
                String.format(space, birthDate.toString()) + " " + String.format(space, contactAddress.getCity()) + " " +
                String.format(space, phoneNumber.toString()));
    }
}
