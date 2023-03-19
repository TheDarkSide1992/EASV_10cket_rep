package be;

public class EventCoordinator {

    private int eventCoordinatorID;
    private String userName;
    private String name;
    private int userType;
    private String userEmail;
    private String userPhone;

    public int getAdminID() {
        return eventCoordinatorID;
    }

    public void setAdminID(int eventCoordinatorID) {
        this.eventCoordinatorID = eventCoordinatorID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

}
