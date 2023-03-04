public class PhoneNumber {
    String countryCode;
    String cityCode;
    String number;

    public static PhoneNumber convertStringPhoneNumberToPhoneNumber(String strPhoneNumber) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.countryCode = strPhoneNumber.substring(0, 3);
        phoneNumber.cityCode = strPhoneNumber.substring(3, 6);
        phoneNumber.number = strPhoneNumber.substring(6);
        return phoneNumber;
    }

    public String toString() {
        return countryCode + " (" + cityCode + ") " + number;
    }
}
