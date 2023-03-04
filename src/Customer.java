public class Customer {

    String customerId;
    String name;
    String surname;
    Address contactAddress;
    PhoneNumber phoneNumber;

    DeuDate birthDate;
    String gender;

    public void print() {
        String space = "%-10s";
        System.out.println("\tCustomer #" + customerId + " " +
                String.format(space, name) + " " + String.format(space, surname) + " " + String.format(space, gender) + " " +
                String.format(space, birthDate.toString()) + " " + String.format(space, contactAddress.city) + " " +
                String.format(space, phoneNumber.toString()));
    }
}
