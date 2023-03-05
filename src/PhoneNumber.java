public class PhoneNumber {
    private String countryCode;
    private String cityCode;
    private String number;

    public static PhoneNumber convertStringPhoneNumberToPhoneNumber(String strPhoneNumber) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.countryCode = strPhoneNumber.substring(0, 3);
        phoneNumber.cityCode = strPhoneNumber.substring(3, 6);
        phoneNumber.number = strPhoneNumber.substring(6);
        return phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String toString() {
        return countryCode + " (" + cityCode + ") " + number;
    }
}
