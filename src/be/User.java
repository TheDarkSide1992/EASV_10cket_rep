package be;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

public abstract class User {
    private int userID;
    private String userNAme;
    private String userFirstName;
    private String userEmail;
    private String userTLF;
    private int userTypeId;
    private String userStringType;

    private byte[]  imageBytes;

    private Image profilePicture;


    public User(int userID, String userNAme, String userFirstName, String userEmail, String userTLF, int userTypeId, String userStringType) {
        this.userID = userID;
        this.userNAme = userNAme;
        this.userFirstName = userFirstName;
        this.userEmail = userEmail;
        this.userTLF = userTLF;
        this.userTypeId = userTypeId;
        this.userStringType = userStringType;

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

    public String getUserStringType() {
        return userStringType;
    }

    public void setUserStringType(String userStringType) {
        this.userStringType = userStringType;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public javafx.scene.image.Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(javafx.scene.image.Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void convertByteToImage() throws Exception{
        javafx.scene.image.Image img = new javafx.scene.image.Image(new ByteArrayInputStream(imageBytes));
        profilePicture = img;
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
