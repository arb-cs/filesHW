package home.work.model;

public class User {

    private int userId;
    private String userEmail;
    private String userGender;
    private Address address;
    private int balance;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

   public static class Address {
        private String street;
        private int homeNum;
        private int flatNum;
        private int floor;

       public String getStreet() {
           return street;
       }

       public void setStreet(String street) {
           this.street = street;
       }

       public int getHomeNum() {
           return homeNum;
       }

       public void setHomeNum(int homeNum) {
           this.homeNum = homeNum;
       }

       public int getFlatNum() {
           return flatNum;
       }

       public void setFlatNum(int flatNum) {
           this.flatNum = flatNum;
       }

       public int getFloor() {
           return floor;
       }

       public void setFloor(int floor) {
           this.floor = floor;
       }
   }
}