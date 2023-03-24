package be;

public abstract class User {
    private int userID;
    private String userNAme;
    private String userFirstName;
    private String userEmail;
    private String userTLF;
    private int userTypeId;

    public User(int userID, String userNAme, String userFirstName, String userEmail, String userTLF, int userTypeId) {
        this.userID = userID;
        this.userNAme = userNAme;
        this.userFirstName = userFirstName;
        this.userEmail = userEmail;
        this.userTLF = userTLF;
        this.userTypeId = userTypeId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserNAme() {
        return userNAme;
    }

    public void setUserNAme(String userNAme) {
        this.userNAme = userNAme;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTLF() {
        return userTLF;
    }

    public void setUserTLF(String userTLF) {
        this.userTLF = userTLF;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userNAme='" + userNAme + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userTLF='" + userTLF + '\'' +
                ", userTypeId=" + userTypeId +
                '}';
    }
}
