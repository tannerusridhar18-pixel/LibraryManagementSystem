package model;

public class Member {
    private long memberId;
    private String name;
    private String email;
    private String phoneNumber;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String PHONE_REGEX = "^[6-9][0-9]{9}$";

    private void validateEmail(String email){
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email cannot be null or Blank");
        }

        if (!email.matches(EMAIL_REGEX)){
            throw new IllegalArgumentException("Invalid Email");
        }
    }

    private void validatePhoneNumber(String phoneNumber){
        if(phoneNumber == null || phoneNumber.isBlank()){
            throw new IllegalArgumentException("Phone Number cannot be Empty");
        }

        if(!phoneNumber.matches(PHONE_REGEX)){
            throw new IllegalArgumentException("Phone number must contain exactly 10 digits and start with 6, 7, 8, or 9.");
        }
    }
    
    private void validateId(long id){
        if(id <= 0) {
            throw new IllegalArgumentException("Member ID must be a positive number.");
        }
    }
        
    private void validateName(String name){
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Member name cannot be null or empty.");
        }
    }

    public Member(long memberId, String name, String email, String phoneNumber) {
        validateId(memberId);
        validateName(name);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);

        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "------------------------------------------------\n" +
                "Member{\n" +
                "memberId=" + memberId +
                ", \nname='" + name + '\'' +
                ", \nemail='" + email + '\'' +
                ", \nphoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
